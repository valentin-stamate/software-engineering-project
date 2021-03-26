package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.email.EmailComponent;
import com.bfourclass.euopendata.security.SecurityComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    SecurityComponent securityComponent;
    EmailComponent emailComponent;


    private final UserService userService;


    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("get/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("post/user")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PostMapping("user/add_location")
    public String addLocationToUser() {
        return "Add location endpoint is working";
    }

    public User loginUser(String username,String password)
    {
        return new User();
    }

    public User register(String username, String password) {
        return new User(username, password);
    }

}
