package com.bfourclass.euopendata.user.json;

import com.bfourclass.euopendata.user.forms.FormValidator;

public class UserLoginJSONRequest {
    public final String username;
    public final String password;

    public UserLoginJSONRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean isValid() {
        return FormValidator.isValidLoginForm(this);
    }
}
