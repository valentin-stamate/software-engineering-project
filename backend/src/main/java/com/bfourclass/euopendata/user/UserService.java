package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.email.EmailService;
import com.bfourclass.euopendata.hotel.HotelModel;
import com.bfourclass.euopendata.hotel.HotelRepository;
import com.bfourclass.euopendata.hotel.json.HotelJSON;
import com.bfourclass.euopendata.requests.APIError;
import com.bfourclass.euopendata.security.StringGenerator;
import com.bfourclass.euopendata.user.auth.SecurityContext;
import com.bfourclass.euopendata.user.json.UserLoginJSON;
import com.bfourclass.euopendata.user.json.UserRegisterJSONRequest;
import com.bfourclass.euopendata.user_verification.UserVerification;
import com.bfourclass.euopendata.user_verification.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final UserVerificationService userVerificationService;
    private final SecurityContext securityContext;
    private final HotelRepository hotelRepository;

    @Autowired
    public UserService(UserRepository userRepository, EmailService emailService, UserVerificationService userVerificationService, SecurityContext securityContext, HotelRepository hotelRepository) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.userVerificationService = userVerificationService;
        this.securityContext = securityContext;
        this.hotelRepository = hotelRepository;
    }

    public boolean checkTokenIsValid(String token) {
        return securityContext.exists(token);
    }

    public UserModel getUserFromToken(String token) {
        String username = securityContext.extractUsername(token);
        return userRepository.findUserByUsername(username);
    }

    public void createUserByForm(UserRegisterJSONRequest registerForm) {
        UserModel userModel = registerForm.toUser();

        String verificationKey = StringGenerator.generate();
        UserVerification userVerification = new UserVerification(userModel, verificationKey);

        userVerificationService.save(userVerification);

        userRepository.save(userModel);
        emailService.sendEmailVerificationEmail(userModel.getUsername(), userModel.getEmail(), verificationKey);
    }

    public void makeAdmin(String username) {
        UserModel userModel = getUserByLogin(username);
        userModel.setAdmin(true);
        userRepository.save(userModel);
    }

    public void removeAdmin(String username) {
        UserModel userModel = getUserByLogin(username);
        userModel.setAdmin(false);
        userRepository.save(userModel);
    }

    public String loginUserReturnToken(String username) {
        return securityContext.authenticateUserReturnToken(username);
    }

    public boolean userExists(String login) {
        return userRepository.findUserByUsername(login) != null || userRepository.findUserByEmail(login) != null;
    }

    public ResponseEntity<Object> checkUserToken(String token) {
        // check if token exists in request
        if (token == null) {
            return new ResponseEntity<>(
                    new APIError("Missing Authorization header"),
                    HttpStatus.UNAUTHORIZED
            );
        }

        // check if token exists in SecurityContext
        if (!checkTokenIsValid(token)) {
            return new ResponseEntity<>(
                    new APIError("Invalid Authorization header"),
                    HttpStatus.UNAUTHORIZED
            );
        }

        return null;
    }

    public UserModel getUserByLogin(String login) {
        UserModel userModel = userRepository.findUserByUsername(login);

        if (userModel != null) {
            return userModel;
        }

        return userRepository.findUserByEmail(login);
    }

    public List<HotelJSON> getUserHotels(UserModel userModel) {
        List<HotelJSON> response = new ArrayList<>();

        List<HotelModel> hotels = userModel.getUserHotels();

        for (HotelModel hotelModel : hotels) {
            response.add(new HotelJSON(hotelModel.getId(), hotelModel.getIdentifier(), hotelModel.getHotelName(), hotelModel.getLocationName(), hotelModel.getAverageRating(), hotelModel.getVotes()));
        }

        return response;
    }

    public void addHotel(UserModel userModel, HotelModel hotelModel) {
        userModel.addHotel(hotelModel);
        userRepository.save(userModel);
        if (hotelRepository.findById(hotelModel.getId()).isEmpty()) {
            hotelRepository.save(hotelModel);
        }
    }

    public void deleteUserHotel(UserModel userModel, HotelModel hotelModel) {
        userModel.deleteUserHotel(hotelModel);
        userRepository.save(userModel);
    }
}
