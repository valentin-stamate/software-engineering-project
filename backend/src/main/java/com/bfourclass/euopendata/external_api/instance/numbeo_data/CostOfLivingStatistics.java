package com.bfourclass.euopendata.external_api.instance.numbeo_data;

public class CostOfLivingStatistics {
    /*
    * Every attribute's value is an estimation of the price, with values in local currency.
     */
    private String monthlyPersonCost;

    // Prices on an inexpensive restaurant
    private String averageMealPrice;
    private String cappuccinoPrice;

    // Prices at market
    private String domesticBeerPrice;
    private String waterPrice;
    private String milkPrice;
    private String breadPrice;
    private String applesPrice;
    private String cigarettesPrice;

    // Transportation prices
    private String busTicketPrice;
    private String taxiKmPrice;
    private String gasolinePrice;

    public CostOfLivingStatistics() {
        monthlyPersonCost = null;
        averageMealPrice = null;
        domesticBeerPrice = null;
        cappuccinoPrice = null;
        waterPrice = null;
        milkPrice = null;
        breadPrice = null;
        applesPrice = null;
        cigarettesPrice = null;
        busTicketPrice = null;
        taxiKmPrice = null;
        gasolinePrice = null;
    }

    public String getMonthlyPersonCost() {
        return monthlyPersonCost;
    }

    public void setMonthlyPersonCost(String monthlyPersonCost) {
        this.monthlyPersonCost = monthlyPersonCost;
    }

    public String getAverageMealPrice() {
        return averageMealPrice;
    }

    public void setAverageMealPrice(String averageMealPrice) {
        this.averageMealPrice = averageMealPrice;
    }

    public String getDomesticBeerPrice() {
        return domesticBeerPrice;
    }

    public void setDomesticBeerPrice(String domesticBeerPrice) {
        this.domesticBeerPrice = domesticBeerPrice;
    }

    public String getCappuccinoPrice() {
        return cappuccinoPrice;
    }

    public void setCappuccinoPrice(String cappuccinoPrice) {
        this.cappuccinoPrice = cappuccinoPrice;
    }

    public String getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(String waterPrice) {
        this.waterPrice = waterPrice;
    }

    public String getMilkPrice() {
        return milkPrice;
    }

    public void setMilkPrice(String milkPrice) {
        this.milkPrice = milkPrice;
    }

    public String getBreadPrice() {
        return breadPrice;
    }

    public void setBreadPrice(String breadPrice) {
        this.breadPrice = breadPrice;
    }

    public String getApplesPrice() {
        return applesPrice;
    }

    public void setApplesPrice(String applesPrice) {
        this.applesPrice = applesPrice;
    }

    public String getCigarettesPrice() {
        return cigarettesPrice;
    }

    public void setCigarettesPrice(String cigarettesPrice) {
        this.cigarettesPrice = cigarettesPrice;
    }

    public String getBusTicketPrice() {
        return busTicketPrice;
    }

    public void setBusTicketPrice(String busTicketPrice) {
        this.busTicketPrice = busTicketPrice;
    }

    public String getTaxiKmPrice() {
        return taxiKmPrice;
    }

    public void setTaxiKmPrice(String taxiKmPrice) {
        this.taxiKmPrice = taxiKmPrice;
    }

    public String getGasolinePrice() {
        return gasolinePrice;
    }

    public void setGasolinePrice(String gasolinePrice) {
        this.gasolinePrice = gasolinePrice;
    }
}
