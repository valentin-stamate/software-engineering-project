package com.bfourclass.euopendata.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

}
