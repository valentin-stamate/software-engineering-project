package com.bfourclass.euopendata.external_api;

import com.bfourclass.euopendata.external_api.instance.aqicn_data.AirPollution;
import com.bfourclass.euopendata.external_api.instance.covid_news.CovidNewsJSON;
import com.bfourclass.euopendata.external_api.instance.covid_statistics.CovidStatistics;
import com.bfourclass.euopendata.external_api.instance.location.LocationStatisticsJSON;
import com.bfourclass.euopendata.external_api.instance.numbeo_data.CriminalityStatistics;
import com.bfourclass.euopendata.external_api.instance.weather.current_weather.Weather;
import com.bfourclass.euopendata.external_api.instance.weather.week_weather.Forecast;
import com.bfourclass.euopendata.hotel.HotelService;
import com.bfourclass.euopendata.hotel.json.HotelJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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
        CovidNewsJSON covidNewsJSON = ExternalAPI.getCovidNews(location);
        AirPollution airPollution = ExternalAPI.getAirPollution(location);
        CriminalityStatistics criminalityStatistics=ExternalAPI.getCriminalityStatistics(location);
        LocationStatisticsJSON locationStatistics = new LocationStatisticsJSON(hotels, weather, covidStatistics,covidNewsJSON,airPollution,criminalityStatistics);

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
    public ResponseEntity<Object> getLocationCovidStatistics(@RequestParam(name = "countries") String countriesString) {
        String[] countries = countriesString.split(",");

        List<CovidStatistics> covidStatistics = new ArrayList<>();

        for (String country : countries) {
            covidStatistics.add(ExternalAPI.getCovidStatistics(country));
        }

        return new ResponseEntity<>(covidStatistics, HttpStatus.OK);
    }

    /* TODO, location data */
    /* TODO add current weather */
}
