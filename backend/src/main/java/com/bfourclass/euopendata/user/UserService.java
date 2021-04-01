package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.security.SimpleHashingAlgo;
import com.bfourclass.euopendata.user.forms.FormValidator;
import com.bfourclass.euopendata.user.forms.UserRegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FormValidator formValidator;

    @Autowired
    public UserService(UserRepository userRepository, FormValidator formValidator) {
        this.userRepository = userRepository;
        this.formValidator = formValidator;
    }

    public boolean isValidRegisterForm(UserRegisterForm registerForm) {
        return formValidator.isValidRegisterForm(registerForm);
    }

    public void createUserByForm(UserRegisterForm registerForm) {
        userRepository.save(registerForm.toUser());
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User loginUser(User user) {
        if (checkUserPassword(user)) {
            return userRepository.findUserByUsername(user.getUsername()).get();
        }

        return null;
    }

    private boolean checkUserPassword(User user) {

        boolean userExists = userExists(user);
        if (userExists) {
            User dbUser = userRepository.findUserByUsername(user.getUsername()).get();

            return dbUser.getPassword().equals(SimpleHashingAlgo.hash(user.getPassword()));
        }

        return false;
    }

    private boolean userExists(User user) {
        return userRepository.findUserByUsername(user.getUsername()).isPresent();
    }

    public boolean verifyLoginUserCredentials(User user) {
        if (!isUsernameValid(user.getUsername()) || !verifyEmail(user.getEmail())) {
            return false;
        }

        return true;
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

    /* Example purpose */
    public void addUser(User user) {
        userRepository.save(user);
    }

    public User signUpUser(User user) {
//        if (!verifyUserCredentials(user))
//            return null;
//        if (!userExists(user.getUsername()))
//            return null;

        return userRepository.save(user);
    }
}
