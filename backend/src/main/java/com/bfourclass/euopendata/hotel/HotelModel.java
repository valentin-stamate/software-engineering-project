package com.bfourclass.euopendata.hotel;

import com.bfourclass.euopendata.hotel_review.HotelReviewModel;
import com.bfourclass.euopendata.hotel_review.json.HotelReviewJSON;
import com.bfourclass.euopendata.security.SimpleHashingAlgo;
import com.bfourclass.euopendata.user.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private long id;

    @Column(unique = true)
    private String identifier;

    @Column(unique = true)
    private String hotelUrl;

    @OneToMany
    private final Set<HotelReviewModel> hotelReviews = new HashSet<>();

    @Column(unique = true)
    private String hotelName;
    private String locationName;
    private String photoLink;
    @Column(length = 8192)
    private String description;
    private float price;
    @JsonIgnore
    private Long ownerId;

    private double averageRating = 0.0D;
    private int votes = 0;

    @ManyToMany
    @JsonIgnore
    private final List<UserModel> userSaves = new ArrayList<>();

    public HotelModel(String hotelUrl, String hotelName, String locationName) {
        this.identifier = SimpleHashingAlgo.hash(hotelUrl);
        this.hotelUrl = hotelUrl;
        this.hotelName = hotelName;
        this.locationName = locationName;
    }

    // constructor with fields needed in the web app
    public HotelModel(String hotelUrl, String hotelName, String locationName, String photoLink, String description, float price, Long ownerId) {
        this.identifier = SimpleHashingAlgo.hash(hotelUrl);
        this.hotelUrl = hotelUrl;
        this.hotelName = hotelName;
        this.locationName = locationName;
        this.photoLink = photoLink;
        this.description = description;
        this.price = price;
        this.ownerId = ownerId;
    }

    public HotelModel() {
    }

    @JsonIgnore
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

    public void addUserSave(UserModel user) {
        userSaves.add(user);
    }

    /**
     * TO_DO: Implement equals() in UserModel
     */
    public void deleteUserSave(UserModel user) {
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

    @JsonIgnore
    public List<UserModel> getUserSaves() {
        return userSaves;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }


    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getHotelUrl() {
        return hotelUrl;
    }
}
