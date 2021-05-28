package com.bfourclass.smartbooking.external_api.instance.covid_statistics;

public class Item {
    public String date;
    public int newCases;
    public int totalCases;
    public int newDeaths;
    public int totalDeaths;

    public Item(String date, int newCases, int totalCases, int newDeaths, int totalDeaths) {
        this.date = date;
        this.newCases = newCases;
        this.totalCases = totalCases;
        this.newDeaths = newDeaths;
        this.totalDeaths = totalDeaths;
    }

}
