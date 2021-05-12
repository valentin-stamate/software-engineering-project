package com.bfourclass.euopendata.user_history;


import com.bfourclass.euopendata.user.UserModel;

import javax.persistence.*;


@Entity
@Table(name = "user_history")
public class UserHistoryModel {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private UserModel userModel;

    private String searchQuery;

    public UserHistoryModel(String query) {
        this.searchQuery = query;
    }

    public UserHistoryModel() { }

    public String getSearchedLocation() {
        return searchQuery;
    }

    public void setSearchedLocation(String searchedLocation) {
        this.searchQuery = searchedLocation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
