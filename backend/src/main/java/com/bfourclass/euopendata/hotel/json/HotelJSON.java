package com.bfourclass.euopendata.hotel.json;

public class HotelJSON {
    public final String hotelName;
    public final String locationName;
    public final double averageRating;
    public int votes;

    public HotelJSON(String hotelName, String locationName, double averageRating, int votes) {
        this.hotelName = hotelName;
        this.locationName = locationName;
        this.averageRating = averageRating;
        this.votes = votes;
    }
}
