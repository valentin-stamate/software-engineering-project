package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.security.SimpleHashingAlgo;
import com.bfourclass.euopendata.user.forms.UserLoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User loginUser(UserLoginForm userLoginForm) {
        if (!userExists(userLoginForm.username)) {
            return null;
        }

        if (checkUserPassword(userLoginForm)) {
            return userRepository.findUserByUsername(userLoginForm.username).get();
        }

        return null;
    }

    private boolean checkUserPassword(UserLoginForm userLoginForm) {

        boolean userExists = userExists(userLoginForm.username);
        if (userExists) {
            User dbUser = userRepository.findUserByUsername(userLoginForm.username).get();

            return dbUser.getPassword().equals(SimpleHashingAlgo.hash(userLoginForm.password));
        }

        return false;
    }

    private boolean userExists(String username) {
        return userRepository.findUserByUsername(username).isPresent();
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
