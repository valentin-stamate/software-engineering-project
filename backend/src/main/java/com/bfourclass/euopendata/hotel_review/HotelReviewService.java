package com.bfourclass.euopendata.hotel_review;

import com.bfourclass.euopendata.hotel.HotelModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelReviewService {

    private final HotelReviewRepository hotelReviewRepository;

    @Autowired
    public HotelReviewService(HotelReviewRepository hotelReviewRepository) {
        this.hotelReviewRepository = hotelReviewRepository;
    }

    public HotelModel getHotelByReviewId(Long reviewId) {
        return hotelReviewRepository.findById(reviewId).isPresent() ?
                hotelReviewRepository.findById(reviewId).get().getHotel() :
                null;
    }
}
