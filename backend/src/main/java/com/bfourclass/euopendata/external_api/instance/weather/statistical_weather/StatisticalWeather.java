package com.bfourclass.euopendata.external_api.instance.weather.statistical_weather;

import com.bfourclass.euopendata.external_api.StatisticalWeatherAPI;

import java.util.ArrayList;
import java.util.List;

public class StatisticalWeather {
    public String timezone;
    public String state_code;
    public String country_code;
    public double lat;
    public double lon;
    public String city_name;
    public String station_id;
    public List<Data> data;
    public List<String> sources;
    public String city_id;

    public static StatisticalWeather mean(List<StatisticalWeather> statisticalWeatherList) {
        int n = statisticalWeatherList.size();

        StatisticalWeather statisticalWeatherMean = new StatisticalWeather();

        StatisticalWeather statisticalWeather = statisticalWeatherList.get(0);

        statisticalWeatherMean.timezone = statisticalWeather.timezone;
        statisticalWeatherMean.state_code = statisticalWeather.state_code;
        statisticalWeatherMean.country_code = statisticalWeather.country_code;
        statisticalWeatherMean.lat = statisticalWeather.lat;
        statisticalWeatherMean.lon = statisticalWeather.lon;
        statisticalWeatherMean.city_name = statisticalWeather.city_name;
        statisticalWeatherMean.station_id = statisticalWeather.station_id;
        statisticalWeatherMean.data = new ArrayList<>(1);
        statisticalWeatherMean.sources = new ArrayList<>(1);
        statisticalWeatherMean.city_id = statisticalWeather.city_id;

        List<Data> dataList = new ArrayList<>();

        for (StatisticalWeather st : statisticalWeatherList) {
            dataList.add(st.data.get(0));
        }

        statisticalWeatherMean.data.add(Data.mean(dataList));

        return statisticalWeatherMean;
    }
}


