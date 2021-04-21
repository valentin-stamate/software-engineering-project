package com.bfourclass.euopendata.external_api.covid;

import com.bfourclass.euopendata.external_api.instance.covid_news.CovidNews;
import com.bfourclass.euopendata.external_api.instance.covid_news.CovidNewsJSON;
import com.bfourclass.euopendata.external_api.instance.covid_news.Item;
import com.bfourclass.euopendata.secrets.Secrets;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;

public abstract class CovidNewsAPI {

    public static CovidNewsJSON requestCovidInformation(String locationName) {
        String requestURL = "https://www.googleapis.com/customsearch/v1?key=" + Secrets.googleCustomSearchKey + "&cx=183e9c932cf280453&q=" + locationName + "%20covid";

        ObjectMapper mapper = new ObjectMapper();

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet request = new HttpGet(requestURL);

            CovidNews response = client.execute(request, httpResponse -> mapper.readValue(httpResponse.getEntity().getContent(), CovidNews.class));

            CovidNewsJSON info = null;

            if (response.items != null) {
                if (response.items.size() > 0) {
                    Item item = response.items.get(0);
                    info = new CovidNewsJSON(item.title, item.link, item.displayLink, item.snippet);
                }
            }
            return info;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
