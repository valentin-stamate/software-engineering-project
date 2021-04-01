package com.bfourclass.euopendata.user.forms;

import com.bfourclass.euopendata.security.SimpleHashingAlgo;
import com.bfourclass.euopendata.user.User;

public class UserRegisterForm {
    private String displayName;
    private String email;
    private String username;
    private String password;
    private String profilePhotoLink;

    public UserRegisterForm(String displayName, String email, String username, String password, String profilePhotoLink) {
        this.displayName = displayName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.profilePhotoLink = profilePhotoLink;
    }

    public User toUser() {
        return new User(username, email, SimpleHashingAlgo.hash(password), displayName, profilePhotoLink);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePhotoLink() {
        return profilePhotoLink;
    }

    public void setProfilePhotoLink(String profilePhotoLink) {
        this.profilePhotoLink = profilePhotoLink;
    }
}
