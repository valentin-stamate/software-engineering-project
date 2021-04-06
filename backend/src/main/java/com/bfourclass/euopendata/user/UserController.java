package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.ExternalAPI.OpenWeatherAPI;
import com.bfourclass.euopendata.ExternalAPI.instance.weather.Weather;
import com.bfourclass.euopendata.email.EmailComponent;
import com.bfourclass.euopendata.location.Location;
import com.bfourclass.euopendata.security.SecurityComponent;
import com.bfourclass.euopendata.user.forms.UserRegisterForm;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.util.MimeTypeUtils;
import com.bfourclass.euopendata.user.forms.UserLoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/")
    public String hello() {
        return "hello there. we're an API, not much to see here";
    }

    @PostMapping("user/add_location")
    public String addLocationToUser(@RequestBody String locationName, @RequestBody String username) {
        User user = userService.getUser(username);
        if (!user.existingLocation(locationName)) {
            user.addLocationToFavourites(locationName);
            return "Location added";
        }
        return "Location already exists!";
    }

    @DeleteMapping("user/delete_location")
    public String deleteLocationFromUser(@RequestBody String locationName, @RequestBody String username) {
        User user = userService.getUser(username);
        if (!user.existingLocation(locationName)) {
            user.deleteLocationFromFavourites(locationName);
            return "Location deleted";
        }
        return "Location does not exists!";
    }

    @PostMapping("user/login")
    public User loginUser(@RequestBody UserLoginForm userLoginForm) {
        if (userService.isValidLoginForm(userLoginForm)) {
            User user = userService.loginUser(userLoginForm);
            user.setPassword("");
            user.setDisplayName("");
            return user;
        }
        return null;
    }

    @PostMapping(value = "user/register", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public String registerUser(@RequestBody UserRegisterForm form) {
        if (userService.userExists(form.getUsername())) {
            return "{\"status\": \"failed\", \"reason\": \"user already exists\"}";
        }

        if (userService.isValidRegisterForm(form)) {
            userService.createUserByForm(form);
            return "{\"status\": \"success\"}";
        }
        return "{\"status\": \"failed\", \"reason\": \"invalid form data\"}";
    }

    @GetMapping("get/location")
    public Weather getWeather(@RequestBody String locationName) {
        /* TODO find a proper location for this endpoint */
        return OpenWeatherAPI.requestWeather(locationName);
    }

}
