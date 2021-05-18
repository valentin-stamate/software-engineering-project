package com.bfourclass.euopendata.external_api.instance.numbeo_data;

public class RestaurantsStatistics {
    /*Every attribute represent the average price of something in a specific location (City)*/
    private String location;
    private Double simpleMeal1PersonPrice;
    private Double fullMeal2PersonsPrice;
    private Double McMealPrice;
    private Double beerDraughtPrice;
    private Double beerBottlePrice;
    private Double cappuccinoPrice;
    private Double CokePrice;
    private Double WaterPrice;

    public RestaurantsStatistics(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getSimpleMeal1PersonPrice() {
        return simpleMeal1PersonPrice;
    }

    public void setSimpleMeal1PersonPrice(Double simpleMeal1PersonPrice) {
        this.simpleMeal1PersonPrice = simpleMeal1PersonPrice;
    }

    public Double getFullMeal2PersonsPrice() {
        return fullMeal2PersonsPrice;
    }

    public void setFullMeal2PersonsPrice(Double fullMeal2PersonsPrice) {
        this.fullMeal2PersonsPrice = fullMeal2PersonsPrice;
    }

    public Double getMcMealPrice() {
        return McMealPrice;
    }

    public void setMcMealPrice(Double mcMealPrice) {
        McMealPrice = mcMealPrice;
    }

    public Double getBeerDraughtPrice() {
        return beerDraughtPrice;
    }

    public void setBeerDraughtPrice(Double beerDraughtPrice) {
        this.beerDraughtPrice = beerDraughtPrice;
    }

    public Double getBeerBottlePrice() {
        return beerBottlePrice;
    }

    public void setBeerBottlePrice(Double beerBottlePrice) {
        this.beerBottlePrice = beerBottlePrice;
    }

    public Double getCappuccinoPrice() {
        return cappuccinoPrice;
    }

    public void setCappuccinoPrice(Double cappuccinoPrice) {
        this.cappuccinoPrice = cappuccinoPrice;
    }

    public Double getCokePrice() {
        return CokePrice;
    }

    public void setCokePrice(Double cokePrice) {
        CokePrice = cokePrice;
    }

    public Double getWaterPrice() {
        return WaterPrice;
    }

    public void setWaterPrice(Double waterPrice) {
        WaterPrice = waterPrice;
    }
}
