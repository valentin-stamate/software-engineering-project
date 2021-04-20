package com.bfourclass.euopendata.external_api.instance.weather.current_weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Wind {
    public double speed;
    public int deg;
    @JsonIgnoreProperties
    public double gust;
}
