package com.bfourclass.smartbooking.user.json;

import com.bfourclass.smartbooking.user.forms.FormValidator;

public class UserLoginJSON {
    public final String login;
    public final String password;

    public UserLoginJSON(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String isValid() {
        return FormValidator.isValidLoginForm(this);
    }
}
