package com.bfourclass.smartbooking.external_api.co2_emissions;

import com.bfourclass.smartbooking.external_api.instance.covid_statistics.CovidStatistics;
import com.bfourclass.smartbooking.external_api.util.CsvReader;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.bfourclass.smartbooking.external_api.util.Util.toInt;

public class CO2EmissionsAPI {
    private static final String RESOURCE_PATH = "src/main/resources/static/statistics/annual_co2_emissions.csv";

    private static final Map<String, CO2Emissions> statistics = new HashMap<>();

    public static CO2Emissions get(String country) {
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

        for (String[] row : rows) {
            CO2Emissions co2Emissions = new CO2Emissions(row[0], row[1]);

            String country = row[0].toLowerCase(Locale.ROOT);

            if (!statistics.containsKey(country)) {
                statistics.put(country, co2Emissions);
            }

            Item item = new Item(toInt(row[2]), toInt(row[3]));

            statistics.get(country).addToList(item);
        }
    }

}
