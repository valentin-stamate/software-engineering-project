package com.bfourclass.euopendata.hotel_review;

import com.bfourclass.euopendata.hotel.HotelModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelReviewService {

    private final HotelReviewRepository hotelReviewRepository;

    @Autowired
    public HotelReviewService(HotelReviewRepository hotelReviewRepository) {
        this.hotelReviewRepository = hotelReviewRepository;
    }

    public HotelReviewModel getHotelReviewById(long reviewId) {
        Optional<HotelReviewModel> hotelModelOptional = hotelReviewRepository.findById(reviewId);

        return hotelModelOptional.orElse(null);

    }

}
