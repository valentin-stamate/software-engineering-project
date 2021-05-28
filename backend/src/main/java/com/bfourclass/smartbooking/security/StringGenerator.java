package com.bfourclass.smartbooking.security;

import java.util.Locale;

public abstract class StringGenerator {

    private static final int DEFAULT_LENGTH = 64;

    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lower = upper.toLowerCase(Locale.ROOT);
    private static final String digits = "0123456789";

    private static final String characters = upper + lower + digits;

    public static String generate(int length) {
        char[] buffer = new char[length];

        for (int i = 0; i < length; i++) {
            int randomPosition = generateRandomInt(characters.length());
            buffer[i] = characters.charAt(randomPosition);
        }

        return new String(buffer);
    }

    public static String generate() {
        return generate(DEFAULT_LENGTH);
    }

    private static int generateRandomInt(int max) {
        return (int)(Math.random() * 10000) % max;
    }

}
