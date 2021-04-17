package com.bfourclass.euopendata.external_api;

import com.bfourclass.euopendata.external_api.instance.numbeo_data.CriminalityStatistics;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.stream.Collectors;

public class NumbeoAPI {
    private static CriminalityStatistics parseHTMLCode(String htmlCode)
    {
        Document htmlParser = Jsoup.parse(htmlCode);

        // Verifying if "City not found" exception was raised
        if(htmlParser.select("div[style=\"error_message\"]").size() > 0)
            return null;

        // Parsing data
        Element container;
        Elements elements;
        CriminalityStatistics criminalityStatistics = new CriminalityStatistics();

        // Parsing data on safety and crime indexes container
        container = htmlParser.select(".table_indices").get(0);
        elements = container.select("td[style=\"text-align: right\"]");
        criminalityStatistics.setCrimeIndex(Double.valueOf(elements.get(0).text()));
        criminalityStatistics.setSafetyIndex(Double.valueOf(elements.get(1).text()));

        // Parsing data on crime rates container
        container = htmlParser.select("table[class=\"table_builder_with_value_explanation data_wide_table\"]").get(0);
        elements = container.select("td[class=\"indexValueTd\"]");
        criminalityStatistics.setCrimeIncreasingPast3YearsIndex(Double.valueOf(elements.get(1).text()));
        criminalityStatistics.setBrokenHomesIndex(Double.valueOf(elements.get(2).text()));
        criminalityStatistics.setRobbingIndex(Double.valueOf(elements.get(3).text()));
        criminalityStatistics.setStolenCarsIndex(Double.valueOf(elements.get(4).text()));
        criminalityStatistics.setStolenObjectsFromCarsIndex(Double.valueOf(elements.get(5).text()));
        criminalityStatistics.setAttackedIndex(Double.valueOf(elements.get(6).text()));
        criminalityStatistics.setInsultedIndex(Double.valueOf(elements.get(7).text()));
        criminalityStatistics.setRacismIndex(Double.valueOf(elements.get(8).text()));
        criminalityStatistics.setDrugsIndex(Double.valueOf(elements.get(9).text()));
        criminalityStatistics.setViolentCrimesIndex(Double.valueOf(elements.get(11).text()));
        criminalityStatistics.setCorruptionIndex(Double.valueOf(elements.get(12).text()));

        // Parsing data on safety rates container
        container = htmlParser.select("table[class=\"table_builder_with_value_explanation data_wide_table\"]").get(1);
        elements = container.select("td[class=\"indexValueTd\"]");
        criminalityStatistics.setNightWalkingSafetyIndex(Double.valueOf(elements.get(1).text()));

        return criminalityStatistics;
    }

    public static CriminalityStatistics requestCriminalityStatistics(String cityName) throws IOException
    {
        // Requesting HTML page
        cityName = cityName.substring(0, 1).toUpperCase() + cityName.substring(1);
        String command = "curl https://www.numbeo.com/crime/in/" + cityName + "/";
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));

        processBuilder.directory(new File("C:\\"));
        Process process = processBuilder.start();

        InputStream inputStream = process.getInputStream();
        String data = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
        process.destroy();

        // Processing the html code and creating an instance
        return parseHTMLCode(data);
    }
}
