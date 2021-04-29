package com.bfourclass.euopendata.external_api;

import com.bfourclass.euopendata.external_api.instance.aqicn_data.AirPollution;

import java.io.*;
import java.text.Normalizer;
import java.util.stream.Collectors;

abstract class AQICNDataAPI
{
    protected static AirPollution requestAirPollution(String location) throws IOException
    {
        location = location.replace(" ", "%20");
        /* Getting rid of diacritics */
        location = Normalizer.normalize(location, Normalizer.Form.NFD);
        location = location.replaceAll("\\p{M}", "");

        System.out.println(location);

        // Fetching data
        String command = "curl https://api.waqi.info/feed/" + location + "/?token=d69eed0409fd85ce77805bc2b5b5217c7fcc456f";
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));

        processBuilder.directory(new File("C:\\"));
        Process process = processBuilder.start();

        InputStream inputStream = process.getInputStream();
        String data = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
        process.destroy();

        // Processing the fetched data
        AirPollution airPollution = new AirPollution();
        airPollution.location = location;

        airPollution.setAirQualityIndex(Integer.valueOf(data.substring(
                data.indexOf("\"aqi\""),
                data.indexOf("\"aqi\"") + 9).split(":")[1].replaceAll("[^\\d]", "")
        ));
        airPollution.setPm10Value(Integer.valueOf(data.substring(
                data.indexOf("\"pm10\":{\"v\":"),
                data.indexOf("\"pm10\":{\"v\":") + 16).split(":")[2].replaceAll("[^\\d]", "")
        ));
        airPollution.setAirPressure(Integer.valueOf(data.substring(
                data.indexOf("\"p\":{\"v\":"),
                data.indexOf("\"p\":{\"v\":") + 15).split(":")[2].replaceAll("[^\\d]", "")
        ));
        airPollution.setAirHumidity(Float.valueOf(data.substring(
                data.indexOf("\"h\":{\"v\":"),
                data.indexOf("\"h\":{\"v\":") + 15).split(":")[2].replaceAll("[^\\d.]", "")
        ));

        return airPollution;
    }

}
