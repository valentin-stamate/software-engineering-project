package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.email.EmailComponent;
import com.bfourclass.euopendata.security.SecurityComponent;
import com.bfourclass.euopendata.user.forms.UserLoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /* Example purpose */
    @GetMapping("get/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    /* Example purpose */
    @PostMapping("post/user")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PostMapping("user/add_location")
    public String addLocationToUser() {
        return "Add location endpoint is working";
    }
    
    @PostMapping("user/login")
    public User loginUser(@RequestBody UserLoginForm userLoginForm){
        User user = userService.loginUser(userLoginForm);
        user.setPassword("");
        user.setDisplayName("");
        return user;
    }

    public User loginUser(String username,String password)
    {
        return new User();
    }

    @PostMapping("user/register")
    public String registerUser() {
        return "Trying to register user... to do";
    }

    public User register(String username, String password) {
        return new User(username, password);
    }

}
