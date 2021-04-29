package com.bfourclass.euopendata.hotel;

import com.bfourclass.euopendata.hotel.json.HotelJSON;
import com.bfourclass.euopendata.requests.APISuccess;
import com.bfourclass.euopendata.user.UserService;
import com.bfourclass.euopendata.user.json.AddHotelJsonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {

    private final HotelService hotelService;
    private final UserService userService;

    @Autowired
    HotelController(HotelService hotelService, UserService userService) {
        this.hotelService = hotelService;
        this.userService = userService;
    }

    @GetMapping("hotel/all_hotels")
    public ResponseEntity<Object> getLocation(){
        List<HotelJSON> hotels = hotelService.getHotels();

        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addHotel(@RequestBody AddHotelJsonRequest request, @RequestHeader(name = "Authorization") String token) {
        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        // ... still writing, pushed to allow parallel development

        return new ResponseEntity<>(new APISuccess("added hotel successfully"), HttpStatus.OK);
    }

    /* TODO Hotel Information */

}
