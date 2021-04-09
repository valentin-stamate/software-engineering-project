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
    private final EmailService emailService;
    private final UserVerificationService userVerificationService;
    private final SecurityContext securityContext;

    @Autowired
    public UserService(UserRepository userRepository, EmailService emailService, UserVerificationService userVerificationService, SecurityContext securityContext) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.userVerificationService = userVerificationService;
        this.securityContext = securityContext;
    }

    public boolean checkTokenIsValid(String token) {
        return securityContext.exists(token);
    }

    public UserModel getUserFromToken(String token) {
        String username = securityContext.extractUsername(token);
        return userRepository.findUserByUsername(username);
    }

    public void createUserByForm(UserRegisterForm registerForm) {
        UserModel userModel = registerForm.toUser();

        String verificationKey = StringGenerator.generate();
        UserVerification userVerification = new UserVerification(userModel, verificationKey);

        userVerificationService.save(userVerification);

        userRepository.save(userModel);
        emailService.sendEmailVerificationEmail(userModel.getUsername(), userModel.getEmail(), verificationKey);
    }

    public List<UserModel> getUsers() {
        return userRepository.findAll();
    }

    public String loginUserReturnToken(UserLoginForm userLoginForm) {
        return securityContext.authenticateUserReturnToken(userLoginForm.username);
    }

    public boolean checkUserPassword(UserLoginForm userLoginForm) {

        boolean userExists = userExists(userLoginForm.username);
        if (!userExists) {
            return false;
        }

        UserModel dbUserModel = userRepository.findUserByUsername(userLoginForm.username);

        if (dbUserModel == null) {
            return false;
        }

        return dbUserModel.getPassword().equals(SimpleHashingAlgo.hash(userLoginForm.username));
    }

    public boolean userExists(String username) {
        return userRepository.findUserByUsername(username) != null;
    }

    public boolean verifyLoginUserCredentials(UserModel userModel) {
        return isUsernameValid(userModel.getUsername()) && verifyEmail(userModel.getEmail());
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

    public UserModel getUser(String username) {
        return userRepository.findUserByUsername(username);
    }

    public boolean checkUserIsActivated(String username) {
        UserModel user = userRepository.findUserByUsername(username);
        if (user == null) {
            return false;
        }
        return user.activated();
    }
}
