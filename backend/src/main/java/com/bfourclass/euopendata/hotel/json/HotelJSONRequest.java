package com.bfourclass.euopendata.hotel.json;

import com.bfourclass.euopendata.hotel.HotelModel;

public class HotelJSONRequest {
    public final String hotelName;
    public final String locationName;
    public final String message;
    public final Integer rating;

    public HotelJSONRequest(String hotelName, String locationName, String message, Integer rating) {
        this.hotelName = hotelName;
        this.locationName = locationName;
        this.message = message;
        this.rating = rating;
    }


    public HotelModel toHotelModel() {
        return new HotelModel(hotelName, locationName);
    }
}
