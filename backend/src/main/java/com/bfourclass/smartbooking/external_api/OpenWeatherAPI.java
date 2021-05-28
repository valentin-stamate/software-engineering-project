package com.bfourclass.smartbooking.external_api;

import com.bfourclass.smartbooking.external_api.instance.weather.current_weather.Weather;
import com.bfourclass.smartbooking.external_api.instance.weather.Forecast;
import com.bfourclass.smartbooking.secrets.Secrets;
import com.bfourclass.smartbooking.util.Static;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Locale;

abstract class OpenWeatherAPI {

    protected static Weather requestWeather(String location) {
        if (Static.romanianCountyToRomanian.containsKey(location)) {
            location = Static.romanianCountyToRomanian.get(location);
        }

        location = location.toLowerCase(Locale.ROOT);
        location = location.replace(" ", "%20");
        String requestURL = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&APPID=" + Secrets.API_KEY_OPEN_WEATHER;

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
        if (Static.romanianCountyToRomanian.containsKey(location)) {
            location = Static.romanianCountyToRomanian.get(location);
        }

        location = location.replace(" ", "%20");
        String requestURL = String.format("http://api.openweathermap.org/data/2.5/forecast?q=%s&appid=%s", location, Secrets.API_KEY_OPEN_WEATHER);

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

