package com.bfourclass.euopendata.ExternalAPI;

import com.bfourclass.euopendata.ExternalAPI.instance.aqicn_data.AirPollution;
import com.bfourclass.euopendata.ExternalAPI.instance.weather.Weather;

import java.io.IOException;

public abstract class ExternalAPI {

    public static Weather getWeather(String location) {
        return OpenWeatherAPI.requestWeather(location);
    }
    
    public static AirPollution getAirPollution(String location) throws IOException {
        return AQICNDataAPI.requestAirPollution(location);
    }
}