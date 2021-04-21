package com.bfourclass.euopendata;

import com.bfourclass.euopendata.external_api.ExternalAPI;
import com.bfourclass.euopendata.external_api.covid.CovidStatisticsAPI;
import org.junit.jupiter.api.Test;

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

}
