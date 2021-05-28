package com.bfourclass.smartbooking.hotel_review.json;

public class HotelReviewJSON {
    public long id;
    public String userName;
    public int userRating;
    public String reviewMessage;
    public String reviewDate;

    public HotelReviewJSON(long id, String userName, int userRating, String reviewMessage, String reviewDate) {
        this.id = id;
        this.userName = userName;
        this.userRating = userRating;
        this.reviewMessage = reviewMessage;
        this.reviewDate = reviewDate;
    }
}
