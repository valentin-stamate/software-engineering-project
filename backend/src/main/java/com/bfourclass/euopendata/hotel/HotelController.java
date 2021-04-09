package com.bfourclass.euopendata.hotel;

import com.bfourclass.euopendata.hotel.json.HotelJSONRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bfourclass.euopendata.requests.APIError;

@RestController
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    /* TODO */
    @GetMapping("hotel/get_hotel")
    public ResponseEntity<Object> getLocation(@RequestBody HotelJSONRequest locationRegisterForm){

        return new ResponseEntity<>(
                new APIError("invalid dsadasd"),
                HttpStatus.BAD_REQUEST
        );
    }

}
