package com.bfourclass.smartbooking.hotel.json;

public class HotelJSON {
    public long id;
    public String identifier;
    public String hotelUrl;
    public String hotelName;
    public String locationName;
    public double averageRating;
    public int votes;
    public String photoLink;
    public String description;
    public float price;

    public HotelJSON(long id, String identifier, String hotelUrl, String hotelName, String locationName, double averageRating, int votes, String photoLink, String description, float price) {
        this.id = id;
        this.identifier = identifier;
        this.hotelUrl = hotelUrl;
        this.hotelName = hotelName;
        this.locationName = locationName;
        this.averageRating = averageRating;
        this.votes = votes;
        this.photoLink = photoLink;
        this.description = description;
        this.price = price;
    }

}
