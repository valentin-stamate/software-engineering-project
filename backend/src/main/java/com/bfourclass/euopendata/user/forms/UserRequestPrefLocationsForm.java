package com.bfourclass.euopendata.user.forms;

public class UserRequestPrefLocationsForm {
    String userToken;
    UserRequestPrefLocationsForm(String userToken){
        this.userToken=userToken;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
