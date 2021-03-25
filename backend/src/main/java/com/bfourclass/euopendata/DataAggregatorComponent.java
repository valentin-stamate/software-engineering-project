package com.bfourclass.euopendata;

public abstract class DataAggregatorComponent{
    private GoogleWeatherClient weatherClient;
    private GoogleNewsClient newsClient;
    private CovidRestrictionsClient covidRestrictionsClient;

    public DataAggregatorComponent(GoogleWeatherClient weatherClient, GoogleNewsClient newsClient, CovidRestrictionsClient covidRestrictionsClient) {
        this.weatherClient = weatherClient;
        this.newsClient = newsClient;
        this.covidRestrictionsClient = covidRestrictionsClient;
    }

    public GoogleWeatherClient getWeatherClient() {
        return weatherClient;
    }

    public void setWeatherClient(GoogleWeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public GoogleNewsClient getNewsClient() {
        return newsClient;
    }

    public void setNewsClient(GoogleNewsClient newsClient) {
        this.newsClient = newsClient;
    }

    public CovidRestrictionsClient getCovidRestrictionsClient() {
        return covidRestrictionsClient;
    }

    public void setCovidRestrictionsClient(CovidRestrictionsClient covidRestrictionsClient) {
        this.covidRestrictionsClient = covidRestrictionsClient;
    }
}