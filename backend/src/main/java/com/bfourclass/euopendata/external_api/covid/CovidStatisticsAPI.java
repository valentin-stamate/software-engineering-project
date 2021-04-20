package com.bfourclass.euopendata.external_api.covid;

import com.bfourclass.euopendata.external_api.instance.covid_statistics.CovidStatistics;
import com.bfourclass.euopendata.external_api.instance.covid_statistics.Item;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CovidStatisticsAPI implements Runnable {

    private static final String COVID_STATISTICS_URL = "https://covid.ourworldindata.org/data/owid-covid-data.csv";
    private static final String COVID_STATISTICS_LOCAL_PATH = "src/main/resources/static/covid_statistics.csv";
    private static String lastUpdate;

    private static Map<String, CovidStatistics> covidStatisticsCache = new HashMap<>();

    public static CovidStatistics getCovidStatistics(String country) {
        if (covidStatisticsCache.containsKey(country)) {
            return covidStatisticsCache.get(country);
        }

        return null;
    }

    private void updateData() {
        downloadData();

        try (CSVReader csvReader = new CSVReader(new FileReader(COVID_STATISTICS_LOCAL_PATH));) {
            String[] values = null;
            csvReader.readNext();

            covidStatisticsCache.clear();

            while ((values = csvReader.readNext()) != null) {
                CovidStatistics covidStatistics = new CovidStatistics(values[0], values[1], values[2]);

                Item item = new Item(values[3], toInt(values[5]), toInt(values[4]), toInt(values[8]), toInt(values[7]));
                String key = covidStatistics.country;

                if (!covidStatisticsCache.containsKey(key)) {
                    covidStatisticsCache.put(key, covidStatistics);
                }

                covidStatisticsCache.get(key).addItem(item);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        updateUpdateTime();
    }

    private int toInt(String value) {
        if (value.length() == 0) {
            return 0;
        }
        return (int)Double.parseDouble(value);
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
