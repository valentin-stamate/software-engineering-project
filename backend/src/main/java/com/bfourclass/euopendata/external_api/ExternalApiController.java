package com.bfourclass.euopendata.external_api;

import com.bfourclass.euopendata.external_api.instance.covid_statistics.CovidStatistics;
import com.bfourclass.euopendata.external_api.instance.weather.week_weather.Forecast;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ExternalApiController {

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

}
