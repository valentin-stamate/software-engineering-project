package com.bfourclass.euopendata.external_api;

import com.bfourclass.euopendata.external_api.instance.numbeo_data.CostOfLivingStatistics;
import com.bfourclass.euopendata.external_api.instance.numbeo_data.CriminalityStatistics;
import com.bfourclass.euopendata.external_api.instance.numbeo_data.PollutionStatistics;
import com.bfourclass.euopendata.external_api.instance.numbeo_data.RestaurantsStatistics;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.text.Normalizer;
import java.util.Locale;
import java.util.stream.Collectors;

public class NumbeoAPI {
    private static CriminalityStatistics parseCriminalityHTMLCode(String cityName, String htmlCode) {
        Document htmlParser = Jsoup.parse(htmlCode);

        // Verifying if "City not found" exception was raised
        if(htmlParser.select("div[style=\"error_message\"]").size() > 0)
            return null;

        // Parsing data
        Element container;
        Elements elements;
        CriminalityStatistics criminalityStatistics = new CriminalityStatistics(cityName);

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

    public static CriminalityStatistics requestCriminalityStatistics(String cityName) throws IOException {
        // Getting rid of diacritics
        cityName = Normalizer.normalize(cityName, Normalizer.Form.NFD);
        cityName = cityName.replaceAll("\\p{M}", "");
        cityName = cityName.toLowerCase(Locale.ROOT);

        // Non-english name bug fix
        if(cityName.equals("bucuresti"))
            cityName = "bucharest";

        // Requesting HTML page
        cityName = cityName.substring(0, 1).toUpperCase() + cityName.substring(1);
        String command = "curl https://www.numbeo.com/crime/in/" + cityName + "/";
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));

        if(System.getProperty("os.name").startsWith("Windows")) // Windows
            processBuilder.directory(new File("C:\\"));
        else // Linux
            processBuilder.directory(new File("~"));

        Process process = processBuilder.start();

        InputStream inputStream = process.getInputStream();
        String data = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
        process.destroy();

