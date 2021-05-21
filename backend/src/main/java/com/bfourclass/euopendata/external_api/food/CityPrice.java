package com.bfourclass.euopendata.external_api.food;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CityPrice {
    @JsonProperty("data_points")
    private int dataPoints;

    @JsonProperty("item_id")
    private int itemId;

    @JsonProperty("lowest_price")
    private float lowestPrice;

    @JsonProperty("average_price")
    private float averagePrice;

    @JsonProperty("highest_price")
    private float highestPrice;

    @JsonProperty("item_name")
    private String itemName;

    public CityPrice() {}

    public CityPrice(int dataPoints, int itemId, float lowestPrice, float averagePrice, float highestPrice, String itemName) {
        this.dataPoints = dataPoints;
        this.itemId = itemId;
        this.lowestPrice = lowestPrice;
        this.averagePrice = averagePrice;
        this.highestPrice = highestPrice;
        this.itemName = itemName;
    }

    public int getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(int dataPoints) {
        this.dataPoints = dataPoints;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public float getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(float lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public float getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(float averagePrice) {
        this.averagePrice = averagePrice;
    }

    public float getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(float highestPrice) {
        this.highestPrice = highestPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
