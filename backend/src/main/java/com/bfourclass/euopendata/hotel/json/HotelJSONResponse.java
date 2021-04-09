package com.bfourclass.euopendata.hotel.json;

import com.bfourclass.euopendata.external_api.instance.aqicn_data.AirPollution;
import com.bfourclass.euopendata.external_api.instance.covid_restrictions.CovidRestrictions;
import com.bfourclass.euopendata.external_api.instance.weather.Weather;

public class HotelJSONResponse {
    public final String hotelName;
    public final String locationName;

    public final Weather weather;
    public final CovidRestrictions covidRestrictions;
    public final AirPollution airPollution;

    public HotelJSONResponse(String hotelName, String locationName, Weather weather, CovidRestrictions covidRestrictions, AirPollution airPollution) {
        this.hotelName = hotelName;
        this.locationName = locationName;
        this.weather = weather;
        this.covidRestrictions = covidRestrictions;
        this.airPollution = airPollution;
    }
}
