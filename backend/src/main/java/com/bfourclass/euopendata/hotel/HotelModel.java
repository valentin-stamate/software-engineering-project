package com.bfourclass.euopendata.hotel;

import com.bfourclass.euopendata.hotel_review.HotelReviewModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    /* TODO return reviews list instance */
    public List<HotelReviewModel> getReviews() {
        return List.copyOf(new ArrayList<>());
    }

    public void removeReview(Long reviewId) {
        /* TODO: loop trough all hotel reviews, find the instace and remove it */
    }
}
