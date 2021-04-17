package com.bfourclass.euopendata.hotel_review.json;

public class HotelReviewJSON {
    public Long id;
    public String userName;
    public int userRating;
    public String reviewMessage;
    public String reviewDate;

    public HotelReviewJSON(Long id, String userName, int userRating, String reviewMessage, String reviewDate) {
        this.id = id;
        this.userName = userName;
        this.userRating = userRating;
        this.reviewMessage = reviewMessage;
        this.reviewDate = reviewDate;
    }
}
