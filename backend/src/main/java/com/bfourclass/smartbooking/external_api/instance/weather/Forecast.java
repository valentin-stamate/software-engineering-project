package com.bfourclass.smartbooking.external_api.instance.weather;

import com.bfourclass.smartbooking.external_api.instance.weather.current_weather.*;
import java.util.List;

public class Forecast {
    public String cod;
    public int message;
    public int cnt;
    public List<Weather> list;
    public City city;
}


