package com.bfourclass.euopendata.user.json;

import com.bfourclass.euopendata.external_api.instance.aqicn_data.AirPollution;
import com.bfourclass.euopendata.external_api.instance.covid_information.CovidInformation;
import com.bfourclass.euopendata.external_api.instance.weather.Weather;
import com.bfourclass.euopendata.hotel.json.Hotel;

public class HotelInformationJSON {
    public final Hotel hotel;

    public final Weather weather;
    public final CovidInformation covidInformation;
    public final AirPollution airPollution;

    public HotelInformationJSON(Hotel hotel, Weather weather, CovidInformation covidInformation, AirPollution airPollution) {
        this.hotel = hotel;
        this.weather = weather;
        this.covidInformation = covidInformation;
        this.airPollution = airPollution;
    }
}
