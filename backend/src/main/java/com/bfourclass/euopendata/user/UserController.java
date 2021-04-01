package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.email.EmailComponent;
import com.bfourclass.euopendata.security.SecurityComponent;
import com.bfourclass.euopendata.user.forms.UserRegisterForm;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
//    @GetMapping("get/users")
//    public List<User> getUsers() {
//        return userService.getUsers();
//    }

    /* Example purpose */
//    @PostMapping("post/user")
//    public void addUser(@RequestBody User user) {
//        userService.addUser(user);
//    }

    @PostMapping("user/add_location")
    public String addLocationToUser() {
        return "Add location endpoint is working";
    }
    
    @PostMapping("user/login")
    public User loginUser(@RequestBody User user){
        return userService.loginUser(user);
    }

    public User loginUser(String username,String password)
    {
        return new User();
    }

    @PostMapping(value = "user/register", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public String registerUser(@RequestBody UserRegisterForm form) {
        if (userService.isValidRegisterForm(form)) {
            userService.createUserByForm(form);
            return "{\"status\": \"success\"}";
        }
        return "{\"status\": \"failed\", \"reason\": \"invalid form data\"}";
    }
}
