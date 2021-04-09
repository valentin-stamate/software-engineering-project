package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.ExternalAPI.OpenWeatherAPI;
import com.bfourclass.euopendata.ExternalAPI.instance.weather.Weather;
import com.bfourclass.euopendata.location.Location;
import com.bfourclass.euopendata.user.auth.AuthSuccessResponse;
import com.bfourclass.euopendata.user.forms.UserLocationsResponse;
import com.bfourclass.euopendata.user.forms.UserLoginForm;
import com.bfourclass.euopendata.user.forms.UserRegisterForm;
import com.bfourclass.euopendata.user.forms.UserRequestPrefLocationsForm;
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

import static org.hibernate.internal.util.collections.ArrayHelper.toList;

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
    public ResponseEntity<Object> addLocationToUser(@RequestBody String locationName, @RequestHeader(name = "Authorization", required = false) String token) {
        // check if token exists in request
        if (token == null) {
            return new ResponseEntity<>(
                new APIError("missing Authorization header"),
                HttpStatus.UNAUTHORIZED
            );
        }
        // check if token exists in SecurityContext
        if (!userService.checkTokenIsValid(token)) {
            return new ResponseEntity<>(
                    new APIError("invalid Authorization header"),
                    HttpStatus.UNAUTHORIZED
            );
        }

        // TODO: update the database
        User user = userService.getUserFromToken(token);
        if (!user.existingLocation(locationName)) {
            user.addLocationToFavourites(locationName);
        }
        return new ResponseEntity<>(new APISuccess("location added successfully"), HttpStatus.OK);
    }

    @DeleteMapping("user/delete_location")
    public ResponseEntity<Object> deleteLocationFromUser(@RequestBody String locationName, @RequestHeader(name = "Authorization", required = false) String token) {
        // check if token exists in request
        if (token == null) {
            return new ResponseEntity<>(
                    new APIError("missing Authorization header"),
                    HttpStatus.UNAUTHORIZED
            );
        }
        // check if token exists in SecurityContext
        if (!userService.checkTokenIsValid(token)) {
            return new ResponseEntity<>(
                    new APIError("invalid Authorization header"),
                    HttpStatus.UNAUTHORIZED
            );
        }
        // TODO: update the database
        User user = userService.getUserFromToken(token);
        if (!user.existingLocation(locationName)) {
            user.deleteLocationFromFavourites(locationName);
            return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("no such location found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("user/login")
    public ResponseEntity<Object> loginUser(@RequestBody UserLoginForm userLoginForm) {
        // check if form is valid
        if (!userService.isValidLoginForm(userLoginForm)) {
            return new ResponseEntity<>(
                    new APIError("invalid login form"),
                    HttpStatus.BAD_REQUEST
            );
        }
        // check if user exists
        if (!userService.userExists(userLoginForm.getUsername())) {
            return new ResponseEntity<>(
                    new APIError("user does not exist"),
                    HttpStatus.BAD_REQUEST
            );
        }
        // check if password is correct
        if (!userService.checkUserPassword(userLoginForm)) {
            return new ResponseEntity<>(
                    new APIError("invalid password"),
                    HttpStatus.UNAUTHORIZED
            );
        }

        // check if account is activated
        if (!userService.checkUserIsActivated(userLoginForm.getUsername())) {
            return new ResponseEntity<>(
                    new APIError("account not activated"),
                    HttpStatus.UNAUTHORIZED
            );
        }

        String token = userService.loginUserReturnToken(userLoginForm);

        return new ResponseEntity<>(
                new AuthSuccessResponse("authentication successful", token),
                HttpStatus.OK
        );
    }

    @PostMapping(value = "user/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserRegisterForm form) {
        if (!userService.isValidRegisterForm(form)) {
            return new ResponseEntity<>(
                    new APIError("invalid form data"),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (userService.userExists(form.getUsername())) {
            return new ResponseEntity<>(
                    new APIError("user already exists"),
                    HttpStatus.BAD_REQUEST
            );
        }

        userService.createUserByForm(form);

        return new ResponseEntity<>(
                new APISuccess("registration successful"),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "user/verify")
    public ResponseEntity<Object> verifyUser(@RequestParam(name="user_verification_key") String userKey) {
        if (userVerificationService.activateUser(userKey)) {
            return new ResponseEntity<>(
                    new APISuccess("User successfully activated. Now you can log in"),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                new APIError("Wrong verification key"),
                HttpStatus.NOT_FOUND
        );
    }

    @GetMapping("user/locations")
    public ResponseEntity<Object> getUserLocations(@RequestBody UserRequestPrefLocationsForm userRequest) {

        if (!userService.checkTokenIsValid(userRequest.getUserToken())) {
            return new ResponseEntity<>(
                    new APIError("Invalid user token"),
                    HttpStatus.BAD_REQUEST
            );
        }

        User user = userService.getUserFromToken(userRequest.getUserToken());

        UserLocationsResponse response = new UserLocationsResponse(toList(user.getLocations()));

        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

}
