package com.bfourclass.euopendata.external_api.food;

public class FoodPrice {
    private String name;
    private String price;

    public FoodPrice(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
