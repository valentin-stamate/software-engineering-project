package com.bfourclass.euopendata.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    private final LocationService locationService;

    @Autowired
    LocationController(LocationService locationService) {
        this.locationService = locationService;
    }



}
