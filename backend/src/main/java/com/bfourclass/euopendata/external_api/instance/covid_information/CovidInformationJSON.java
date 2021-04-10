package com.bfourclass.euopendata.external_api.instance.covid_information;

public class CovidInformationJSON {
    public String title;
    public String link;
    public String displayLink;
    public String snippet;

    public CovidInformationJSON(String title, String link, String displayLink, String snippet) {
        this.title = title;
        this.link = link;
        this.displayLink = displayLink;
        this.snippet = snippet;
    }
}
