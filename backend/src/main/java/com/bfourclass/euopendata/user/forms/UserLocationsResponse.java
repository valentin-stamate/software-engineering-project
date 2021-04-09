package com.bfourclass.euopendata.user.forms;

import com.bfourclass.euopendata.hotel.HotelModel;

import java.util.List;

public class UserLocationsResponse {
    private List<HotelModel> hotels;

    public UserLocationsResponse(List<HotelModel> hotels) {
        this.hotels = hotels;
    }

    public List<HotelModel> getLocations() {
        return hotels;
    }

    public void setLocations(List<HotelModel> hotels) {
        this.hotels = hotels;
    }
}
