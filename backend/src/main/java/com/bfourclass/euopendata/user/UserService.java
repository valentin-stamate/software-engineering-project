package com.bfourclass.euopendata.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public boolean verifySignUpCredentials(User user){
        return true;
    }

    /* Example purpose */
    public void addUser(User user){
        userRepository.save(user);
    }
    public User signUpUser(User user) {
        if(!verifySignUpCredentials(user))
            return null;
        if(!userExists(user.getUsername()))
            return null;

        return userRepository.save(user);
    }
}
