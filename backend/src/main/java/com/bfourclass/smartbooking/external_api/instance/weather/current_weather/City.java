package com.bfourclass.smartbooking.external_api.instance.weather.current_weather;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreType
public class City {
    public int id;
    public String name;
    public Coord coord;
    public String country;
    public int population;
    public int timezone;
    public int sunrise;
    public int sunset;
}
