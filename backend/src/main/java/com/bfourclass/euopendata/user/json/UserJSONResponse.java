package com.bfourclass.euopendata.user.json;

public class UserJSONResponse {

    public final Long userId;
    public final String username;
    public final String email;
    public final String profilePhotoLink;

    public UserJSONResponse(Long userId, String username, String email, String profilePhotoLink) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.profilePhotoLink = profilePhotoLink;
    }
}
