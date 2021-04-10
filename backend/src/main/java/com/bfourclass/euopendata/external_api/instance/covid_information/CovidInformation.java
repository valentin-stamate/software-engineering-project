package com.bfourclass.euopendata.external_api.instance.covid_information;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class CovidInformation {
    public String kind;
    public Url url;
    public Queries queries;
    public Context context;
    public SearchInformation searchInformation;
    public List<Item> items;

    @JsonIgnoreProperties
    public Object error;

}


