package com.bfourclass.smartbooking.external_api.instance.weather.current_weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

public class Weather {
    public int dt;
    public Main main;
    public List<WeatherDetails> weather;
    public int visibility;
    public Wind wind;
    public Rain rain;

    public int timezone;
    public int id;
    public String name;
    public int cod;
    public String message;

    @JsonIgnoreProperties
    public String dt_txt;
    @JsonIgnoreProperties
    public double pop;
    @JsonIgnoreProperties
    public Clouds clouds;
    @JsonIgnoreProperties
    public String base;
    @JsonIgnoreProperties
    public Coord coord;
    @JsonIgnoreProperties
    public Object sys;
}

