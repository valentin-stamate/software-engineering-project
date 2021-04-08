package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.email.EmailService;
import com.bfourclass.euopendata.security.SimpleHashingAlgo;
import com.bfourclass.euopendata.security.StringGenerator;
import com.bfourclass.euopendata.user.auth.SecurityContext;
import com.bfourclass.euopendata.user.forms.FormValidator;
import com.bfourclass.euopendata.user.forms.UserRegisterForm;
import com.bfourclass.euopendata.user.forms.UserLoginForm;
import com.bfourclass.euopendata.user_verification.UserVerification;
import com.bfourclass.euopendata.user_verification.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FormValidator formValidator;
    private final EmailService emailService;
    private final UserVerificationService userVerificationService;
    private final SecurityContext securityContext;

    @Autowired
    public UserService(UserRepository userRepository, FormValidator formValidator, EmailService emailService, UserVerificationService userVerificationService, SecurityContext securityContext) {
        this.userRepository = userRepository;
        this.formValidator = formValidator;
        this.emailService = emailService;
        this.userVerificationService = userVerificationService;
        this.securityContext = securityContext;
    }

    public boolean checkTokenIsValid(String token) {
        return securityContext.exists(token);
    }

    public User getUserFromToken(String token) {
        String username = securityContext.extractUsername(token);
        return userRepository.findUserByUsername(username).orElse(null);
    }

    public boolean isValidRegisterForm(UserRegisterForm registerForm) {
        return formValidator.isValidRegisterForm(registerForm);
    }

    public boolean isValidLoginForm(UserLoginForm userLoginForm) {
        return formValidator.isValidLoginForm(userLoginForm);
    }

    public void createUserByForm(UserRegisterForm registerForm) {
        User user = registerForm.toUser();

        String verificationKey = StringGenerator.generate();
        UserVerification userVerification = new UserVerification(user, verificationKey);

        userVerificationService.save(userVerification);

        userRepository.save(user);
        emailService.sendEmailVerificationEmail(user.getUsername(), user.getEmail(), verificationKey);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public String loginUserReturnToken(UserLoginForm userLoginForm) {
        return securityContext.authenticateUserReturnToken(userLoginForm.getUsername());
    }

    public boolean checkUserPassword(UserLoginForm userLoginForm) {

        boolean userExists = userExists(userLoginForm.getUsername());
        if (!userExists) {
            return false;
        }
        Optional<User> optional = userRepository.findUserByUsername(userLoginForm.getUsername());
        if (optional.isEmpty()) {
            return false;
        }
        User dbUser = optional.get();
        return dbUser.getPassword().equals(SimpleHashingAlgo.hash(userLoginForm.getPassword()));
    }

    public boolean userExists(String username) {
        return userRepository.findUserByUsername(username).isPresent();
    }

    public boolean verifyLoginUserCredentials(User user) {
        return isUsernameValid(user.getUsername()) && verifyEmail(user.getEmail());
    }

    private boolean isUsernameValid(String username) {
        return username.length() > 0;
    }

    private boolean verifyEmail(String email) {
        String regex = "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public User getUser(String username) {
        return userRepository.findUserByUsername(username).get();
    }
}
