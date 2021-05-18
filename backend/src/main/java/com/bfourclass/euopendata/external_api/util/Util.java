package com.bfourclass.euopendata.external_api.util;

public class Util {
    public static int toInt(String value) {
        if (value.length() == 0) {
            return 0;
        }
        return (int)Double.parseDouble(value);
    }
}
