package com.bfourclass.euopendata.user;

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

    @GetMapping
    @RequestMapping(path = "get/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    @RequestMapping(path = "post/user")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    public User loginUser(String username,String password)
    {
        return new User();
    }

    public void register(String username, String password)
    {
        return new User(username,password);
    }

}
