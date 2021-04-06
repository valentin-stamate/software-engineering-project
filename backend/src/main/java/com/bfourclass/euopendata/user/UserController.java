package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.ExternalAPI.OpenWeatherAPI;
import com.bfourclass.euopendata.ExternalAPI.instance.weather.Weather;
import com.bfourclass.euopendata.email.EmailService;
import com.bfourclass.euopendata.user.forms.UserRegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import com.bfourclass.euopendata.user.forms.UserLoginForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import requests.APIError;
import requests.responses.UserResponse;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;
    private EmailService emailService;

    @Autowired
    UserController(UserService userService, EmailService emailService) { }

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
    public ResponseEntity<Object> loginUser(@RequestBody UserLoginForm userLoginForm) {
        String errorMessage = "";

        if (userService.isValidLoginForm(userLoginForm)) {
            User user = userService.loginUser(userLoginForm);

            if (user != null) {
                UserResponse userResponse = new UserResponse(user.getUserId(), user.getUsername(), user.getEmail(), user.getProfilePhotoLink());
                return new ResponseEntity<>(userResponse, HttpStatus.OK);
            } else {
                errorMessage = "Wrong user or password";
            }
        } else {
            errorMessage = "User form is not valid";
        }

        return new ResponseEntity<>(
                new APIError("User not found", List.of(errorMessage)),
                HttpStatus.NOT_FOUND
        );
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
