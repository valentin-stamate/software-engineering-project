package com.bfourclass.euopendata.external_api.instance.covid_statistics;

import java.util.ArrayList;
import java.util.List;

public class CovidStatistics {
    public String isoCode;
    public String continent;
    public String country;
    public List<Item> items;

    public CovidStatistics(String isoCode, String continent, String country) {
        this.isoCode = isoCode;
        this.continent = continent;
        this.country = country;
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public CovidStatistics copy() {
        CovidStatistics covidStatistics = new CovidStatistics(isoCode, continent, country);
        covidStatistics.items = new ArrayList<>(items);
        return covidStatistics;
    }
}
