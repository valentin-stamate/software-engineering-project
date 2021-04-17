package com.bfourclass.euopendata.external_api;

import com.bfourclass.euopendata.external_api.instance.aqicn_data.AirPollution;
import com.bfourclass.euopendata.external_api.instance.covid_information.CovidInformationJSON;
import com.bfourclass.euopendata.external_api.instance.covid_information.Item;
import com.bfourclass.euopendata.external_api.instance.numbeo_data.CriminalityStatistics;
import com.bfourclass.euopendata.external_api.instance.weather.Weather;

import java.io.IOException;

public abstract class ExternalAPI {

    public static Weather getWeather(String location) {
        return OpenWeatherAPI.requestWeather(location);
    }

    public static CovidInformationJSON getCovidInformation(String location) {
        return CovidInformationAPI.requestCovidInformation(location);
    }
    
    public static AirPollution getAirPollution(String location) {
        try {
            return AQICNDataAPI.requestAirPollution(location);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static CriminalityStatistics getCriminalityStatistics(String location) {
        try {
            return NumbeoAPI.requestCriminalityStatistics(location);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}