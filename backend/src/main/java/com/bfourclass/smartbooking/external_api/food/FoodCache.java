package com.bfourclass.smartbooking.external_api.food;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodCache {
    private HashMap<String, List<FoodPrice>> foodPriceMap;

    public FoodCache() {
        this.foodPriceMap = new HashMap<String, List<FoodPrice>>();
    }

    public List<FoodPrice> verifyLocation(String locationName){
        if(foodPriceMap.containsKey(locationName)){
            return foodPriceMap.get(locationName);
        }else{
            return null;
        }
    }
    public void addLocationPrice(String locationName, List<FoodPrice> foodPrices){
        foodPriceMap.put(locationName,foodPrices);
    }
}
