package com.bfourclass.euopendata.user.forms;

public class UserLoginForm {
    private final String username;
    private final String password;

    public UserLoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
