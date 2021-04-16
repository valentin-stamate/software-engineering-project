package com.bfourclass.euopendata.hotel;

import com.bfourclass.euopendata.hotel.json.HotelJSONRequest;
import com.bfourclass.euopendata.hotel_review.HotelReviewModel;
import com.bfourclass.euopendata.hotel_review.HotelReviewRepository;
import com.bfourclass.euopendata.hotel_review.json.HotelReviewJSONUpdateRequest;
import com.bfourclass.euopendata.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelReviewRepository hotelReviewRepository;

    @Autowired
    HotelService(HotelRepository hotelRepository, HotelReviewRepository hotelReviewRepository) {
        this.hotelRepository = hotelRepository;
        this.hotelReviewRepository = hotelReviewRepository;
    }

    public void createHotelIfNotExists(HotelModel hotelModel) {
        if (!locationExists(hotelModel.getHotelName())) {
            hotelRepository.save(hotelModel);
        }
    }

    private boolean locationExists(String hotelName) {
        return hotelRepository.getHotelByName(hotelName) != null;
    }

    public HotelModel getHotelByName(String hotelName) {
        return hotelRepository.getHotelByName(hotelName);
    }

    public boolean addReview(UserModel userModel, HotelModel hotelModel, String requestMessage, int rating) {
        if (requestMessage.length() < 10 || rating == 0)
            return false;
        if (userModel.hasAlreadyReviewedHotel(hotelModel))
            return false;
        HotelReviewModel hotelReview = new HotelReviewModel(rating, requestMessage, LocalDateTime.now().toString(), userModel, hotelModel);
        hotelModel.updateHotelRating(hotelReview);
        hotelReviewRepository.save(hotelReview);
        return true;
    }

    public void deleteHotelReview(HotelModel hotelModel, Long reviewId) {
        hotelModel.deleteReviewById(reviewId);
    }

    public void updateHotelReview(HotelModel hotelModel, Long reviewId, HotelReviewJSONUpdateRequest request) {
        hotelModel.updateHotelReview(reviewId, request);
    }

}
