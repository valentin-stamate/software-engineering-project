package com.bfourclass.euopendata.external_api.instance.location;

import com.bfourclass.euopendata.external_api.instance.covid_news.SearchResultJSON;
import com.bfourclass.euopendata.external_api.instance.covid_statistics.CovidStatistics;
import com.bfourclass.euopendata.external_api.instance.numbeo_data.CriminalityStatistics;
import com.bfourclass.euopendata.external_api.instance.numbeo_data.PollutionStatistics;
import com.bfourclass.euopendata.external_api.instance.weather.current_weather.Weather;
import com.bfourclass.euopendata.hotel.json.HotelJSON;

import java.util.List;

public class LocationStatisticsJSON {
    public List<HotelJSON> hotels;
    public Weather weather;
    public CovidStatistics covidStatistics;
    public SearchResultJSON covidNews;
    public CriminalityStatistics criminalityStatistics;
    public PollutionStatistics pollutionStatistics;

    public LocationStatisticsJSON(List<HotelJSON> hotels, Weather weather, CovidStatistics covidStatistics, SearchResultJSON covidNews,
                                  CriminalityStatistics criminalityStatistics, PollutionStatistics pollutionStatistics) {
        this.hotels = hotels;
        this.weather = weather;
        this.covidStatistics = covidStatistics;
        this.covidNews = covidNews;
        this.criminalityStatistics = criminalityStatistics;
        this.pollutionStatistics = pollutionStatistics;
    }
}
