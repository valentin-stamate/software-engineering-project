package com.bfourclass.euopendata.external_api.covid;

import com.bfourclass.euopendata.external_api.instance.covid_news.CovidNews;
import com.bfourclass.euopendata.external_api.instance.covid_news.SearchResultJSON;
import com.bfourclass.euopendata.external_api.instance.covid_news.Item;
import com.bfourclass.euopendata.secrets.Secrets;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class GoogleSearchAPI {

    public static List<SearchResultJSON> fetchGoogleSearchResults(String query, int results) {
        query = query.replace(" ", "%20");
        String requestURL = "https://www.googleapis.com/customsearch/v1?key=" + Secrets.GOOGLE_CUSTOM_SEARCH_API_KEY + "&cx=183e9c932cf280453&q=" + query;

        ObjectMapper mapper = new ObjectMapper();

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet request = new HttpGet(requestURL);

            CovidNews response = client.execute(request, httpResponse -> mapper.readValue(httpResponse.getEntity().getContent(), CovidNews.class));

            List<SearchResultJSON> info = new ArrayList<>();

            if (response.items != null) {
                for (int i = 0; i < results && i < response.items.size(); i++) {
                    Item item = response.items.get(i);
                    info.add(new SearchResultJSON(item.title, item.link, item.displayLink, item.snippet));
                }
            }
            return info;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
