package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.hotel.HotelModel;
import com.bfourclass.euopendata.security.SimpleHashingAlgo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
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
    private String profilePhotoLink;
    private boolean isActivated = false;

    @ManyToMany
    private final Set<HotelModel> hotels = new HashSet<>();

    public UserModel(String username, String email, String password, String profilePhotoLink) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.profilePhotoLink = profilePhotoLink;
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

    public String getProfilePhotoLink() {
        return profilePhotoLink;
    }

    public void setProfilePhotoLink(String profilePhotoLink) {
        this.profilePhotoLink = profilePhotoLink;
    }

    public boolean hasHotel(String hotelName) {
        if (this.hotels.isEmpty())
            return false;
        for (HotelModel hotelModel : this.hotels) {
            if (hotelModel.getHotelName().equals(hotelName))
                return true;
        }
        return false;
    }

    public void addHotel(HotelModel hotelModel) {
        hotels.add(hotelModel);
    }

    public void deleteUserHotel(String locationName) {
        this.hotels.removeIf(hotelModel -> hotelModel.getHotelName().equals(locationName));
    }

    public List<HotelModel> getUserHotels() {
        return List.copyOf(hotels);
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void activateUser() {
        isActivated = true;
    }

    public boolean checkUserPassword(String password) {
        return this.password.equals(SimpleHashingAlgo.hash(password));
    }
}
