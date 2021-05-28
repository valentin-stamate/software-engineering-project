package com.bfourclass.smartbooking.user.json;

import com.bfourclass.smartbooking.security.SimpleHashingAlgo;
import com.bfourclass.smartbooking.user.UserModel;
import com.bfourclass.smartbooking.user.forms.FormValidator;

public class OwnerRegisterJSONRequest {
    public final String username;
    public final String email;
    public final String password;
    public final String profilePhotoLink;

    public OwnerRegisterJSONRequest(String username, String email, String password, String profilePhotoLink) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.profilePhotoLink = profilePhotoLink;
    }
    public UserModel toUser() {
        return new UserModel(username, email, SimpleHashingAlgo.hash(password), profilePhotoLink,true);
    }

    public String isValid() {
        return FormValidator.isValidOwnerRegisterForm(this);
    }
}
