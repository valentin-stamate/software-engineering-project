package com.bfourclass.euopendata.user.json;

import com.bfourclass.euopendata.user.forms.FormValidator;

public class UserLoginJSON {
    public final String username;
    public final String password;

    public UserLoginJSON(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String isValid() {
        return FormValidator.isValidLoginForm(this);
    }
}
