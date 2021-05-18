package com.bfourclass.euopendata.external_api.instance.numbeo_data;

public class CostOfLivingStatistics {
    /*
     * For each attribute, you can interpret the value as it follows:
     *      - VERY LOW level, if value is lower than 20
     *      - LOW level, if value is between 20 and 40
     *      - MODERATE level, if value is between 40 and 60
     *      - HIGH level, if value is between 60 and 80
     *      - VERY HIGH level, if value is higher than 80
     *
     *
     */
    private double monthlyPersonCost;
    private double averageMealPrice;
    private double domesticBeerPrice;
    private double capuccinoPrice;
    private double waterPrice;
    private double milkPrice;
    private double breadPrice;
    private double applesPrice;
    private double cigarettesPrice;
    private double busTicketPrice;
    private double taxiKmPrice;
    private double gasolinePrice;

    public double getMonthlyPersonCost() {
        return monthlyPersonCost;
    }

    public void setMonthlyPersonCost(double monthlyPersonCost) {
        this.monthlyPersonCost = monthlyPersonCost;
    }

    public double getAverageMealPrice() {
        return averageMealPrice;
    }

    public void setAverageMealPrice(double averageMealPrice) {
        this.averageMealPrice = averageMealPrice;
    }

    public double getDomesticBeerPrice() {
        return domesticBeerPrice;
    }

    public void setDomesticBeerPrice(double domesticBeerPrice) {
        this.domesticBeerPrice = domesticBeerPrice;
    }

    public double getCapuccinoPrice() {
        return capuccinoPrice;
    }

    public void setCapuccinoPrice(double capuccinoPrice) {
        this.capuccinoPrice = capuccinoPrice;
    }

    public double getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(double waterPrice) {
        this.waterPrice = waterPrice;
    }

    public double getMilkPrice() {
        return milkPrice;
    }

    public void setMilkPrice(double milkPrice) {
        this.milkPrice = milkPrice;
    }

    public double getBreadPrice() {
        return breadPrice;
    }

    public void setBreadPrice(double breadPrice) {
        this.breadPrice = breadPrice;
    }

    public double getApplesPrice() {
        return applesPrice;
    }

    public void setApplesPrice(double applesPrice) {
        this.applesPrice = applesPrice;
    }

    public double getCigarettesPrice() {
        return cigarettesPrice;
    }

    public void setCigarettesPrice(double cigarettesPrice) {
        this.cigarettesPrice = cigarettesPrice;
    }

    public double getBusTicketPrice() {
        return busTicketPrice;
    }

    public void setBusTicketPrice(double busTicketPrice) {
        this.busTicketPrice = busTicketPrice;
    }

    public double getTaxiKmPrice() {
        return taxiKmPrice;
    }

    public void setTaxiKmPrice(double taxiKmPrice) {
        this.taxiKmPrice = taxiKmPrice;
    }

    public double getGasolinePrice() {
        return gasolinePrice;
    }

    public void setGasolinePrice(double gasolinePrice) {
        this.gasolinePrice = gasolinePrice;
    }
}
