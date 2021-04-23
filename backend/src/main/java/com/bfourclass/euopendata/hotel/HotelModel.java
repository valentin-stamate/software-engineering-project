package com.bfourclass.euopendata.hotel;

import com.bfourclass.euopendata.hotel_review.HotelReviewModel;
import com.bfourclass.euopendata.hotel_review.json.HotelReviewJSON;
import com.bfourclass.euopendata.user.UserModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "hotels")
public class HotelModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String identifier;

    @OneToMany
    private final Set<HotelReviewModel> hotelReviews = new HashSet<>();

    @Column(unique = true)
    private String hotelName;
    private String locationName;

    private double averageRating = 0.0D;
    private int votes = 0;

    @Transient
    private List<UserModel> userSaves= new ArrayList<>();

    public HotelModel(String identifier, String hotelName, String locationName) {
        this.identifier = identifier;
        this.hotelName = hotelName;
        this.locationName = locationName;
    }

    public HotelModel() { }

    public List<HotelReviewJSON> getReviewsAsJSON() {
        List<HotelReviewJSON> hotelReviewJSONList = new ArrayList<>();

        for (HotelReviewModel hotelReviewModel : hotelReviews) {
            UserModel userModel = hotelReviewModel.getUser();
            hotelReviewJSONList.add(new HotelReviewJSON(hotelReviewModel.getId(),
                    userModel.getUsername(),
                    hotelReviewModel.getRating(),
                    hotelReviewModel.getReviewMessage(),
                    hotelReviewModel.getReviewDate()));
        }

        return hotelReviewJSONList;
    }

    public void addHotelReview(HotelReviewModel hotelReviewModel) {
        hotelReviews.add(hotelReviewModel);
        updateHotelReviewNumber(hotelReviewModel.getRating(), false);
    }

    public void removeHotelReview(HotelReviewModel hotelReviewModel) {
        for (HotelReviewModel review : hotelReviews) {
            if (review.getId().equals(hotelReviewModel.getId())) {
                updateHotelReviewNumber(review.getRating(), true);
                hotelReviews.remove(review);
                break;
            }
        }
    }

    public void updateHotelReviewNumber(int review, boolean remove) {
        double oldTotalSum = averageRating * votes;

        if (!remove) {
            oldTotalSum += review;
            votes++;
            averageRating = oldTotalSum / votes;
            return;
        }

        oldTotalSum -= review;
        votes--;

        if (votes == 0) {
            averageRating = 0;
            return;
        }

        averageRating = oldTotalSum / votes;

    }
    public void addUserSave(UserModel user){
        userSaves.add(user);
    }

    /**
     * TO_DO: Implement equals() in UserModel
     *
     */
    public void deleteUserSave(UserModel user){
        userSaves.remove(user);
    }

    /* Getters and Setters */
    public double getAverageRating() {
        return averageRating;
    }

    public int getVotes() {
        return votes;
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

    public String getIdentifier() {
        return identifier;
    }

    public List<UserModel> getUserSave() {
        return userSaves;
    }
}
