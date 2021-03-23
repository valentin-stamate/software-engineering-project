package com.bfourclass.euopendata.googleApis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Entity
@Table(name = "weather")
public class GoogleWeatherClient extends DataAggregatorComponent{

    @Entity
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

    public Weather requestWeather( String city){
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
