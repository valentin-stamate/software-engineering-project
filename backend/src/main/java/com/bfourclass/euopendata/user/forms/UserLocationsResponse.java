package com.bfourclass.euopendata.user.forms;

import com.bfourclass.euopendata.location.Location;

import java.util.List;

public class UserLocationsResponse {
    private List<Location> locations;

    public UserLocationsResponse(List<Location> locations) {
        this.locations = locations;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
