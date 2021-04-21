package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.hotel.HotelModel;
import com.bfourclass.euopendata.hotel.HotelService;
import com.bfourclass.euopendata.hotel.json.HotelJSON;
import com.bfourclass.euopendata.user.json.*;
import com.bfourclass.euopendata.user_verification.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bfourclass.euopendata.requests.APIError;
import com.bfourclass.euopendata.requests.APISuccess;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final UserVerificationService userVerificationService;
    private final HotelService hotelService;

    @Autowired
    UserController(UserService userService, UserVerificationService userVerificationService, HotelService hotelService) {
        this.userService = userService;
        this.userVerificationService = userVerificationService;
        this.hotelService = hotelService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello there. we're an API, not much to see here";
    }

    @PostMapping("user/add_hotel")
    public ResponseEntity<Object> addLocationToUser(@RequestBody HotelJSON request, @RequestHeader(name = "Authorization") String token) {

        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        UserModel userModel = userService.getUserFromToken(token);

        /* TODO the new identifier */
        if (!userModel.hasHotel(request.hotelName)) {
            HotelModel hotelModel = new HotelModel(request.identifier, request.hotelName, request.locationName);

            hotelService.createHotelIfNotExists(hotelModel);

            userService.addHotel(userModel, hotelModel);
        } else {
            return new ResponseEntity<>(new APIError("Hotel is already saved"), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(new APISuccess("Location added successfully"), HttpStatus.OK);
    }

    @DeleteMapping("user/delete_hotel")
    public ResponseEntity<Object> deleteLocationFromUser(@RequestParam(name = "hotel_id") long id, @RequestHeader(name = "Authorization") String token) {
        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        UserModel userModel = userService.getUserFromToken(token);
        HotelModel hotelModel = hotelService.getHotelById(id);

        if (userModel.hasHotel(id)) {
            userService.deleteUserHotel(userModel, hotelModel);
            return new ResponseEntity<>("Hotel deleted successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("User doesn't have the location", HttpStatus.NOT_FOUND);
    }

    @PostMapping("user/login")
    public ResponseEntity<Object> loginUser(@RequestBody UserLoginJSONRequest request) {
        // check if form is valid
        if (!request.isValid()) {
            return new ResponseEntity<>(new APIError("Invalid login form"), HttpStatus.BAD_REQUEST);
        }
        // check if user exists
        if (!userService.userExists(request.username)) {
            return new ResponseEntity<>(new APIError("User does not exist"), HttpStatus.BAD_REQUEST);
        }

        UserModel userModel = userService.getUserByUsername(request.username);

        // check if password is correct
        if (!userModel.checkUserPassword(request.password)) {
            return new ResponseEntity<>(new APIError("Invalid password"), HttpStatus.UNAUTHORIZED);
        }

        // check if account is activated
        if (!userModel.isActivated()) {
            return new ResponseEntity<>(new APIError("Account not activated"), HttpStatus.UNAUTHORIZED);
        }

        String token = userService.loginUserReturnToken(request);

        return new ResponseEntity<>(new UserJSON(userModel.getUsername(), userModel.getEmail(), userModel.getProfilePhotoLink(), token), HttpStatus.OK);
    }

    @PostMapping(value = "user/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserRegisterJSONRequest form) {
        if (!form.isValid()) {
            return new ResponseEntity<>(new APIError("Invalid form data"), HttpStatus.BAD_REQUEST);
        }

        if (userService.userExists(form.username)) {
            return new ResponseEntity<>(new APIError("User already exists"), HttpStatus.BAD_REQUEST);
        }

        userService.createUserByForm(form);

        return new ResponseEntity<>(new APISuccess("Registration successful"), HttpStatus.OK);
    }

    @GetMapping(value = "user/verify")
    public ResponseEntity<Object> verifyUser(@RequestParam(name="user_verification_key") String userKey) {
        if (userVerificationService.activateUser(userKey)) {
            return new ResponseEntity<>(new APISuccess("User successfully activated. Now you can log in"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new APIError("Wrong verification key"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("user/hotels")
    public ResponseEntity<Object> getUserHotels(@RequestHeader(name = "Authorization") String token) {

        System.out.println(token);
        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        UserModel userModel = userService.getUserFromToken(token);

        List<HotelJSON> response = userService.getUserHotels(userModel);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /* User as admin */
    @PostMapping("admin/add_hotel")
    public ResponseEntity<Object> addLocationAdmin(@RequestParam HotelJSON request, @RequestHeader(name = "Authorization") String token) {

        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        UserModel userModel = userService.getUserFromToken(token);

        if (!userModel.hasHotel(request.hotelName)) {
            HotelModel hotelModel = new HotelModel(request.identifier, request.hotelName, request.locationName);

            hotelService.createHotelIfNotExists(hotelModel);

            userModel.addHotel(hotelModel);
        } else {
            return new ResponseEntity<>(new APIError("Hotel is already saved"), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(new APISuccess("Location added successfully"), HttpStatus.OK);
    }

    @DeleteMapping("admin/delete_hotel")
    public ResponseEntity<Object> deleteLocationAdmin(@RequestBody HotelJSON request, @RequestHeader(name = "Authorization") String token, String username) {
        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        UserModel userModel = userService.getUserByUsername(username);
        HotelModel hotelModel = hotelService.getHotelById(request.id);

        if (userModel.hasHotel(request.hotelName)) {
            userService.deleteUserHotel(userModel, hotelModel);
            return new ResponseEntity<>("Hotel from the user specified is deleted successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Specified user doesn't have the location", HttpStatus.NOT_FOUND);
    }

    @PatchMapping("admin/make_admin")
    public ResponseEntity<Object> makeAdmin(
            @RequestParam String username,
            @RequestHeader(name = "Authorization", required = true) String token) {

        if (!userService.checkTokenIsValid(token)) {
            return new ResponseEntity<>(new APIError("unauthorized"), HttpStatus.UNAUTHORIZED);
        }
        UserModel admin = userService.getUserFromToken(token);
        if (!admin.isAdmin()) {
            return new ResponseEntity<>(new APIError("unauthorized"), HttpStatus.UNAUTHORIZED);
        }
        if (username == null) {
            return new ResponseEntity<>(new APIError("username query unspecified"), HttpStatus.BAD_REQUEST);
        }
        if (!userService.userExists(username)) {
            return new ResponseEntity<>(new APIError("username doesn't exist"), HttpStatus.NOT_FOUND);
        }
        userService.makeAdmin(username);
        return new ResponseEntity<>(new APISuccess("successfully added admin"), HttpStatus.OK);
    }

    @PatchMapping("admin/remove_admin")
    public ResponseEntity<Object> removeAdmin(
            @RequestParam String username,
            @RequestHeader(name = "Authorization", required = true) String token) {

        if (!userService.checkTokenIsValid(token)) {
            return new ResponseEntity<>(new APIError("unauthorized"), HttpStatus.UNAUTHORIZED);
        }
        UserModel admin = userService.getUserFromToken(token);
        if (!admin.isAdmin()) {
            return new ResponseEntity<>(new APIError("unauthorized"), HttpStatus.UNAUTHORIZED);
        }
        if (username == null) {
            return new ResponseEntity<>(new APIError("username query unspecified"), HttpStatus.BAD_REQUEST);
        }
        if (!userService.userExists(username)) {
            return new ResponseEntity<>(new APIError("username doesn't exist"), HttpStatus.NOT_FOUND);
        }
        userService.removeAdmin(username);
        return new ResponseEntity<>(new APISuccess("successfully added admin"), HttpStatus.OK);
    }
}
