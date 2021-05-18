package com.bfourclass.euopendata.external_api.gasoline_price;

public class GasolinePrice {
    public final String country;
    public final String code;
    public final String price;
    public final String measure = "$/l";

    public GasolinePrice(String country, String code, String price) {
        this.country = country;
        this.code = code;
        this.price = price;
    }
}
