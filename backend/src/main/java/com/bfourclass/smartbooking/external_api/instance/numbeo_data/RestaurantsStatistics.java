package com.bfourclass.smartbooking.external_api.instance.numbeo_data;

public class RestaurantsStatistics {
    /*Every attribute represent the average price of something in a specific location (City)*/
    private String location;
    private String simpleMeal1PersonPrice;
    private String fullMeal2PersonsPrice;
    private String McMealPrice;
    private String beerDraughtPrice;
    private String beerBottlePrice;
    private String cappuccinoPrice;
    private String CokePrice;
    private String WaterPrice;

    public RestaurantsStatistics(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSimpleMeal1PersonPrice() {
        return simpleMeal1PersonPrice;
    }

    public void setSimpleMeal1PersonPrice(String simpleMeal1PersonPrice) {
        this.simpleMeal1PersonPrice = simpleMeal1PersonPrice;
    }

    public String getFullMeal2PersonsPrice() {
        return fullMeal2PersonsPrice;
    }

    public void setFullMeal2PersonsPrice(String fullMeal2PersonsPrice) {
        this.fullMeal2PersonsPrice = fullMeal2PersonsPrice;
    }

    public String getMcMealPrice() {
        return McMealPrice;
    }

    public void setMcMealPrice(String mcMealPrice) {
        McMealPrice = mcMealPrice;
    }

    public String getBeerDraughtPrice() {
        return beerDraughtPrice;
    }

    public void setBeerDraughtPrice(String beerDraughtPrice) {
        this.beerDraughtPrice = beerDraughtPrice;
    }

    public String getBeerBottlePrice() {
        return beerBottlePrice;
    }

    public void setBeerBottlePrice(String beerBottlePrice) {
        this.beerBottlePrice = beerBottlePrice;
    }

    public String getCappuccinoPrice() {
        return cappuccinoPrice;
    }

    public void setCappuccinoPrice(String cappuccinoPrice) {
        this.cappuccinoPrice = cappuccinoPrice;
    }

    public String getCokePrice() {
        return CokePrice;
    }

    public void setCokePrice(String cokePrice) {
        CokePrice = cokePrice;
    }

    public String getWaterPrice() {
        return WaterPrice;
    }

    public void setWaterPrice(String waterPrice) {
        WaterPrice = waterPrice;
    }
}
