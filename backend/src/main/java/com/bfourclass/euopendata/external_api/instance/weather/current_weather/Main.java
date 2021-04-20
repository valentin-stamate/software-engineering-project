package com.bfourclass.euopendata.external_api.instance.weather.current_weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Main {
    public double temp;
    public double feels_like;
    public double temp_min;
    public double temp_max;
    public int pressure;
    public int humidity;
    @JsonIgnoreProperties
    public int sea_level;
    @JsonIgnoreProperties
    public int grnd_level;
    @JsonIgnoreProperties
    public double temp_kf;
}
