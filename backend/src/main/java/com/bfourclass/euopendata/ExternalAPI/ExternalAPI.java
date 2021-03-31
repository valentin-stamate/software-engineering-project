package com.bfourclass.euopendata.ExternalAPI;

import com.bfourclass.euopendata.ExternalAPI.instance.weather.Weather;

public abstract class ExternalAPI {

    public static Weather getWeather(String location) {
        return OpenWeatherAPI.requestWeather(location);
    }

}