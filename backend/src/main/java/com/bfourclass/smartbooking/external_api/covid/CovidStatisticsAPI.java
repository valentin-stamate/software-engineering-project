package com.bfourclass.smartbooking.external_api.covid;

import com.bfourclass.smartbooking.external_api.instance.covid_statistics.CovidStatistics;
import com.bfourclass.smartbooking.external_api.instance.covid_statistics.Item;
import com.bfourclass.smartbooking.external_api.util.CsvReader;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bfourclass.smartbooking.external_api.util.Util.toInt;

public class CovidStatisticsAPI implements Runnable {

    private static final String COVID_STATISTICS_URL = "https://covid.ourworldindata.org/data/owid-covid-data.csv";
    private static final String COVID_STATISTICS_LOCAL_PATH = "src/main/resources/static/statistics/covid_statistics.csv";
    private static String lastUpdate;

    private static final Map<String, CovidStatistics> covidStatisticsCache = new HashMap<>();

    public static CovidStatistics getCovidStatistics(String country) {
        if (covidStatisticsCache.containsKey(country)) {
            return covidStatisticsCache.get(country).copy();
        }

        return null;
    }


    private void updateData() {
        downloadData();

        CsvReader csvReader = new CsvReader(COVID_STATISTICS_LOCAL_PATH);
        List<String[]> rows = csvReader.read();

        covidStatisticsCache.clear();

        for (String[] row : rows) {
            CovidStatistics covidStatistics = new CovidStatistics(row[0], row[1], row[2]);

            Item item = new Item(row[3], toInt(row[5]), toInt(row[4]), toInt(row[8]), toInt(row[7]));
            String key = covidStatistics.country;

            if (!covidStatisticsCache.containsKey(key)) {
                covidStatisticsCache.put(key, covidStatistics);
            }

            covidStatisticsCache.get(key).addItem(item);
        }

        updateUpdateTime();
    }

    private static void downloadData() {
        try {
            File output = new File(COVID_STATISTICS_LOCAL_PATH);
            output.createNewFile();

            BufferedInputStream inputStream = new BufferedInputStream(new URL(COVID_STATISTICS_URL).openStream());
            FileOutputStream file = new FileOutputStream(COVID_STATISTICS_LOCAL_PATH);
            byte[] data = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(data, 0, 1024)) != -1) {
                file.write(data, 0, bytesRead);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void updateUpdateTime() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        lastUpdate = formatter.format(date);
    }

    @Override
    public void run() {
        System.out.println("Covid statistics thread running...");
        try {
            while (true) {
                updateData();
                Thread.sleep(86400000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
