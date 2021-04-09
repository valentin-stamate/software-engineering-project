package com.bfourclass.euopendata.external_api;

import com.bfourclass.euopendata.external_api.instance.aqicn_data.AirPollution;
import com.bfourclass.euopendata.external_api.instance.weather.Weather;

import java.io.IOException;

public abstract class ExternalAPI {

    public static Weather getWeather(String location) {
        return OpenWeatherAPI.requestWeather(location);
    }
    
    public static AirPollution getAirPollution(String location) throws IOException {
        return AQICNDataAPI.requestAirPollution(location);
    }

}