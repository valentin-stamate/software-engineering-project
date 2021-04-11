package com.bfourclass.euopendata.hotel_review;

import com.bfourclass.euopendata.hotel.HotelModel;
import com.bfourclass.euopendata.hotel.HotelService;
import com.bfourclass.euopendata.hotel.json.HotelJSONRequest;
import com.bfourclass.euopendata.hotel_review.json.HotelReviewJSONResponse;
import com.bfourclass.euopendata.hotel_review.json.HotelReviewJSONUpdateRequest;
import com.bfourclass.euopendata.requests.APIError;
import com.bfourclass.euopendata.requests.APISuccess;
import com.bfourclass.euopendata.user.UserModel;
import com.bfourclass.euopendata.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
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
    public ResponseEntity<Object> addLocationToUser(@RequestBody String hotelName) {

        List<HotelReviewJSONResponse> hotelReviews = new ArrayList<>();

        HotelModel hotelModel = hotelService.getHotelByName(hotelName);

        if (hotelModel != null) {
            hotelModel.getReviews();
        }

        return new ResponseEntity<>(hotelReviews, HttpStatus.OK);
    }

    @PostMapping("hotel/add_review")
    public ResponseEntity<Object> addLocationToUser(@RequestBody HotelJSONRequest hotelJSONRequest, @RequestHeader(name = "Authorization", required = false) String token) {

        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        UserModel userModel = userService.getUserFromToken(token);
        HotelModel hotelModel = hotelService.getHotelByName(hotelJSONRequest.hotelName);

        if (hotelModel == null) {
            return new ResponseEntity<>(new APIError("Hotel doesn't exist"), HttpStatus.NOT_FOUND);
        }

        /* TODO - Valentin, use hotelReview and hotel service to add a review */

        return new ResponseEntity<>(new APISuccess("Review added successfully"), HttpStatus.OK);
    }

    @DeleteMapping("hotel/delete_review")
    public ResponseEntity<Object> addLocationToUser(@RequestBody Long reviewId, @RequestHeader(name = "Authorization", required = false) String token) {

        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        UserModel userModel = userService.getUserFromToken(token);
        HotelModel hotelModel = hotelReviewService.getHotelByReviewId(reviewId);

        if (hotelModel == null) {
            return new ResponseEntity<>(new APIError("Hotel doesn't exist"), HttpStatus.NOT_FOUND);
        }

        hotelModel.removeReview(reviewId);
        /* TODO - Valentin, delete the hotelModel and see if the review still remains in the table */

        /* TODO - Valentin, use hotelReview and hotel service to add a review */

        return new ResponseEntity<>(new APISuccess("Review deleted successfully"), HttpStatus.OK);
    }

    @PostMapping("hotel/update_review")
    public ResponseEntity<Object> addLocationToUser(@RequestBody HotelReviewJSONUpdateRequest request, @RequestHeader(name = "Authorization", required = false) String token) {

        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        UserModel userModel = userService.getUserFromToken(token);
        HotelModel hotelModel = hotelReviewService.getHotelByReviewId(request.reviewId);

        if (hotelModel == null) {
            return new ResponseEntity<>(new APIError("Hotel doesn't exist"), HttpStatus.NOT_FOUND);
        }

        /* TODO - Valentin, update the hotelModel and see if the review still remains in the table */

        return new ResponseEntity<>(new APISuccess("Review updated successfully"), HttpStatus.OK);
    }

}
