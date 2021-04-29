package com.bfourclass.euopendata.external_api;

import com.bfourclass.euopendata.external_api.instance.aqicn_data.AirPollution;
import com.bfourclass.euopendata.external_api.instance.covid_news.CovidNews;
import com.bfourclass.euopendata.external_api.instance.covid_news.SearchResultJSON;
import com.bfourclass.euopendata.external_api.instance.covid_statistics.CovidStatistics;
import com.bfourclass.euopendata.external_api.instance.location.LocationStatisticsJSON;
import com.bfourclass.euopendata.external_api.instance.numbeo_data.CriminalityStatistics;
import com.bfourclass.euopendata.external_api.instance.weather.current_weather.Weather;
import com.bfourclass.euopendata.external_api.instance.weather.week_weather.Forecast;
import com.bfourclass.euopendata.external_api.json.CovidNewsJSON;
import com.bfourclass.euopendata.hotel.HotelService;
import com.bfourclass.euopendata.hotel.json.HotelJSON;
import com.bfourclass.euopendata.requests.APIError;
import com.bfourclass.euopendata.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ExternalApiController {

    private final HotelService hotelService;

    @Autowired
    public ExternalApiController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/location_statistics")
    public ResponseEntity<Object> getLocationStatistics(@RequestParam(name = "location") String location,@RequestParam(name="country") String country) {
        Weather weather = ExternalAPI.getWeather(location);
        CovidStatistics covidStatistics = ExternalAPI.getCovidStatistics(country);
        List<HotelJSON> hotels = hotelService.getHotels();

        List<SearchResultJSON> results = ExternalAPI.getGoogleSearchResults(location + " covid", CovidNewsJSON.MAX_RESULTS);

        SearchResultJSON searchResultJSON = null;

        if (results.size() != 0) {
            searchResultJSON = results.get(0);
        }

        AirPollution airPollution = ExternalAPI.getAirPollution(location);
        CriminalityStatistics criminalityStatistics=ExternalAPI.getCriminalityStatistics(location);
        LocationStatisticsJSON locationStatistics = new LocationStatisticsJSON(hotels, weather, covidStatistics, searchResultJSON,airPollution,criminalityStatistics);

        return new ResponseEntity<>(locationStatistics, HttpStatus.OK);
    }

    @GetMapping("/forecast")
    public ResponseEntity<Object> getLocationForecast(@RequestParam(name = "locations") String locationString) {
        String[] locations = locationString.split(",");

        List<Forecast> forecasts = new ArrayList<>();

        for (String location : locations) {
            forecasts.add(ExternalAPI.getForecast(location));
        }

        return new ResponseEntity<>(forecasts, HttpStatus.OK);
    }

    @GetMapping("/covid_statistics")
    public ResponseEntity<Object> getLocationCovidStatistics(@RequestParam(name = "countries") String countriesString, @RequestParam(required = false) String start, @RequestParam(required = false) String end) {
        String[] countries = countriesString.split(",");
        List<CovidStatistics> covidStatistics = new ArrayList<>();

        for (String country : countries) {
            covidStatistics.add(ExternalAPI.getCovidStatistics(country));
        }

        Date startDate;
        Date endDate;

        try {
            if (start == null) {
                start = "2020-02-24";
            }

            if (end == null) {
                end = Util.currentDate();
            }

            startDate = Util.toDate(start);
            endDate = Util.toDate(end);
        } catch (ParseException e) {
            return new ResponseEntity<>(new APIError("Invalid date format"), HttpStatus.BAD_REQUEST);
        }

        for (CovidStatistics covidInfo : covidStatistics) {
            covidInfo.items = covidInfo.items.stream().filter(item -> {
                try {
                    Date covidDate = Util.toDate(item.date);

                    return startDate.compareTo(covidDate) <= 0 && covidDate.compareTo(endDate) <= 0;

                } catch (ParseException e) { }
                return false;
            }).collect(Collectors.toList());
        }

        return new ResponseEntity<>(covidStatistics, HttpStatus.OK);
    }

    @GetMapping("/criminality_statistics")
    public ResponseEntity<Object> getLocationCriminalityStatistics(@RequestParam(name = "locations") String locationsString) {
        String[] locations = locationsString.split(",");

        List<CriminalityStatistics> criminalityStatistics = new ArrayList<>();

        for (String location : locations) {
            criminalityStatistics.add(ExternalAPI.getCriminalityStatistics(location));
        }

        return new ResponseEntity<>(criminalityStatistics, HttpStatus.OK);
    }

    @GetMapping("/covid_news")
    public ResponseEntity<Object> getLocationCovidNews(@RequestParam(name = "locations") String locationsString, @RequestParam(name = "max_results", required = false) Integer maxResults) {
        String[] locations = locationsString.split(",");

        List<CovidNewsJSON> covidNewsList = new ArrayList<>();

        int maxFetchResults = maxResults == null ? CovidNewsJSON.MAX_RESULTS : maxResults;

        for (String location : locations) {
            List<SearchResultJSON> results = ExternalAPI.getGoogleSearchResults(location + " covid", maxFetchResults);
            covidNewsList.add(new CovidNewsJSON(location, results));
        }

        return new ResponseEntity<>(covidNewsList, HttpStatus.OK);
    }

    @GetMapping("/air_pollution")
    public ResponseEntity<Object> getLocationAirPollution(@RequestParam(name = "locations") String locationsString) {
        String[] locations = locationsString.split(",");

        List<AirPollution> airPollutions = new ArrayList<>();

        for (String location : locations) {
            airPollutions.add(ExternalAPI.getAirPollution(location));
        }

        return new ResponseEntity<>(airPollutions, HttpStatus.OK);
    }

    /* TODO, location data */
    /* TODO add current weather */
}
