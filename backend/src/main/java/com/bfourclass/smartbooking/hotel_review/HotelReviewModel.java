package com.bfourclass.smartbooking.hotel_review;

import com.bfourclass.smartbooking.hotel.HotelModel;
import com.bfourclass.smartbooking.user.UserModel;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "hotel_reviews")
public class HotelReviewModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private UserModel user;

    @ManyToOne
    private HotelModel hotel;

    private int rating = 0;
    private String reviewMessage = "";
    private String reviewDate;

    public HotelReviewModel(UserModel user, HotelModel hotelModel, String reviewMessage, int rating) {
        this.rating = rating;
        this.reviewMessage = reviewMessage;
        this.reviewDate = new Timestamp((new Date()).getTime()).toString();
        this.user = user;
        this.hotel = hotelModel;

        user.addHotelReview(this);
        hotelModel.addHotelReview(this);
    }

    public HotelReviewModel() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public HotelModel getHotel() {
        return hotel;
    }

    public void setHotel(HotelModel hotel) {
        this.hotel = hotel;
    }

    public int getRating() {
        return rating;
    }

    public String getReviewMessage() {
        return reviewMessage;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setReviewMessage(String reviewMessage) {
        this.reviewMessage = reviewMessage;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }
}
