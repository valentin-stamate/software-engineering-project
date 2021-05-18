package com.bfourclass.euopendata.external_api.instance.numbeo_data;

public class RestaurantsStatistics {
    /*Every attribute represent the average price of something in a specific location (City)*/
    private String location;
    private Float simpleMeal1PersonPrice;
    private Float fullMeal2PersonsPrice;
    private Float McMealPrice;
    private Float beerDraughtPrice;
    private Float beerBottlePrice;
    private Float cappuccinoPrice;
    private Float CokePrice;
    private Float WaterPrice;

    public RestaurantsStatistics(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Float getSimpleMeal1PersonPrice() {
        return simpleMeal1PersonPrice;
    }

    public void setSimpleMeal1PersonPrice(Double simpleMeal1PersonPrice) {
        this.simpleMeal1PersonPrice = simpleMeal1PersonPrice;
    }

    public Float getFullMeal2PersonsPrice() {
        return fullMeal2PersonsPrice;
    }

    public void setFullMeal2PersonsPrice(Double fullMeal2PersonsPrice) {
        this.fullMeal2PersonsPrice = fullMeal2PersonsPrice;
    }

    public Float getMcMealPrice() {
        return McMealPrice;
    }

    public void setMcMealPrice(Double mcMealPrice) {
        McMealPrice = mcMealPrice;
    }

    public Float getBeerDraughtPrice() {
        return beerDraughtPrice;
    }

    public void setBeerDraughtPrice(Double beerDraughtPrice) {
        this.beerDraughtPrice = beerDraughtPrice;
    }

    public Float getBeerBottlePrice() {
        return beerBottlePrice;
    }

    public void setBeerBottlePrice(Double beerBottlePrice) {
        this.beerBottlePrice = beerBottlePrice;
    }

    public Float getCappuccinoPrice() {
        return cappuccinoPrice;
    }

    public void setCappuccinoPrice(Double cappuccinoPrice) {
        this.cappuccinoPrice = cappuccinoPrice;
    }

    public Float getCokePrice() {
        return CokePrice;
    }

    public void setCokePrice(Double cokePrice) {
        CokePrice = cokePrice;
    }

    public Float getWaterPrice() {
        return WaterPrice;
    }

    public void setWaterPrice(Double waterPrice) {
        WaterPrice = waterPrice;
    }
}
