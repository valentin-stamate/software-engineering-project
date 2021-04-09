package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.email.EmailService;
import com.bfourclass.euopendata.requests.APIError;
import com.bfourclass.euopendata.security.StringGenerator;
import com.bfourclass.euopendata.user.auth.SecurityContext;
import com.bfourclass.euopendata.user.forms.UserLoginForm;
import com.bfourclass.euopendata.user.forms.UserRegisterForm;
import com.bfourclass.euopendata.user_verification.UserVerification;
import com.bfourclass.euopendata.user_verification.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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

    private boolean checkTokenIsValid(String token) {
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

    public String loginUserReturnToken(UserLoginForm userLoginForm) {
        return securityContext.authenticateUserReturnToken(userLoginForm.username);
    }

    public boolean userExists(String username) {
        return userRepository.findUserByUsername(username) != null;
    }

    private boolean verifyEmail(String email) {
        String regex = "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public void updateUser(UserModel userModel) {
        userRepository.save(userModel);
    }

    public ResponseEntity<Object> checkUserToken(String token) {
        // check if token exists in request
        if (token == null) {
            return new ResponseEntity<>(
                    new APIError("Missing Authorization header"),
                    HttpStatus.UNAUTHORIZED
            );
        }

        // check if token exists in SecurityContext
        if (!checkTokenIsValid(token)) {
            return new ResponseEntity<>(
                    new APIError("Invalid Authorization header"),
                    HttpStatus.UNAUTHORIZED
            );
        }

        return null;
    }

    public UserModel getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
