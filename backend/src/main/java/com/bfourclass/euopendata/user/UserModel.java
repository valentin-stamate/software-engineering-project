package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.hotel.HotelModel;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    private String displayName;
    private String profilePhotoLink;
    private boolean isActivated = false;

    @ManyToMany
    private Set<HotelModel> hotels;

    public UserModel(String username, String email, String password, String profilePhotoLink, boolean isActivated) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.hotels = null;
        this.profilePhotoLink = profilePhotoLink;
        this.isActivated = isActivated;
    }

    public UserModel() { }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getProfilePhotoLink() {
        return profilePhotoLink;
    }

    public void setProfilePhotoLink(String profilePhotoLink) {
        this.profilePhotoLink = profilePhotoLink;
    }

    public boolean existingLocation(String hotelName) {
        if (this.hotels.isEmpty())
            return false;
        for (HotelModel hotelModel : this.hotels) {
            if (hotelModel.getHotelName().equals(hotelName))
                return true;
        }
        return false;
    }

    public void addLocationToFavourites(HotelModel hotelModel) {
        hotels.add(hotelModel);
    }

    public void deleteLocationFromFavourites(String locationName) {
        this.hotels.removeIf(hotelModel -> hotelModel.getHotelName().equals(locationName));
    }

    public Set<HotelModel> getLocations() {
        return hotels;
    }

    public boolean activated() {
        return isActivated;
    }

    public void activateUser() {
        isActivated = true;
    }
}
