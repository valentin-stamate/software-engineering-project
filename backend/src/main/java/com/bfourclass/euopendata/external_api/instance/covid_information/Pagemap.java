package com.bfourclass.euopendata.external_api.instance.covid_information;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class Pagemap {
    public List<Metatag> metatags;
    public List<CseImage> cse_image;
    public List<CseThumbnail> cse_thumbnail;
    public List<Breadcrumb> breadcrumb;
    public List<Webpage> webpage;
    public List<Hcard> hcard;
    public List<Person> person;
    public List<Itemlist> itemlist;
    public List<Listitem> listitem;

    @JsonIgnoreProperties
    public Object WebPage;
    @JsonIgnoreProperties
    public Object Organization;
    @JsonIgnoreProperties
    public Object BreadcrumbList;
}
