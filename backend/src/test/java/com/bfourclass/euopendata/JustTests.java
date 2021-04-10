package com.bfourclass.euopendata;

import com.bfourclass.euopendata.external_api.ExternalAPI;
import org.junit.jupiter.api.Test;

public class JustTests {

    @Test
    public void test() {
        System.out.println(ExternalAPI.getCovidInformation("iasi"));
    }

}
