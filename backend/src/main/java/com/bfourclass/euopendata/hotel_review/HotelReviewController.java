package com.bfourclass.euopendata.hotel_review;

import com.bfourclass.euopendata.hotel.HotelModel;
import com.bfourclass.euopendata.hotel.HotelService;
import com.bfourclass.euopendata.hotel_review.json.HotelNewReviewJSON;
import com.bfourclass.euopendata.hotel_review.json.HotelReviewJSON;
import com.bfourclass.euopendata.requests.APIError;
import com.bfourclass.euopendata.requests.APISuccess;
import com.bfourclass.euopendata.user.UserModel;
import com.bfourclass.euopendata.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelReviewController {

    private final HotelReviewService hotelReviewService;
    private final HotelService hotelService;
    private final UserService userService;

    @Autowired
    HotelReviewController(HotelReviewService hotelReviewService, HotelService hotelService, UserService userService) {
        this.hotelReviewService = hotelReviewService;
        this.hotelService = hotelService;
        this.userService = userService;
    }

    @GetMapping("hotel/reviews")
    public ResponseEntity<Object> getHotelReviews(@RequestParam(name = "hotel_id") long id) {

        List<HotelReviewJSON> hotelReviews = null;

        HotelModel hotelModel = hotelService.getHotelById(id);

        if (hotelModel != null) {
            hotelReviews = hotelModel.getReviewsAsJSON();
            return new ResponseEntity<>(hotelReviews, HttpStatus.OK);
        }

        return new ResponseEntity<>(new APIError("Hotel not found"), HttpStatus.NOT_FOUND);
    }

    @PostMapping("hotel/add_review")
    public ResponseEntity<Object> addHotelReview(@RequestBody HotelNewReviewJSON request, @RequestHeader(name = "Authorization") String token) {

        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        UserModel userModel = userService.getUserFromToken(token);
        HotelModel hotelModel = hotelService.getHotelById(request.hotelId);

        if (hotelModel == null) {
            return new ResponseEntity<>(new APIError("Hotel doesn't exist"), HttpStatus.NOT_FOUND);
        }

        if (!hotelService.addReview(userModel, hotelModel, request.message, request.rating)) {
            return new ResponseEntity<>(new APIError("Error adding review"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new APISuccess("Review added successfully"), HttpStatus.OK);
    }

    @DeleteMapping("hotel/delete_review")
    public ResponseEntity<Object> deleteHotelReview(@RequestParam(name = "review_id") long reviewId, @RequestHeader(name = "Authorization") String token) {

        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        UserModel userModel = userService.getUserFromToken(token);
        HotelReviewModel hotelReviewModel = hotelReviewService.getHotelReviewById(reviewId);

        if (hotelReviewModel == null) {
            return new ResponseEntity<>(new APIError("This review doesn't exist"), HttpStatus.NOT_FOUND);
        }

        HotelModel hotelModel = hotelReviewModel.getHotel();

        if (!hotelService.removeReview(userModel, hotelModel, hotelReviewModel)) {
            return new ResponseEntity<>(new APIError("Error adding review"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new APISuccess("Review deleted successfully"), HttpStatus.OK);
    }

    @PostMapping("hotel/update_review")
    public ResponseEntity<Object> updateHotelReview(@RequestBody HotelReviewJSON request, @RequestHeader(name = "Authorization") String token) {

        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        UserModel userModel = userService.getUserFromToken(token);
        HotelReviewModel hotelReviewModel = hotelReviewService.getHotelReviewById(request.id);

        if (hotelReviewModel == null) {
            return new ResponseEntity<>(new APIError("This review doesn't exist"), HttpStatus.NOT_FOUND);
        }

        if (!hotelReviewModel.getUser().getId().equals(userModel.getId())) {
            return new ResponseEntity<>(new APIError("This review is not yours"), HttpStatus.NOT_ACCEPTABLE);
        }

        HotelModel hotelModel = hotelReviewModel.getHotel();

        hotelService.updateHotelReview(hotelModel, hotelReviewModel, request);

        return new ResponseEntity<>(new APISuccess("Review updated successfully"), HttpStatus.OK);
    }

}
