package com.bfourclass.euopendata.hotel.json;

import com.bfourclass.euopendata.hotel.HotelModel;

public class HotelJSONRequest {
    public final String hotelName;
    public final String locationName;

    public HotelJSONRequest(String hotelName, String locationName) {
        this.hotelName = hotelName;
        this.locationName = locationName;
    }

    public HotelModel toHotelModel() {
        return new HotelModel(hotelName, locationName);
    }
}
