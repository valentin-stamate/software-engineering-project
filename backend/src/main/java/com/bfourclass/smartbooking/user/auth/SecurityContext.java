package com.bfourclass.smartbooking.user.auth;

import com.bfourclass.smartbooking.user.UserModel;
import com.bfourclass.smartbooking.user.UserService;

public interface SecurityContext {

    String extractUsername(String token);

    boolean exists(String token);

    boolean isValid(String token, UserService userService);

    String generateToken(UserModel user);

    String authenticateUserReturnToken(UserModel user);

    void removeToken(String token);
}
