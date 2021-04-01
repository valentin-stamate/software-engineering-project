package com.bfourclass.euopendata.user;

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

    public boolean userExists(String username) {
        return userRepository.findUserByUsername(username).isPresent();
    }

    public boolean verifyUserCredentials(User user) {
        if (user.getUsername().length() < 8) {
            return false;
        }
        String email = user.getEmail();
        String regex = "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (!(matcher.matches())) {
            return false;
        }
        return true;
    }

    public boolean verifyExistenceOfUser(User user) {
        if (!verifyUserCredentials(user))
            return false;
        if (!userExists(user.getUsername()))
            return false;
        return true;
    }

    /* Example purpose */
    public void addUser(User user) {
        userRepository.save(user);
    }

    public User signUpUser(User user) {
        if (!verifyUserCredentials(user))
            return null;
        if (!userExists(user.getUsername()))
            return null;

        return userRepository.save(user);
    }
}
