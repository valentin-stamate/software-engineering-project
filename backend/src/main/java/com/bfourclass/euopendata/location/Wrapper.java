package com.bfourclass.euopendata.location;

import com.bfourclass.euopendata.ExternalAPI.AQICNDataAPI;
import com.bfourclass.euopendata.ExternalAPI.OpenWeatherAPI;
import com.bfourclass.euopendata.ExternalAPI.instance.aqicn_data.AirPollution;
import com.bfourclass.euopendata.ExternalAPI.instance.covid_restrictions.CovidRestrictions;
import com.bfourclass.euopendata.ExternalAPI.instance.weather.Weather;

import java.io.IOException;

public class Wrapper  {
    public Weather weather;
    public CovidRestrictions covidRestrictions;
    public AirPollution airPollution;



    public Location setLocation(String location) throws IOException {
        weather=OpenWeatherAPI.requestWeather(location);
        //**covid restricion to be done**
        airPollution= AQICNDataAPI.requestAirPollution(location);
        return new Location(location);
    }
}
