package com.bfourclass.euopendata.user.forms;

import com.bfourclass.euopendata.security.SimpleHashingAlgo;
import com.bfourclass.euopendata.user.User;

public class UserRegisterForm {
    private final String displayName;
    private final String email;
    private final String username;
    private final String password;
    private final String profilePhotoLink;

    public UserRegisterForm(String displayName, String email, String username, String password, String profilePhotoLink) {
        this.displayName = displayName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.profilePhotoLink = profilePhotoLink;
    }

    public User toUser() {
        return new User(username, email, SimpleHashingAlgo.hash(password), displayName, profilePhotoLink, false);
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getProfilePhotoLink() {
        return profilePhotoLink;
    }
}
