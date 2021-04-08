package com.bfourclass.euopendata.user.auth;

public class AuthSuccessResponse {
    private String message;
    private String token;

    public AuthSuccessResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}
