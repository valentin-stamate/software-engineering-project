package com.bfourclass.smartbooking.external_api.instance.weather.current_weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rain{
    @JsonProperty("1h")
    public double _1h;

    @JsonProperty("3h")
    public double _3h;
}
