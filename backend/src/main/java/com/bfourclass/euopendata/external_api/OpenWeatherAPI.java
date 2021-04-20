package com.bfourclass.euopendata.external_api;

import com.bfourclass.euopendata.external_api.instance.weather.current_weather.Weather;
import com.bfourclass.euopendata.external_api.instance.weather.week_weather.Forecast;
import com.bfourclass.euopendata.secrets.Secrets;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

abstract class OpenWeatherAPI {

    protected static Weather requestWeather(String location) {
        String requestURL = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&APPID=" + Secrets.weatherApiKey;

        ObjectMapper mapper = new ObjectMapper();

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet request = new HttpGet(requestURL);

            return client.execute(request, httpResponse -> mapper.readValue(httpResponse.getEntity().getContent(), Weather.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected static Forecast requestForecast(String location) {
        String requestURL = String.format("http://api.openweathermap.org/data/2.5/forecast?q=%s&appid=%s", location, Secrets.weatherApiKey);

        ObjectMapper mapper = new ObjectMapper();

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet request = new HttpGet(requestURL);

            return client.execute(request, httpResponse -> mapper.readValue(httpResponse.getEntity().getContent(), Forecast.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}

