package com.bfourclass.euopendata.user.forms;

public class UserLoginForm {
    public final String username;
    public final String password;

    public UserLoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean isValid() {
        return FormValidator.isValidLoginForm(this);
    }
}
