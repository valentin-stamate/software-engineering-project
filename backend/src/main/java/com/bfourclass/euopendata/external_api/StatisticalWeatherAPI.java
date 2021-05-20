package com.bfourclass.euopendata.external_api;

import com.bfourclass.euopendata.external_api.instance.weather.statistical_weather.StatisticalWeather;
import com.bfourclass.euopendata.secrets.Secrets;
import com.bfourclass.euopendata.util.Static;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class StatisticalWeatherAPI {

    protected static StatisticalWeather getStatisticalWeather(String location, LocalDate startDate) {
        if (Static.romanianCountyToRomanian.containsKey(location)) {
            location = Static.romanianCountyToRomanian.get(location);
        }

        location = location.toLowerCase(Locale.ROOT);
        location = location.replace(" ", "%20");

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate endDate = startDate.plusDays(1);

        String requestURL = String.format("https://api.weatherbit.io/v2.0/history/daily?&city=%s&start_date=%s&end_date=%s&key=%s",
                location, startDate.format(dateTimeFormatter), endDate.format(dateTimeFormatter), Secrets.WEATHER_BIT_API_KEY);

        ObjectMapper mapper = new ObjectMapper();

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet request = new HttpGet(requestURL);

            return client.execute(request, httpResponse -> mapper.readValue(httpResponse.getEntity().getContent(), StatisticalWeather.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
