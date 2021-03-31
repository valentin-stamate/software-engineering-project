package com.bfourclass.euopendata.ExternalAPI;

import com.bfourclass.euopendata.ExternalAPI.instance.covid_restrictions.CovidRestrictions;

public class CovidRestrictionsClient {
    private String apiKey;

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public CovidRestrictions requestCovidRestrictions (String city){
        return null;
    }
    public CovidRestrictions requestCovidRestrictions (String city,String date){
        return null;
    }

//    public CovidRestrictions requestCovidRestrictions (String country){
//        return null;
//    }
//    public CovidRestrictions requestCovidRestrictions (String country, String date){
//        return null;
//    }
}