        // Processing the html code and creating an instance
        return parseCriminalityHTMLCode(cityName, data);
    }

    private static CostOfLivingStatistics parseCostOfLiving(String cityName, String htmlCode) {
        Document htmlParser = Jsoup.parse(htmlCode);

        // Verifying if "City not found" exception was raised
        if(htmlParser.select("div[style=\"error_message\"]").size() > 0)
            return null;

        // Parsing data
        Element container;
        Elements elements;
        CostOfLivingStatistics costOfLivingStatistics=new CostOfLivingStatistics();

        // Parsing data on safety and crime indexes container
        container = htmlParser.select("table[class=\"seeding-call table_color summary limit_size_ad_right padding_lower other_highlight_color\"]").get(0);
        elements = container.select("span[class=\"emp_number\"]");
        costOfLivingStatistics.setMonthlyPersonCost(Double.valueOf(elements.get(1).text().split("lei")[0]));

        // Parsing data on crime rates container
        container = htmlParser.select("table[class=\"data_wide_table new_bar_table\"]").get(0);
        elements = container.select("span[class=\"first_currency\"]");
        costOfLivingStatistics.setAverageMealPrice(Double.valueOf(elements.get(0).text().split("&nbsp;")[0]));
        costOfLivingStatistics.setDomesticBeerPrice(Double.valueOf(elements.get(24).text().split("&nbsp;")[0]));
        costOfLivingStatistics.setCapuccinoPrice(Double.valueOf(elements.get(5).text().split("&nbsp;")[0]));
        costOfLivingStatistics.setWaterPrice(Double.valueOf(elements.get(22).text().split("&nbsp;")[0]));
        costOfLivingStatistics.setMilkPrice(Double.valueOf(elements.get(8).text().split("&nbsp;")[0]));
        costOfLivingStatistics.setBreadPrice(Double.valueOf(elements.get(9).text().split("&nbsp;")[0]));
        costOfLivingStatistics.setApplesPrice(Double.valueOf(elements.get(15).text().split("&nbsp;")[0]));
        costOfLivingStatistics.setCigarettesPrice(Double.valueOf(elements.get(26).text().split("&nbsp;")[0]));
        costOfLivingStatistics.setBusTicketPrice(Double.valueOf(elements.get(27).text().split("&nbsp;")[0]));
        costOfLivingStatistics.setTaxiKmPrice(Double.valueOf(elements.get(30).text().split("&nbsp;")[0]));
        costOfLivingStatistics.setGasolinePrice(Double.valueOf(elements.get(32).text().split("&nbsp;")[0]));



        return costOfLivingStatistics;
    }

    public static CostOfLivingStatistics requestCostOfLiving(String cityName) throws IOException {
        // Getting rid of diacritics
        cityName = Normalizer.normalize(cityName, Normalizer.Form.NFD);
        cityName = cityName.replaceAll("\\p{M}", "");
        cityName = cityName.toLowerCase(Locale.ROOT);

        // Non-english name bug fix
        if(cityName.equals("bucuresti"))
            cityName = "bucharest";

        // Requesting HTML page
        cityName = cityName.substring(0, 1).toUpperCase() + cityName.substring(1);
        String command = "curl https://www.numbeo.com/cost-of-living/in/" + cityName + "/";
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));

        if(System.getProperty("os.name").startsWith("Windows")) // Windows
            processBuilder.directory(new File("C:\\"));
        else // Linux
            processBuilder.directory(new File("~"));

        Process process = processBuilder.start();

        InputStream inputStream = process.getInputStream();
        String data = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
        process.destroy();

        // Processing the html code and creating an instance
        return parseCostOfLiving(cityName, data);
    }

    private static PollutionStatistics parsePollutionHTMLCode(String htmlCode, String location)
    {
        Document htmlParser = Jsoup.parse(htmlCode);

        // Verifying if "City not found" exception was raised
        if(htmlParser.select("div[style=\"error_message\"]").size() > 0)
            return null;

        // Parsing data
        Element container;
        Elements elements;
        PollutionStatistics pollutionStatistics = new PollutionStatistics(location);

        // Parsing data on pollution index container
        container = htmlParser.select(".table_indices").get(0);
        elements = container.select("td[style=\"text-align: right\"]");
        pollutionStatistics.setPollutionIndex(Double.valueOf(elements.get(0).text()));

        // Parsing data on pollution container
        container = htmlParser.select("table[class=\"table_builder_with_value_explanation data_wide_table\"]").get(0);
        elements = container.select("td[class=\"indexValueTd\"]");
        pollutionStatistics.setDrinkingWaterPollutionAndInaccesibilityIndex(Double.valueOf(elements.get(1).text()));
        pollutionStatistics.setDissatisfactionGarbageDisposalIndex(Double.valueOf(elements.get(2).text()));
        pollutionStatistics.setDirtyAndUntidyIndex(Double.valueOf(elements.get(3).text()));
        pollutionStatistics.setNoiseAndLightPollutionIndex(Double.valueOf(elements.get(4).text()));
        pollutionStatistics.setWaterPollutionIndex(Double.valueOf(elements.get(5).text()));
        pollutionStatistics.setDissatisfactionGreenAndParksIndex(Double.valueOf(elements.get(7).text()));

        return pollutionStatistics;
    }

    public static PollutionStatistics requestPollutionStatistics(String cityName) throws IOException
    {
        // Requesting HTML page
        cityName = cityName.substring(0, 1).toUpperCase() + cityName.substring(1);
        String command = "curl https://www.numbeo.com/pollution/in/" + cityName + "/";
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));

        if(System.getProperty("os.name").startsWith("Windows")) // Windows
            processBuilder.directory(new File("C:\\"));
        else // Linux
            processBuilder.directory(new File("~"));
        Process process = processBuilder.start();

        InputStream inputStream = process.getInputStream();
        String data = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
        process.destroy();

        // Processing the html code and creating an instance
        return parsePollutionHTMLCode(data, cityName);
    }
    private static RestaurantsStatistics parseRestaurantsHTMLCode(String htmlCode, String location)
    {
        Document htmlParser = Jsoup.parse(htmlCode);

        // Verifying if "City not found" exception was raised
        if(htmlParser.select("div[style=\"error_message\"]").size() > 0)
            return null;

        // Parsing data
        Element container;
        Elements elements;
        RestaurantsStatistics restaurantsStatistics = new RestaurantsStatistics(location);

        // Parsing data on pollution index container
        container = htmlParser.select(".table_indices").get(0);
        elements = container.select("td[style=\"text-align: right\"]");


        // Parsing data on restaurant container
        container = htmlParser.select("table[class=\"table_builder_with_value_explanation data_wide_table\"]").get(0);
        elements = container.select("td[class=\"indexValueTd\"]");
        restaurantsStatistics.setSimpleMeal1PersonPrice(Double.valueOf(elements.get(1).text()));
        restaurantsStatistics.setFullMeal2PersonsPrice(Double.valueOf(elements.get(2).text()));
        restaurantsStatistics.setMcMealPrice(Double.valueOf(elements.get(3).text()));
        restaurantsStatistics.setBeerDraughtPrice(Double.valueOf(elements.get(4).text()));
        restaurantsStatistics.setBeerBottlePrice(Double.valueOf(elements.get(5).text()));
        restaurantsStatistics.setCappuccinoPrice(Double.valueOf(elements.get(7).text()));
        restaurantsStatistics.setCokePrice(Double.valueOf(elements.get(8).text()));
        restaurantsStatistics.setWaterPrice(Double.valueOf(elements.get(9).text()));

        return restaurantsStatistics;
    }
    public static RestaurantsStatistics requestRestaurantStatistics(String cityName) throws IOException
    {
        // Requesting HTML page
        cityName = cityName.substring(0, 1).toUpperCase() + cityName.substring(1);
        String command = "curl https://www.numbeo.com/cost-of-living/in/" + cityName + "/";
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));

        if(System.getProperty("os.name").startsWith("Windows")) // Windows
            processBuilder.directory(new File("C:\\"));
        else // Linux
            processBuilder.directory(new File("~"));
        Process process = processBuilder.start();

        InputStream inputStream = process.getInputStream();
        String data = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
        process.destroy();

        // Processing the html code and creating an instance
        return parseRestaurantsHTMLCode(data, cityName);
    }
}
