package com.bfourclass.euopendata.googleApis;

import com.bfourclass.euopendata.weather.Weather;

public class GoogleWeatherClient {

    private final String apiKey;
    private final String locationName;
    private final double weather;

    public GoogleWeatherClient(String apiKey, String locationName, double weather) {
        this.apiKey = apiKey;
        this.locationName = locationName;
        this.weather = weather;
    }

    public String getApiKey() {
        return apiKey;
    }

    public Weather requestWeather(String city){
        return new Weather();
    }

    public Weather requestWeather(String city, String date){
        return new Weather();
    }

    @Override
    public String toString() {
        return "GoogleWeatherClient{" +
                "apiKey='" + apiKey + '\'' +
                '}';
    }
}
