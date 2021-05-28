package com.bfourclass.smartbooking.hotel_review.json;

public class ReviewResponse {
    public long id;
    public long hotelId;
    public int rating;
    public String reviewMessage;
    public String reviewDate;

    public ReviewResponse(long id, long hotelId, int rating, String reviewMessage, String reviewDate) {
        this.id = id;
        this.hotelId = hotelId;
        this.rating = rating;
        this.reviewMessage = reviewMessage;
        this.reviewDate = reviewDate;
    }
}
