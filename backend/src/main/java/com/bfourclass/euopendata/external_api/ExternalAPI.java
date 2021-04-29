package com.bfourclass.euopendata.external_api;

import com.bfourclass.euopendata.external_api.covid.GoogleSearchAPI;
import com.bfourclass.euopendata.external_api.covid.CovidStatisticsAPI;
import com.bfourclass.euopendata.external_api.instance.aqicn_data.AirPollution;
import com.bfourclass.euopendata.external_api.instance.covid_news.SearchResultJSON;
import com.bfourclass.euopendata.external_api.instance.covid_statistics.CovidStatistics;
import com.bfourclass.euopendata.external_api.instance.numbeo_data.CriminalityStatistics;
import com.bfourclass.euopendata.external_api.instance.weather.current_weather.Weather;
import com.bfourclass.euopendata.external_api.instance.weather.week_weather.Forecast;

import java.io.IOException;
import java.util.List;

public abstract class ExternalAPI {

    public static Weather getWeather(String location) {
        return OpenWeatherAPI.requestWeather(location);
    }

    public static CriminalityStatistics getCriminalityStatistics(String location) {
        try {
            return NumbeoAPI.requestCriminalityStatistics(location);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<SearchResultJSON> getGoogleSearchResults(String query, int results) {
        return GoogleSearchAPI.fetchGoogleSearchResults(query, results);
    }

    public static CovidStatistics getCovidStatistics(String country) {
        return CovidStatisticsAPI.getCovidStatistics(country);
    }

    public static AirPollution getAirPollution(String location) {
        try {
            return AQICNDataAPI.requestAirPollution(location);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Forecast getForecast(String location) {
        return OpenWeatherAPI.requestForecast(location);
    }

}