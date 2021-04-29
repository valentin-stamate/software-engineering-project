package com.bfourclass.euopendata.user.json;

import com.bfourclass.euopendata.hotel.HotelModel;

public class AddHotelJsonRequest {
    private final String name;
    private final String location;
    private final String identifier;
    private final String photoLink;
    private final String description;
    private final Float price;

    public AddHotelJsonRequest(String name, String location, String identifier, String photoLink, String description, Float price) {
        this.name = name;
        this.location = location;
        this.identifier = identifier;
        this.photoLink = photoLink;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public String getDescription() {
        return description;
    }

    public Float getPrice() {
        return price;
    }
}