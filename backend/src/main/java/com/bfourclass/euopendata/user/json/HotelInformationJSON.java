package com.bfourclass.euopendata.user.json;

import com.bfourclass.euopendata.external_api.instance.aqicn_data.AirPollution;
import com.bfourclass.euopendata.external_api.instance.covid_information.CovidInformationJSON;
import com.bfourclass.euopendata.external_api.instance.weather.current_weather.Weather;
import com.bfourclass.euopendata.hotel.json.HotelJSON;

public class HotelInformationJSON {
    public final HotelJSON hotelJSON;

    public final Weather weather;
    public final CovidInformationJSON covidInformation;
    public final AirPollution airPollution;

    public HotelInformationJSON(HotelJSON hotelJSON, Weather weather, CovidInformationJSON covidInformation, AirPollution airPollution) {
        this.hotelJSON = hotelJSON;
        this.weather = weather;
        this.covidInformation = covidInformation;
        this.airPollution = airPollution;
    }
}
