package com.bfourclass.euopendata;

import com.bfourclass.euopendata.security.StringGenerator;
import org.junit.jupiter.api.Test;

public class RandomStringTest {
    @Test
    void getString() {
        System.out.println(StringGenerator.generate());
    }
}
