package com.bfourclass.euopendata.external_api;

import com.bfourclass.euopendata.external_api.instance.aqicn_data.AirPollution;
import com.bfourclass.euopendata.external_api.instance.covid_information.CovidInformation;
import com.bfourclass.euopendata.external_api.instance.weather.Weather;

import java.io.IOException;

public abstract class ExternalAPI {

    public static Weather getWeather(String location) {
        return OpenWeatherAPI.requestWeather(location);
    }

    public static CovidInformation getCovidInformation(String location) {
        /* TODO */
        return new CovidInformation("API still in development");
    }
    
    public static AirPollution getAirPollution(String location) {
        try {
            return AQICNDataAPI.requestAirPollution(location);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}