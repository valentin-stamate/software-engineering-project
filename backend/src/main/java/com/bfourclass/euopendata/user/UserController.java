package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.ExternalAPI.OpenWeatherAPI;
import com.bfourclass.euopendata.ExternalAPI.instance.weather.Weather;
import com.bfourclass.euopendata.user.forms.UserLoginForm;
import com.bfourclass.euopendata.user.forms.UserRegisterForm;
import com.bfourclass.euopendata.user_verification.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import requests.APIError;
import requests.APISuccess;
import requests.responses.UserResponse;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final UserVerificationService userVerificationService;

    @Autowired
    UserController(UserService userService, UserVerificationService userVerificationService) {
        this.userService = userService;
        this.userVerificationService = userVerificationService;
    }

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
    public ResponseEntity<Object> registerUser(@RequestBody UserRegisterForm form) {
        if (userService.userExists(form.getUsername())) {
            return new ResponseEntity<>(
                    new APIError("Failed", List.of("User already exists")),
                    HttpStatus.NOT_FOUND
            );
        }

        if (userService.isValidRegisterForm(form)) {
            userService.createUserByForm(form);

            return new ResponseEntity<>(
                    new APISuccess("User registered. Check your email to activate your account"),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                new APIError("Failed", List.of("Invalid form data")),
                HttpStatus.NOT_FOUND
        );
    }

    @PostMapping(value = "user/verify")
    public ResponseEntity<Object> verifyUser(@RequestParam(name="verification_key") String userKey) {
        if (userVerificationService.activateUser(userKey)) {
            return new ResponseEntity<>(
                    new APISuccess("User successfully activated. Now you can log in"),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                new APIError("Wrong verification key", List.of("")),
                HttpStatus.NOT_FOUND
        );
    }

    @GetMapping("get/location")
    public Weather getWeather(@RequestBody String locationName) {
        /* TODO find a proper location for this endpoint */
        return OpenWeatherAPI.requestWeather(locationName);
    }

}
