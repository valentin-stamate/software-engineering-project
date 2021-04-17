package com.bfourclass.euopendata.external_api.instance.covid_information;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

public class CovidInformation {
    @JsonIgnoreProperties
    public Object kind;
    @JsonIgnoreProperties
    public Object url;
    @JsonIgnoreProperties
    public Object queries;
    @JsonIgnoreProperties
    public Object context;
    @JsonIgnoreProperties
    public Object searchInformation;
    @JsonIgnoreProperties
    public Object error;
    @JsonIgnoreProperties
    public Object spelling;

    public List<Item> items;
}


