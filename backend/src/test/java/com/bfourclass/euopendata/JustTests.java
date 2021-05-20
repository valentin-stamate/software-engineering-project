package com.bfourclass.euopendata;

import com.bfourclass.euopendata.external_api.ExternalAPI;
import com.bfourclass.euopendata.external_api.covid.CovidStatisticsAPI;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.stream.Collectors;

public class JustTests {

    @Test
    public void test() throws InterruptedException {
//        CovidStatisticsAPI.downloadData();
        CovidStatisticsAPI ins = new CovidStatisticsAPI();
        Thread thread = new Thread(ins);
        thread.start();

        Thread.sleep(5000);

        System.out.println(ExternalAPI.getCovidStatistics("Afghanistan"));
    }

    @Test
    public void commandRunnerTest() throws IOException, InterruptedException {
        String command = "curl https://www.numbeo.com/pollution/in/Iasi/";

        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));

        if (System.getProperty("os.name").startsWith("Windows")) // Windows
            processBuilder.directory(new File("C:\\"));
        else // Linux
            processBuilder.directory(new File("."));

        Process process = processBuilder.start();

        InputStream inputStream = process.getInputStream();
        String data = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));

        System.out.println(data);
        System.out.println("Ana are mere");

        process.destroy();
    }

}
