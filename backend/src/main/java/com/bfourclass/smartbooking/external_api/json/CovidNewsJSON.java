package com.bfourclass.smartbooking.external_api.json;

import com.bfourclass.smartbooking.external_api.instance.covid_news.SearchResultJSON;

import java.util.List;

public class CovidNewsJSON {
    public static final int MAX_RESULTS = 3;

    public String location;
    public List<SearchResultJSON> results;

    public CovidNewsJSON(String location, List<SearchResultJSON> results) {
        this.location = location;
        this.results = results;
    }
}
