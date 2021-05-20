package com.bfourclass.euopendata.external_api;

import com.bfourclass.euopendata.external_api.covid.*;
import com.bfourclass.euopendata.external_api.instance.covid_news.SearchResultJSON;
import com.bfourclass.euopendata.external_api.instance.covid_statistics.CovidStatistics;
import com.bfourclass.euopendata.external_api.instance.numbeo_data.*;
import com.bfourclass.euopendata.external_api.instance.weather.current_weather.Weather;
import com.bfourclass.euopendata.external_api.instance.weather.Forecast;
import com.bfourclass.euopendata.external_api.instance.weather.statistical_weather.StatisticalWeather;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
            return null;
        }
    }

    public static List<SearchResultJSON> getGoogleSearchResults(String query, int results) {
        return GoogleSearchAPI.fetchGoogleSearchResults(query, results);
    }

    public static CovidStatistics getCovidStatistics(String country) {
        return CovidStatisticsAPI.getCovidStatistics(country);
    }

    public static Forecast getForecast(String location) {
        return OpenWeatherAPI.requestForecast(location);
    }

    public static StatisticalWeather getStatisticWeather(String location, LocalDate date) {

        List<StatisticalWeather> statisticalWeatherList = new ArrayList<>();

        statisticalWeatherList.add(StatisticalWeatherAPI.getStatisticalWeather(location, date.minusYears(1)));
        statisticalWeatherList.add(StatisticalWeatherAPI.getStatisticalWeather(location, date.minusYears(2)));
        statisticalWeatherList.add(StatisticalWeatherAPI.getStatisticalWeather(location, date.minusYears(3)));

        return StatisticalWeather.mean(statisticalWeatherList);
    }

    public static PollutionStatistics getPollutionStatistics(String location) {
        try {
            PollutionStatistics pollutionStatistics = NumbeoAPI.requestPollutionStatistics(location);
            pollutionStatistics = AQICNDataAPI.requestAirPollution(location, pollutionStatistics);
            return pollutionStatistics;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static RestaurantsStatistics getRestaurantsStatistics(String location) {
        try {
            return NumbeoAPI.requestRestaurantStatistics(location);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static CostOfLivingStatistics getCostOfLivingStatistics(String location) {
        try {
            return NumbeoAPI.requestCostOfLivingStatistics(location);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}