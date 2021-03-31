package com.bfourclass.euopendata.ExternalAPI;

import com.bfourclass.euopendata.ExternalAPI.instance.weather.Weather;
import com.bfourclass.euopendata.secrets.Secrets;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public abstract class OpenWeatherAPI {

    public static Weather requestWeather(String locationName) {
        String requestURL = "http://api.openweathermap.org/data/2.5/weather?q=" + locationName + "&APPID=" + Secrets.weatherApiKey;

        ObjectMapper mapper = new ObjectMapper();

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet request = new HttpGet(requestURL);

            return client.execute(request, httpResponse -> mapper.readValue(httpResponse.getEntity().getContent(), Weather.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}

