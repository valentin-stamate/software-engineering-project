package com.bfourclass.euopendata.location;

import com.bfourclass.euopendata.location.forms.LocationRegisterForm;
import com.bfourclass.euopendata.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import requests.APIError;

import java.util.Arrays;
import java.util.List;

@RestController
public class LocationController {

    private final LocationService locationService;

    @Autowired
    LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("location/get_location_info")
    public ResponseEntity<Object> getLocation(@RequestBody LocationRegisterForm locationRegisterForm){



        return new ResponseEntity<>(
                new APIError("invalid dsadasd"),
                HttpStatus.BAD_REQUEST
        );
    }

}
