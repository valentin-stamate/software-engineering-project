package com.bfourclass.euopendata.user.json;

import com.bfourclass.euopendata.hotel.HotelModel;

public class AddHotelJsonRequest {
    private final String name;
    private final String location;
    private final String identifier;
    private final String photoLink;
    private final String description;
    private final float price;

    public AddHotelJsonRequest(String name, String location, String identifier, String photoLink, String description, float price) {
        this.name = name;
        this.location = location;
        this.identifier = identifier;
        this.photoLink = photoLink;
        this.description = description;
        this.price = price;
    }

    public HotelModel toHotel() {
        return new HotelModel(identifier, name, location, photoLink, description, price);
    }
}