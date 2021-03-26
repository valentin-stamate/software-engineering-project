package com.bfourclass.euopendata.googleApis;

import com.bfourclass.euopendata.weather.Weather;

public class GoogleWeatherClient {

    String apiKey;

    public GoogleWeatherClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
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
