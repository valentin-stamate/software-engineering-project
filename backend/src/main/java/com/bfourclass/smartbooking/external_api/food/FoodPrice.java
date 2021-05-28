package com.bfourclass.smartbooking.external_api.food;

public class FoodPrice {
    private String name;
    private String price;

    public FoodPrice(String name, String price) {
        this.name = name;
        setPrice(price);
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
        try {
            price = price.substring(0, price.indexOf(".") + 3) + " lei";
        } catch (Exception e) { }
        this.price = price;
    }
}
