package com.bfourclass.euopendata.hotel;

import com.bfourclass.euopendata.external_api.ExternalAPI;
import com.bfourclass.euopendata.external_api.instance.covid_news.SearchResultJSON;
import com.bfourclass.euopendata.hotel.json.HotelJSON;
import com.bfourclass.euopendata.hotel.json.HotelSearchResultJSON;
import com.bfourclass.euopendata.user.UserModel;
import com.bfourclass.euopendata.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class HotelController {

    private final HotelService hotelService;
    private final UserService userService;

    @Autowired
    HotelController(HotelService hotelService, UserService userService) {
        this.hotelService = hotelService;
        this.userService = userService;
    }

    @GetMapping("hotel/all_hotels")
    public ResponseEntity<Object> getLocation(){
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

}
