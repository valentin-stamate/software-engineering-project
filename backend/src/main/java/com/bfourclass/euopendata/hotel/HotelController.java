package com.bfourclass.euopendata.hotel;

import com.bfourclass.euopendata.external_api.ExternalAPI;
import com.bfourclass.euopendata.external_api.instance.covid_news.SearchResultJSON;
import com.bfourclass.euopendata.hotel.json.AddHotelJsonRequest;
import com.bfourclass.euopendata.hotel.json.HotelJSON;
import com.bfourclass.euopendata.hotel.json.HotelSearchResultJSON;
import com.bfourclass.euopendata.notification.NotificationModel;
import com.bfourclass.euopendata.notification.NotificationService;
import com.bfourclass.euopendata.requests.ResponseError;
import com.bfourclass.euopendata.requests.ResponseSucces;
import com.bfourclass.euopendata.user.UserModel;
import com.bfourclass.euopendata.user.UserService;
import com.bfourclass.euopendata.user.forms.FormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class HotelController {

    private final HotelService hotelService;
    private final UserService userService;
    private final NotificationService notificationService;

    @Autowired
    HotelController(HotelService hotelService, UserService userService, NotificationService notificationService) {
        this.hotelService = hotelService;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    @GetMapping("hotel/all_hotels")
    public ResponseEntity<Object> getLocation() {
        List<HotelJSON> hotels = hotelService.getHotels();

        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @GetMapping("/search_hotel")
    public ResponseEntity<Object> searchHotel(@RequestParam(name = "query") String query, @RequestHeader(name = "Authorization", required = false) String token) {
        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);

        if (errorResponse == null) {
            UserModel userModel = userService.getUserFromToken(token);
            userService.addUserSearchHistory(userModel, query);
        }

        /* TODO, make this clearer and refactor*/

        List<HotelJSON> hotelJSONS = hotelService.getHotels();

        query = query.toLowerCase(Locale.ROOT);
        String[] searchTokens = query.split(" ");

        Map<String, HotelJSON> searchResult = new HashMap<>();

        for (HotelJSON hotelJSON : hotelJSONS) {
            searchResult.put(hotelJSON.hotelName.toLowerCase(Locale.ROOT) + " " + hotelJSON.locationName.toLowerCase(Locale.ROOT), hotelJSON);
        }

        for (String searchToken : searchTokens) {
            searchResult = searchResult.entrySet().stream().
                    filter(map -> map.getKey().contains(searchToken))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        List<HotelJSON> dbResult = new ArrayList<>(searchResult.values());
        List<SearchResultJSON> webResult = ExternalAPI.getGoogleSearchResults(query + " booking.com", 10);

        HotelSearchResultJSON hotelSearchResultJSON = new HotelSearchResultJSON(dbResult, webResult);

        return new ResponseEntity<>(hotelSearchResultJSON, HttpStatus.OK);
    }

    @PostMapping("hotel/add_hotel")
    public ResponseEntity<Object> addHotel(@RequestBody AddHotelJsonRequest request, @RequestHeader(name = "Authorization") String token) {
        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        if (!FormValidator.isValidHotelAddForm(request)) {
            return new ResponseEntity<>(new ResponseError("invalid form"), HttpStatus.BAD_REQUEST);
        }

        UserModel user = userService.getUserFromToken(token);
        if (!user.isOwner()) {
            return new ResponseEntity<>(new ResponseError("not a hotel owner"), HttpStatus.UNAUTHORIZED);
        }
        HotelModel hotel = new HotelModel(request.getIdentifier(), request.getName(), request.getLocation(), request.getPhotoLink(), request.getDescription(), request.getPrice(), user.getId());
        hotelService.save(hotel);
        return new ResponseEntity<>(new ResponseSucces("added hotel successfully"), HttpStatus.OK);
    }

    /*TODO implement update hotel endpoint*/
    @PutMapping("hotel/update_hotel")
    public ResponseEntity<Object> updateHotel(@RequestBody HotelJSON hotelJSON, @RequestHeader(name = "Authorization") String token) {
        HotelModel hotelModel = hotelService.getHotelById(hotelJSON.id);
        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        for (UserModel user : hotelModel.getUserSaves()) {
            NotificationModel notification = new NotificationModel("Hotel " + hotelJSON.hotelName + " was updated");
            notificationService.save(notification);

            notificationService.addUserNotification(user, notification);
        }

        hotelModel.setHotelName(hotelJSON.hotelName);
        hotelModel.setLocationName(hotelJSON.locationName);
        hotelModel.setAverageRating(hotelJSON.averageRating);
        hotelModel.setVotes(hotelJSON.votes);
        hotelModel.setPrice(hotelJSON.price);
        hotelModel.setDescription(hotelJSON.description);
        hotelModel.setPhotoLink(hotelJSON.photoLink);
        hotelService.save(hotelModel);

        return new ResponseEntity<>(new ResponseSucces("hotel updated successfully"), HttpStatus.OK);
    }
    /* TODO Hotel Information */

    /*TODO implement delete hotel endpoint*/
    @DeleteMapping("hotel/delete_hotel")
    public ResponseEntity<Object> deleteHotel(@RequestBody HotelJSON hotelJSON, @RequestHeader(name = "Authorization") String token){
        HotelModel hotelModel = hotelService.getHotelById(hotelJSON.id);
        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }
        hotelService.delete(hotelModel);

        return new ResponseEntity<>(new ResponseSucces("hotel deleted successfully"), HttpStatus.OK);
    }


}
