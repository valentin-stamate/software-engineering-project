package com.bfourclass.euopendata.hotel;

import com.bfourclass.euopendata.hotel_review.HotelReviewModel;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "hotels")
public class HotelModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private Set<HotelReviewModel> hotelReviews;

    @Column(unique = true)
    private String hotelName;
    private String locationName;
    private Double averageRating;

    public HotelModel(String hotelName, String locationName) {
        this.hotelName = hotelName;
        this.locationName = locationName;
    }

    public HotelModel() {
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getLocationName() {
        return locationName;
    }

    public Long getId() {
        return id;
    }

    public List<HotelReviewModel> getReviews() {
        return List.copyOf(hotelReviews);
    }

    public void removeReview(Long reviewId) {
        for (HotelReviewModel hotelReview : hotelReviews) {
            if (hotelReview.getId().equals(reviewId)) {
                hotelReviews.remove(hotelReview);
                return;
            }
        }
    }

    public void updateHotelRating(HotelReviewModel hotelReview) {
        this.averageRating = (averageRating * hotelReviews.size() + hotelReview.getRating()) / (hotelReviews.size() + 1);
        hotelReviews.add(hotelReview);
    }
}
