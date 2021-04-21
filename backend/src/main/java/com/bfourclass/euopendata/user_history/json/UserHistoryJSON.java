package com.bfourclass.euopendata.user_history.json;

public class UserHistoryJSON {
    public final long id;
    public final String query;

    public UserHistoryJSON(long id, String query) {
        this.id = id;
        this.query = query;
    }
}
