package com.bfourclass.euopendata.external_api;

import com.bfourclass.euopendata.external_api.instance.numbeo_data.PollutionStatistics;

import java.io.*;
import java.text.Normalizer;
import java.util.stream.Collectors;

abstract class AQICNDataAPI
{
    protected static PollutionStatistics requestAirPollution(String location, PollutionStatistics pollutionStatistics) throws IOException
    {
        location = location.replace(" ", "%20");
        /* Getting rid of diacritics */
        location = Normalizer.normalize(location, Normalizer.Form.NFD);
        location = location.replaceAll("\\p{M}", "");

        System.out.println(location);

        // Fetching data
        String command = "curl https://api.waqi.info/feed/" + location + "/?token=d69eed0409fd85ce77805bc2b5b5217c7fcc456f";
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

        int index;
        // Verifying if a correct location was given
        index = data.indexOf("\"status\":\"error\"");
        if(index != -1)
            return null;

        // Processing the fetched data
        index = data.indexOf("\"aqi\"");
        if (index != -1)
            pollutionStatistics.setAirQualityIndex(Integer.valueOf(data.substring(
                    index, index + 9
                    ).split(":")[1].replaceAll("[^\\d]", "")
            ));

        index = data.indexOf("\"pm10\":{\"v\":");
        if (index != -1)
            pollutionStatistics.setPm10ValueIndex(Integer.valueOf(data.substring(
                    index, index + 16
                    ).split(":")[2].replaceAll("[^\\d]", "")
            ));

        index = data.indexOf("\"pm25\":{\"v\":");
        if (index != -1)
            pollutionStatistics.setPm25ValueIndex(Integer.valueOf(data.substring(
                    index, index + 16
                    ).split(":")[2].replaceAll("[^\\d]", "")
            ));

        index = data.indexOf("\"o3\":{\"v\":");
        if (index != -1)
            pollutionStatistics.setO3ValueIndex(Float.valueOf(data.substring(
                    index, index + 16
                    ).split(":")[2].replaceAll("[^.\\d]", "")
            ));

        index = data.indexOf("\"no2\":{\"v\":");
        if (index != -1)
            pollutionStatistics.setNO2ValueIndex(Float.valueOf(data.substring(
                    index, index + 16
                    ).split(":")[2].replaceAll("[^.\\d]", "")
            ));

        index = data.indexOf("\"so2\":{\"v\":");
        if (index != -1)
            pollutionStatistics.setSO2ValueIndex(Float.valueOf(data.substring(
                    index, index + 16
                    ).split(":")[2].replaceAll("[^.\\d]", "")
            ));

        index = data.indexOf("\"co\":{\"v\":");
        if (index != -1)
            pollutionStatistics.setCOValueIndex(Float.valueOf(data.substring(
                    index, index + 16
                    ).split(":")[2].replaceAll("[^.\\d]", "")
            ));

        index = data.indexOf("\"p\":{\"v\":");
        if (index != -1)
            pollutionStatistics.setAirPressure(Float.valueOf(data.substring(
                    index, index + 15
                    ).split(":")[2].replaceAll("[^.\\d]", "")
            ));

        index = data.indexOf("\"h\":{\"v\":");
        if (index != -1)
            pollutionStatistics.setAirHumidity(Float.valueOf(data.substring(
                    index, index + 15
                    ).split(":")[2].replaceAll("[^\\d.]", "")
            ));

        index = data.indexOf("\"w\":{\"v\":");
        if (index != -1)
            pollutionStatistics.setWindSpeed(Float.valueOf(data.substring(
                    index, index + 15
                    ).split(":")[2].replaceAll("[^\\d.]", "")
            ));

        return pollutionStatistics;
    }
}
