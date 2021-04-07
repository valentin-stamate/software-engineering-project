package com.bfourclass.euopendata.user.auth;

public interface SecurityContext {

    boolean isValid(String token);

    String generateToken();

    void authenticateUser(String username, String token);

    void removeToken();
}
