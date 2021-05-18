package com.bfourclass.euopendata.external_api.util;

import com.bfourclass.euopendata.external_api.instance.covid_statistics.CovidStatistics;
import com.bfourclass.euopendata.external_api.instance.covid_statistics.Item;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private String input;

    public CsvReader(String input) {
        this.input = input;
    }

    public List<String[]> read() {
        List<String[]> rows = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(input));) {
            String[] row = null;
            csvReader.readNext();

            while ((row = csvReader.readNext()) != null) {
                rows.add(row);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return rows;
    }

}
