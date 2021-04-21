package com.bfourclass.euopendata.user_history;


import com.bfourclass.euopendata.user.UserModel;

import javax.persistence.*;

@Entity
@Table(name = "user_history")
public class UserHistoryModel {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private UserModel userModel;

    private String searchedLocationName;

    public String getSearchedLocation() {
        return searchedLocationName;
    }

    public void setSearchedLocation(String searchedLocation) {
        this.searchedLocationName = searchedLocation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
