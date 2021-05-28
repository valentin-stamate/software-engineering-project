package com.bfourclass.smartbooking.user.json;

public class UserChangePassJSon {
    public String oldPassword;
    public String newPassword;

    public UserChangePassJSon(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
