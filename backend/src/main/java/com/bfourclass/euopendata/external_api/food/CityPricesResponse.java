package com.bfourclass.euopendata.external_api.food;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CityPricesResponse {
    @JsonProperty("name")
    private String name;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("contributors12months")
    private int contributors12Months;

    @JsonProperty("monthLastUpdate")
    private int monthLastUpdate;

    @JsonProperty("contributors")
    private int contributors;

    @JsonProperty("yearLastUpdate")
    private int yearLastUpdate;

    @JsonProperty("prices")
    private List<CityPrice> prices;

    @JsonProperty("city_id")
    private int cityId;

    public CityPricesResponse() {}

    public CityPricesResponse(String name, String currency, int contributors12Months, int monthLastUpdate, int contributors, int yearLastUpdate, List<CityPrice> prices, int cityId) {
        this.name = name;
        this.currency = currency;
        this.contributors12Months = contributors12Months;
        this.monthLastUpdate = monthLastUpdate;
        this.contributors = contributors;
        this.yearLastUpdate = yearLastUpdate;
        this.prices = prices;
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getContributors12Months() {
        return contributors12Months;
    }

    public void setContributors12Months(int contributors12Months) {
        this.contributors12Months = contributors12Months;
    }

    public int getMonthLastUpdate() {
        return monthLastUpdate;
    }

    public void setMonthLastUpdate(int monthLastUpdate) {
        this.monthLastUpdate = monthLastUpdate;
    }

    public int getContributors() {
        return contributors;
    }

    public void setContributors(int contributors) {
        this.contributors = contributors;
    }

    public int getYearLastUpdate() {
        return yearLastUpdate;
    }

    public void setYearLastUpdate(int yearLastUpdate) {
        this.yearLastUpdate = yearLastUpdate;
    }

    public List<CityPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<CityPrice> prices) {
        this.prices = prices;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
