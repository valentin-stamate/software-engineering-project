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
        AirPollution airPollution = new AirPollution(location);

        index = data.indexOf("\"aqi\"");
        if (index != -1)
            airPollution.setAirQualityIndex(Integer.valueOf(data.substring(
                    index, index + 9
                    ).split(":")[1].replaceAll("[^\\d]", "")
            ));

        index = data.indexOf("\"pm10\":{\"v\":");
        if (index != -1)
            airPollution.setPm10ValueIndex(Integer.valueOf(data.substring(
                    index, index + 16
                    ).split(":")[2].replaceAll("[^\\d]", "")
            ));

        index = data.indexOf("\"pm25\":{\"v\":");
        if (index != -1)
            airPollution.setPm25ValueIndex(Integer.valueOf(data.substring(
                    index, index + 16
                    ).split(":")[2].replaceAll("[^\\d]", "")
            ));

        index = data.indexOf("\"o3\":{\"v\":");
        if (index != -1)
            airPollution.setO3ValueIndex(Float.valueOf(data.substring(
                    index, index + 16
                    ).split(":")[2].replaceAll("[^.\\d]", "")
            ));

        index = data.indexOf("\"no2\":{\"v\":");
        if (index != -1)
            airPollution.setNO2ValueIndex(Float.valueOf(data.substring(
                    index, index + 16
                    ).split(":")[2].replaceAll("[^.\\d]", "")
            ));

        index = data.indexOf("\"so2\":{\"v\":");
        if (index != -1)
            airPollution.setSO2ValueIndex(Float.valueOf(data.substring(
                    index, index + 16
                    ).split(":")[2].replaceAll("[^.\\d]", "")
            ));

        index = data.indexOf("\"co\":{\"v\":");
        if (index != -1)
            airPollution.setCOValueIndex(Float.valueOf(data.substring(
                    index, index + 16
                    ).split(":")[2].replaceAll("[^.\\d]", "")
            ));

        index = data.indexOf("\"p\":{\"v\":");
        if (index != -1)
            airPollution.setAirPressure(Float.valueOf(data.substring(
                    index, index + 15
                    ).split(":")[2].replaceAll("[^.\\d]", "")
            ));

        index = data.indexOf("\"h\":{\"v\":");
        if (index != -1)
            airPollution.setAirHumidity(Float.valueOf(data.substring(
                    index, index + 15
                    ).split(":")[2].replaceAll("[^\\d.]", "")
            ));

        index = data.indexOf("\"w\":{\"v\":");
        if (index != -1)
            airPollution.setWindSpeed(Float.valueOf(data.substring(
                    index, index + 15
                    ).split(":")[2].replaceAll("[^\\d.]", "")
            ));

        return airPollution;
    }
}
