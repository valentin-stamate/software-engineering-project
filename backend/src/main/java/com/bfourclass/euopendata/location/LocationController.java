package com.bfourclass.euopendata.location;

import com.bfourclass.euopendata.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class LocationController {

    private final LocationService locationService;

    @Autowired
    LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("user/get_locations")
    public List<Location> getLocations(){
        return Arrays.asList(new Location("User location list"));
    }



}
