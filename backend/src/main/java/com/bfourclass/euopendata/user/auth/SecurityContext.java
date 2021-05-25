package com.bfourclass.euopendata.user.auth;

import com.bfourclass.euopendata.user.UserModel;
import com.bfourclass.euopendata.user.UserService;

public interface SecurityContext {

    String extractUsername(String token);

    boolean exists(String token);

    boolean isValid(String token, UserService userService);

    String generateToken(UserModel user);

    String authenticateUserReturnToken(UserModel user);

    void removeToken(String token);
}
