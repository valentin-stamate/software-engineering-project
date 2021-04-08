package com.bfourclass.euopendata.user.auth;

import com.bfourclass.euopendata.user.UserService;

public interface SecurityContext {

    boolean isValid(String token, UserService userService);

    String generateToken(String username);

    void authenticateUser(String username, String token);

    void removeToken();
}
