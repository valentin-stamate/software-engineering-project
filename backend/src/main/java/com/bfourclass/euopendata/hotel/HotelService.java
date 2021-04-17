package com.bfourclass.euopendata.hotel;

import com.bfourclass.euopendata.hotel.json.HotelJSON;
import com.bfourclass.euopendata.hotel_review.HotelReviewModel;
import com.bfourclass.euopendata.hotel_review.HotelReviewRepository;
import com.bfourclass.euopendata.hotel_review.json.HotelReviewJSONUpdateRequest;
import com.bfourclass.euopendata.user.UserModel;
import com.bfourclass.euopendata.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {

    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final HotelReviewRepository hotelReviewRepository;

    @Autowired
    HotelService(UserRepository userRepository, HotelRepository hotelRepository, HotelReviewRepository hotelReviewRepository) {
        this.userRepository = userRepository;
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

    public boolean addReview(UserModel userModel, HotelModel hotelModel, String message, int rating) {

        if (message.length() < 10 || rating == 0) {
            return false;
        }

        if (userModel.hasAlreadyReviewedHotel(hotelModel)) {
            return false;
        }

        HotelReviewModel hotelReviewModel = new HotelReviewModel(userModel, hotelModel, message, rating);

        hotelReviewRepository.save(hotelReviewModel);
        userRepository.save(userModel);
        hotelRepository.save(hotelModel);
        return true;
    }

    public boolean removeReview(UserModel userModel, HotelModel hotelModel, HotelReviewModel hotelReviewModel) {
        userModel.removeHotelReview(hotelReviewModel);
        hotelModel.removeHotelReview(hotelReviewModel);

        hotelReviewRepository.delete(hotelReviewModel);

        userRepository.save(userModel);
        hotelRepository.save(hotelModel);
        return true;
    }

    public List<HotelJSON> getHotels() {
        List<HotelModel> hotelModels = hotelRepository.getAll();
        List<HotelJSON> hotelJSONList = new ArrayList<>();

        for (HotelModel hotelModel : hotelModels) {
            hotelJSONList.add(new HotelJSON(hotelModel.getHotelName(), hotelModel.getLocationName(), hotelModel.getAverageRating(), hotelModel.getVotes()));
        }

        return hotelJSONList;
    }

    public void updateHotelReview(HotelModel hotelModel, HotelReviewModel oldReviewModel, HotelReviewJSONUpdateRequest request) {
        hotelModel.updateHotelReviewNumber(oldReviewModel.getRating(), true);
        hotelModel.updateHotelReviewNumber(request.rating, false);

        hotelReviewRepository.save(oldReviewModel);
        hotelRepository.save(hotelModel);
    }
}
