package com.bfourclass.euopendata.hotel.json;

import com.bfourclass.euopendata.external_api.instance.covid_news.SearchResultJSON;
import java.util.List;

public class HotelSearchResultJSON {
    public List<HotelJSON> hotelList;
    public List<SearchResultJSON> hotelWebList;

    public HotelSearchResultJSON(List<HotelJSON> hotelList, List<SearchResultJSON> hotelWebList) {
        this.hotelList = hotelList;
        this.hotelWebList = hotelWebList;
    }
}
