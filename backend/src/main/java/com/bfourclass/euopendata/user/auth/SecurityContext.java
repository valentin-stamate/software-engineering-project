package com.bfourclass.euopendata.user.auth;

import com.bfourclass.euopendata.user.UserService;

public interface SecurityContext {

    String extractUsername(String token);

    boolean exists(String token);

    boolean isValid(String token, UserService userService);

    String generateToken(String username);

    String authenticateUserReturnToken(String username);

    void removeToken(String token);
}
