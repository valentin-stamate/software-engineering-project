package com.bfourclass.smartbooking.hotel_review;

import com.bfourclass.smartbooking.hotel.HotelModel;
import com.bfourclass.smartbooking.hotel.HotelService;
import com.bfourclass.smartbooking.hotel_review.json.HotelNewReviewJSON;
import com.bfourclass.smartbooking.hotel_review.json.HotelReviewJSON;
import com.bfourclass.smartbooking.hotel_review.json.ReviewResponse;
import com.bfourclass.smartbooking.requests.ResponseError;
import com.bfourclass.smartbooking.requests.ResponseSucces;
import com.bfourclass.smartbooking.user.UserModel;
import com.bfourclass.smartbooking.user.UserService;
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
    public ResponseEntity<Object> getHotelReviews(@RequestParam(name = "hotel_identifier") String identifier) {

        List<HotelReviewJSON> hotelReviews = null;

        HotelModel hotelModel = hotelService.getHotelByIdentifier(identifier);

        if (hotelModel != null) {
            hotelReviews = hotelModel.getReviewsAsJSON();
            return new ResponseEntity<>(hotelReviews, HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseError("Hotel not found"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("review")
    public ResponseEntity<Object> getReview(@RequestParam(name = "id") long id) {

        HotelReviewModel hotelModel = hotelReviewService.getHotelReviewById(id);

        if (hotelModel != null) {
            ReviewResponse reviewResponse = new ReviewResponse(hotelModel.getId(), hotelModel.getHotel().getId(), hotelModel.getRating(), hotelModel.getReviewMessage(), hotelModel.getReviewDate());
            return new ResponseEntity<>(reviewResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseError("Hotel not found"), HttpStatus.NOT_FOUND);
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
            return new ResponseEntity<>(new ResponseError("Hotel doesn't exist"), HttpStatus.NOT_FOUND);
        }

        if (!hotelService.addReview(userModel, hotelModel, request.message, request.rating)) {
            return new ResponseEntity<>(new ResponseError("Error adding review"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new ResponseSucces("Review added successfully"), HttpStatus.OK);
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
            return new ResponseEntity<>(new ResponseError("This review doesn't exist"), HttpStatus.NOT_FOUND);
        }

        HotelModel hotelModel = hotelReviewModel.getHotel();

        if (!hotelService.removeReview(userModel, hotelModel, hotelReviewModel)) {
            return new ResponseEntity<>(new ResponseError("Error adding review"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new ResponseSucces("Review deleted successfully"), HttpStatus.OK);
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
            return new ResponseEntity<>(new ResponseError("This review doesn't exist"), HttpStatus.NOT_FOUND);
        }

        if (!hotelReviewModel.getUser().getId().equals(userModel.getId())) {
            return new ResponseEntity<>(new ResponseError("This review is not yours"), HttpStatus.NOT_ACCEPTABLE);
        }

        HotelModel hotelModel = hotelReviewModel.getHotel();

        hotelService.updateHotelReview(hotelModel, hotelReviewModel, request);

        return new ResponseEntity<>(new ResponseSucces("Review updated successfully"), HttpStatus.OK);
    }

}
