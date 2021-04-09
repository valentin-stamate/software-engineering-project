package com.bfourclass.euopendata.hotel;

import javax.persistence.*;

@Entity
@Table(name = "hotels")
public class HotelModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String hotelName;
    private String locationName;

    public HotelModel(String hotelName, String locationName) {
        this.hotelName = hotelName;
        this.locationName = locationName;
    }

    public HotelModel() { }

    public String getHotelName() {
        return hotelName;
    }

    public String getLocationName() {
        return locationName;
    }
}
