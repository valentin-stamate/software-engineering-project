package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.email.EmailService;
import com.bfourclass.euopendata.external_api.ExternalAPI;
import com.bfourclass.euopendata.external_api.instance.aqicn_data.AirPollution;
import com.bfourclass.euopendata.external_api.instance.covid_information.CovidInformationJSON;
import com.bfourclass.euopendata.external_api.instance.weather.current_weather.Weather;
import com.bfourclass.euopendata.hotel.HotelModel;
import com.bfourclass.euopendata.hotel.HotelRepository;
import com.bfourclass.euopendata.hotel.json.HotelJSON;
import com.bfourclass.euopendata.requests.APIError;
import com.bfourclass.euopendata.security.StringGenerator;
import com.bfourclass.euopendata.user.auth.SecurityContext;
import com.bfourclass.euopendata.user.json.HotelInformationJSON;
import com.bfourclass.euopendata.user.json.UserLoginJSONRequest;
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
        UserModel userModel = getUserByUsername(username);
        userModel.setAdmin(true);
        userRepository.save(userModel);
    }

    public void removeAdmin(String username) {
        UserModel userModel = getUserByUsername(username);
        userModel.setAdmin(false);
        userRepository.save(userModel);
    }

    public String loginUserReturnToken(UserLoginJSONRequest userLoginJSONRequest) {
        return securityContext.authenticateUserReturnToken(userLoginJSONRequest.username);
    }

    public boolean userExists(String username) {
        return userRepository.findUserByUsername(username) != null;
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

    public UserModel getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public List<HotelInformationJSON> getHotelInformation(UserModel userModel) {
        List<HotelInformationJSON> response = new ArrayList<>();

        List<HotelModel> hotels = userModel.getUserHotels();

        for (HotelModel hotelModel : hotels) {
            response.add(getHotelInformation(hotelModel));
        }

        return response;
    }

    public HotelInformationJSON getHotelInformation(HotelModel hotelModel) {
        HotelJSON hotelJSON = new HotelJSON(hotelModel.getHotelName(), hotelModel.getLocationName(), hotelModel.getAverageRating(), hotelModel.getVotes());
        Weather weather = ExternalAPI.getWeather(hotelJSON.locationName);
        CovidInformationJSON covidInformation = ExternalAPI.getCovidNews(hotelJSON.locationName);
        AirPollution airPollution = ExternalAPI.getAirPollution(hotelJSON.locationName);

        return new HotelInformationJSON(hotelJSON, weather, covidInformation, airPollution);
    }

    public void addHotel(UserModel userModel, HotelModel hotelModel) {
        userModel.addHotel(hotelModel);
        hotelRepository.save(hotelModel);
    }

    public void deleteUserHotel(UserModel userModel, HotelModel hotelModel) {
        userModel.deleteUserHotel(hotelModel);
        userRepository.save(userModel);
    }
}
