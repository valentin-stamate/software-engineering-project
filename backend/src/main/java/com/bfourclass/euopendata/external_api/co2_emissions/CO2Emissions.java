package com.bfourclass.euopendata.external_api.co2_emissions;

import java.util.ArrayList;
import java.util.List;

public class CO2Emissions {
    public final String country;
    public final String code;
    public final String measure = "tons";
    public final List<Item> list;


    public CO2Emissions(String country, String code) {
        this.country = country;
        this.code = code;
        this.list = new ArrayList<>();
    }

    public void addToList(Item item) {
        list.add(item);
    }
}
