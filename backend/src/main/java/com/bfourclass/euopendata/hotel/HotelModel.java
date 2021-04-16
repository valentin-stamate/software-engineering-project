package com.bfourclass.euopendata.hotel;

import com.bfourclass.euopendata.hotel_review.HotelReviewModel;
import com.bfourclass.euopendata.hotel_review.json.HotelReviewJSON;
import com.bfourclass.euopendata.hotel_review.json.HotelReviewJSONUpdateRequest;
import com.bfourclass.euopendata.user.UserModel;

import javax.persistence.*;
import java.util.ArrayList;
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
    private Double averageRating = 0.0D;

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

    public List<HotelReviewJSON> getReviewsAsJSON() {
        List<HotelReviewJSON> hotelReviewJSONList = new ArrayList<>();

        List<HotelReviewModel> hotelReviewModelList = getReviews();

        for (HotelReviewModel hotelReviewModel : hotelReviewModelList) {
            UserModel userModel = hotelReviewModel.getUser();
            hotelReviewJSONList.add(new HotelReviewJSON(hotelReviewModel.getId(),
                    userModel.getUsername(),
                    hotelReviewModel.getRating(),
                    hotelReviewModel.getReviewMessage(),
                    hotelReviewModel.getReviewDate()));
        }

        return hotelReviewJSONList;
    }

    public void updateHotelRating(HotelReviewModel hotelReview) {
        averageRating = (averageRating * hotelReviews.size() + hotelReview.getRating()) / (hotelReviews.size() + 1);
        hotelReviews.add(hotelReview);
    }

    public void deleteReviewById(Long reviewId) {
        for (HotelReviewModel review : hotelReviews) {
            if (review.getId().equals(reviewId)) {
                int reviewRating = review.getRating();
                this.averageRating = (averageRating * hotelReviews.size() - reviewRating) / (hotelReviews.size() - 1);
                hotelReviews.remove(review);
            }
        }
    }

    public void updateHotelReview(Long reviewId, HotelReviewJSONUpdateRequest request) {
        for (HotelReviewModel review : hotelReviews) {
            if (review.getId().equals(reviewId)) {
                int oldRating = review.getRating();
                int newRating = request.rating;
                averageRating = (averageRating * hotelReviews.size() - oldRating + newRating) / hotelReviews.size();
                review.setReviewMessage(request.message);
                review.setReviewDate(request.dateAdded);
                review.setRating(newRating);
                break;
            }
        }
    }
}
