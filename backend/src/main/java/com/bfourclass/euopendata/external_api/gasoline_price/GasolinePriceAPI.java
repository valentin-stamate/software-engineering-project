package com.bfourclass.euopendata.external_api.gasoline_price;

import com.bfourclass.euopendata.external_api.util.CsvReader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GasolinePriceAPI {
    private static final String RESOURCE_PATH = "src/main/resources/static/statistics/pump_price_gasoline.csv";

    private static final Map<String, GasolinePrice> statistics = new HashMap<>();

    public static GasolinePrice get(String country) {
        if (statistics.size() == 0) {
            initDataset();
        }

        country = country.toLowerCase(Locale.ROOT);

        if (statistics.containsKey(country)) {
            return statistics.get(country);
        }

        return null;
    }

    public static void initDataset() {
        statistics.clear();

        CsvReader csvReader = new CsvReader(RESOURCE_PATH);
        List<String[]> rows = csvReader.read();

        for (int i = 0; i < rows.size(); i++) {
            String[] row = rows.get(i);
            GasolinePrice co2Emissions = new GasolinePrice(row[0], row[1], row[4]);

            String country = row[0].toLowerCase(Locale.ROOT);

            if (!statistics.containsKey(country)) {
                statistics.put(country, co2Emissions);
            }
        }
    }
}
