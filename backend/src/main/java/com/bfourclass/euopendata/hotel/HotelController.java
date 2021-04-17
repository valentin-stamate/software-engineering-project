package com.bfourclass.euopendata.hotel;

import com.bfourclass.euopendata.hotel.json.HotelJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("hotel/all_hotels")
    public ResponseEntity<Object> getLocation(){
        List<HotelJSON> hotels = hotelService.getHotels();

        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

}
