package com.bfourclass.euopendata.hotel;

import com.bfourclass.euopendata.hotel.json.HotelJSON;
import com.bfourclass.euopendata.hotel_review.HotelReviewModel;
import com.bfourclass.euopendata.hotel_review.HotelReviewRepository;
import com.bfourclass.euopendata.hotel_review.json.HotelReviewJSON;
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

    public void save(HotelModel hotelModel) {
        hotelRepository.save(hotelModel);
    }

    public void createHotelIfNotExists(HotelModel hotelModel) {
        if (!locationExists(hotelModel.getHotelName())) {
            hotelRepository.save(hotelModel);
        }
    }

    private boolean locationExists(String hotelName) {
        return hotelRepository.getHotelByName(hotelName) != null;
    }

    public List<HotelJSON> getHotels() {
        List<HotelModel> hotelModels = hotelRepository.getAll();
        List<HotelJSON> hotelJSONList = new ArrayList<>();

        for (HotelModel hotelModel : hotelModels) {
            hotelJSONList.add(new HotelJSON(hotelModel.getId(), hotelModel.getIdentifier(), hotelModel.getHotelName(), hotelModel.getLocationName(), hotelModel.getAverageRating(), hotelModel.getVotes()));
        }

        return hotelJSONList;
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

    public void updateHotelReview(HotelModel hotelModel, HotelReviewModel oldReviewModel, HotelReviewJSON request) {
        hotelModel.updateHotelReviewNumber(oldReviewModel.getRating(), true);
        hotelModel.updateHotelReviewNumber(request.userRating, false);

        oldReviewModel.setReviewMessage(request.reviewMessage);
        oldReviewModel.setRating(request.userRating);

        hotelReviewRepository.save(oldReviewModel);
        hotelRepository.save(hotelModel);
    }

    public HotelModel getHotelById(long id) {
        if (hotelRepository.findById(id).isPresent()) {
            return hotelRepository.findById(id).get();
        }

        return null;
    }

    public HotelModel getHotelByIdentifier(String identifier) {
        return hotelRepository.findByIdentifier(identifier);
    }
}
