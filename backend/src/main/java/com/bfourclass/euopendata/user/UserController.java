package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.hotel.HotelModel;
import com.bfourclass.euopendata.hotel.HotelService;
import com.bfourclass.euopendata.hotel.json.HotelJSON;
import com.bfourclass.euopendata.requests.APIError;
import com.bfourclass.euopendata.requests.APISuccess;
import com.bfourclass.euopendata.security.SimpleHashingAlgo;
import com.bfourclass.euopendata.user.forms.FormValidator;
import com.bfourclass.euopendata.user.json.*;
import com.bfourclass.euopendata.user_history.UserHistoryModel;
import com.bfourclass.euopendata.user_verification.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @PostMapping("user/add_hotels")
    public ResponseEntity<Object> addLocationToUser(@RequestBody List<HotelJSON> request, @RequestHeader(name = "Authorization") String token) {

        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        UserModel userModel = userService.getUserFromToken(token);

        List<HotelJSON> alreadySavedHotels = new ArrayList<>();

        for (HotelJSON hotelJSON : request) {
            if (!userModel.hasHotel(hotelJSON.hotelName)) {
                HotelModel hotelModel = new HotelModel(hotelJSON.identifier, hotelJSON.hotelName, hotelJSON.locationName);

                hotelService.createHotelIfNotExists(hotelModel);

                /* TODO find a properly primary key when the id is not given */
                hotelModel = hotelService.getHotelByIdentifier(hotelJSON.identifier);

                userService.addHotel(userModel, hotelModel);
            } else {
                alreadySavedHotels.add(hotelJSON);
            }
        }

        if (!alreadySavedHotels.isEmpty()) {
            StringBuilder responseError = new StringBuilder();

            for (int i = 0; i < alreadySavedHotels.size(); i++) {
                HotelJSON hotelJSON = alreadySavedHotels.get(i);
                String append = i == alreadySavedHotels.size() - 1 ? "</b>" : "</b>, ";
                responseError.append("<b>").append(hotelJSON.hotelName).append(append);
            }

            return new ResponseEntity<>(new APIError(String.format("Hotels: %s are already saved", responseError)), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(new APISuccess("Locations added successfully"), HttpStatus.OK);
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
    public ResponseEntity<Object> loginUser(@RequestBody UserLoginJSON request) {

        String errorMessage = request.isValid();
        if (errorMessage != null) {
            return new ResponseEntity<>(new APIError(errorMessage), HttpStatus.BAD_REQUEST);
        }

        if (!userService.userExists(request.login)) {
            return new ResponseEntity<>(new APIError("User does not exist"), HttpStatus.BAD_REQUEST);
        }

        UserModel userModel = userService.getUserByLogin(request.login);

        if (!userModel.checkUserPassword(request.password)) {
            return new ResponseEntity<>(new APIError("Wrong password"), HttpStatus.UNAUTHORIZED);
        }

        if (!userModel.isActivated()) {
            return new ResponseEntity<>(new APIError("Account not activated"), HttpStatus.UNAUTHORIZED);
        }

        String token = userService.loginUserReturnToken(userModel.getUsername());

        return new ResponseEntity<>(new UserJSON(userModel.getUsername(), userModel.getEmail(), userModel.getProfilePhotoLink(), token), HttpStatus.OK);
    }

    @PostMapping(value = "user/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserRegisterJSONRequest form) {

        String errorMessage = form.isValid();
        if (errorMessage != null) {
            return new ResponseEntity<>(new APIError(errorMessage), HttpStatus.BAD_REQUEST);
        }

        if (userService.userExists(form.username)) {
            return new ResponseEntity<>(new APIError("Username is taken"), HttpStatus.BAD_REQUEST);
        }

        if (userService.userExists(form.email)) {
            return new ResponseEntity<>(new APIError("Email already used"), HttpStatus.BAD_REQUEST);
        }

        userService.createUserByForm(form);

        UserModel userModel = userService.getUserByLogin(form.email);

        if (!userService.sendUserActivationEmail(userModel)) {
            return new ResponseEntity<>(new APIError("Error sending verification email"), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(new APISuccess("Registration successful"), HttpStatus.OK);
    }

    @PostMapping(value = "owner/register")
    public ResponseEntity<Object> registerOwner(@RequestBody OwnerRegisterJSONRequest form) {

        String errorMessage = form.isValid();
        if (errorMessage != null) {
            return new ResponseEntity<>(new APIError(errorMessage), HttpStatus.BAD_REQUEST);
        }

        if (userService.userExists(form.username)) {
            return new ResponseEntity<>(new APIError("Username is taken"), HttpStatus.BAD_REQUEST);
        }

        if (userService.userExists(form.email)) {
            return new ResponseEntity<>(new APIError("Email already used"), HttpStatus.BAD_REQUEST);
        }

        userService.createOwnerByForm(form);

        UserModel userModel = userService.getUserByLogin(form.email);

        // skip verification process
        userModel.activateUser();
        userService.updateUser(userModel);
//        if (!userService.sendUserActivationEmail(userModel)) {
//            return new ResponseEntity<>(new APIError("Error sending verification email"), HttpStatus.NOT_ACCEPTABLE);
//        }

        return new ResponseEntity<>(new APISuccess("Registration successful"), HttpStatus.OK);
    }

    @GetMapping("owner/hotels")
    public ResponseEntity<Object> getOwnerHotels(@RequestHeader(name = "Authorization") String token) {

        System.out.println(token);
        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        UserModel userModel = userService.getUserFromToken(token);

        List<HotelModel> response = userService.getOwnerHotels(userModel.getUsername());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PostMapping(value = "user/resent_activation_token")
    public ResponseEntity<Object> resendActivationToken(@RequestBody UserLoginJSON request) {

        if (!userService.userExists(request.login)) {
            return new ResponseEntity<>(new APIError("User does not exist"), HttpStatus.BAD_REQUEST);
        }

        UserModel userModel = userService.getUserByLogin(request.login);

        if (!userModel.checkUserPassword(request.password)) {
            return new ResponseEntity<>(new APIError("Wrong password"), HttpStatus.UNAUTHORIZED);
        }

        if (userModel.isActivated()) {
            return new ResponseEntity<>(new APIError("Your account is already activated"), HttpStatus.UNAUTHORIZED);
        }

        if (!userService.sendUserActivationEmail(userModel)) {
            return new ResponseEntity<>(new APIError("Error sending email"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new APISuccess("Verification token resend"), HttpStatus.OK);
    }

    @GetMapping(value = "user/verify")
    public ResponseEntity<Object> verifyUser(@RequestParam(name = "user_verification_key") String userKey, @RequestParam(name = "new_email", required = false) String newEmail) {
        if (newEmail == null) {
            if (userVerificationService.activateUser(userKey)) {
                return new ResponseEntity<>(new APISuccess("User successfully activated. Now you can log in"), HttpStatus.OK);
            }
        } else {
            /* TODO verify if the new email is already taken */
            UserModel userModel = userVerificationService.getUserModel(userKey);
            userVerificationService.changeEmail(userModel, newEmail);
            return new ResponseEntity<>(new APISuccess("Email changed successfully."), HttpStatus.OK);
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

    @PostMapping("user/update")
    public ResponseEntity<Object> updateUser(@RequestBody UserJSON userJSON, @RequestHeader(name = "Authorization") String token) {
        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }
        UserModel userModel = userService.getUserFromToken(token);

        if (!FormValidator.isValidUsername(userModel.getUsername())) {
            return new ResponseEntity(new APIError("Invalid username"), HttpStatus.BAD_REQUEST);
        }

        if (!FormValidator.isValidLink(userModel.getProfilePhotoLink())) {
            return new ResponseEntity(new APIError("Invalid photo-link"), HttpStatus.BAD_REQUEST);
        }

        userModel.setProfilePhotoLink(userJSON.profilePhotoLink);
        userModel.setUsername(userJSON.username);
        userService.updateUser(userModel);
        return new ResponseEntity(new APISuccess("User update with success"), HttpStatus.OK);

    }

    @PostMapping("user/update_password")
    public ResponseEntity<Object> updatePassword(@RequestBody UserChangePassJSon userJSON, @RequestHeader(name = "Authorization") String token) {
        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }
        UserModel userModel = userService.getUserFromToken(token);
        if (!userModel.checkUserPassword(userJSON.oldPassword)) {
            return new ResponseEntity(new APIError("Invalid current password"), HttpStatus.BAD_REQUEST);
        }
        if (!FormValidator.isValidPassword(userJSON.newPassword)) {
            return new ResponseEntity(new APIError("Invalid new password"), HttpStatus.BAD_REQUEST);
        }
        userModel.setPassword(SimpleHashingAlgo.hash(userJSON.newPassword));
        userService.updateUser(userModel);
        return new ResponseEntity(new APISuccess("User updated with success"), HttpStatus.OK);
    }

    @PostMapping("user/update_email")
    public ResponseEntity<Object> updateEmail(@RequestBody UserJSON userJSON, @RequestHeader(name = "Authorization") String token) {
        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }
        UserModel userModel = userService.getUserFromToken(token);
        if (!FormValidator.isValidEmail(userModel.getEmail())) {
            return new ResponseEntity(new APIError("Invalid email"), HttpStatus.BAD_REQUEST);
        }
        userService.sendEmailVerificationUpdate(userModel, userJSON.email);
        return new ResponseEntity(new APISuccess("An email was sent to you new address."), HttpStatus.OK);
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

        UserModel userModel = userService.getUserByLogin(username);
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

    @PatchMapping("(admin/remove_admin)")
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

    @DeleteMapping("user/delete_search_query")
    public ResponseEntity<Object> deleteSearchedHotel(@RequestParam(name = "hotel_query_id") long id, @RequestHeader(name = "Authorization") String token) {
        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        UserModel userModel = userService.getUserFromToken(token);

        /* TODO, explicit the error */
        userService.removeSearchedHotel(userModel, id);
        return new ResponseEntity<>(new APISuccess("Deleted Successfully"), HttpStatus.OK);
    }

    @GetMapping("user/get_history")
    public ResponseEntity<Object> getUserSearchHistory(@RequestHeader(name = "Authorization") String token) {
        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        UserModel userModel = userService.getUserFromToken(token);

        List<UserHistoryModel> userHistoryJSONS = userModel.getUserSearchHistory();

        return new ResponseEntity<>(userHistoryJSONS, HttpStatus.OK);
    }

}
