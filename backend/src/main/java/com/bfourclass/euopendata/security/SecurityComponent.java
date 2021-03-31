package com.bfourclass.euopendata.security;

import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;

public class SecurityComponent {
    private EmbeddedDatabaseConnection connection;
    private SimpleHashingAlgo hashingAlgo;

    public boolean isValidUser(String username, String password){
        return true;
    }
    public boolean canRegister(String username, String password){
        return true;
    }
}
