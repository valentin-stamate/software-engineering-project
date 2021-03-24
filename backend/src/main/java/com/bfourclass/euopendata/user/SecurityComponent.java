package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.hashingAlgo.HashingAlgo;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;

public class SecurityComponent {
    private EmbeddedDatabaseConnection connection;
    private HashingAlgo hashingAlgo;

    public boolean isValidUser(String username, String password){
        return true;
    }
    public boolean canRegister(String username, String password){
        return true;
    }
}
