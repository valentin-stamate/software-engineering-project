package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.hotel.HotelModel;
import com.bfourclass.euopendata.hotel_review.HotelReviewModel;
import com.bfourclass.euopendata.security.SimpleHashingAlgo;
import com.bfourclass.euopendata.user_history.UserHistoryModel;

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

    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String profilePhotoLink;
    private boolean isActivated = false;
    private boolean isAdmin = false;

    @OneToMany
    private Set<HotelReviewModel> userReviews = new HashSet<>();

    @OneToMany
    private Set<UserHistoryModel> userHistory = new HashSet<>();

    @ManyToMany
    private final Set<HotelModel> hotels = new HashSet<>();

    public UserModel(String username, String email, String password, String profilePhotoLink, boolean isActivated, boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.profilePhotoLink = profilePhotoLink;
        this.isActivated = isActivated;
        this.isAdmin = isAdmin;
    }

    public UserModel(String username, String email, String password, String profilePhotoLink) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.profilePhotoLink = profilePhotoLink;
    }

    public UserModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getProfilePhotoLink() {
        return profilePhotoLink;
    }

    public boolean hasHotel(long hotelId) {
        if (this.hotels.isEmpty())
            return false;
        for (HotelModel hotelModel : this.hotels) {
            if (hotelModel.getId() == hotelId)
                return true;
        }
        return false;
    }

    public boolean hasHotel(String identity) {
        if (this.hotels.isEmpty())
            return false;
        for (HotelModel hotelModel : this.hotels) {
            if (hotelModel.getHotelName().equals(identity))
                return true;
        }
        return false;
    }

    public Long getId() {
        return id;
    }

    public void addHotel(HotelModel hotelModel) {
        hotels.add(hotelModel);
    }

    public void deleteUserHotel(HotelModel hotelModel) {
        hotels.removeIf(hotel -> hotel.getHotelName().equals(hotelModel.getHotelName()));
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

    public boolean isAdmin() {
        return isAdmin;
    }

    /*
        this function is for testing only 
    * */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean checkUserPassword(String password) {
        return this.password.equals(SimpleHashingAlgo.hash(password));
    }

    public boolean hasAlreadyReviewedHotel(HotelModel hotelModel) {
        for (HotelReviewModel reviewModel : userReviews) {
            if (reviewModel.getHotel().getId().equals(hotelModel.getId())) {
                return true;
            }
        }
        return false;
    }

    public void addHotelReview(HotelReviewModel hotelReviewModel) {
        userReviews.add(hotelReviewModel);
    }

    public void removeHotelReview(HotelReviewModel hotelReviewModel) {
        for (HotelReviewModel review : userReviews) {
            if (review.getId().equals(hotelReviewModel.getId())) {
                userReviews.remove(hotelReviewModel);
                break;
            }
        }
    }

    public void addHistory(UserHistoryModel userHistoryModel) {
        userHistory.add(userHistoryModel);
    }

    public void removeSearchedHotelById(long id){
        for(UserHistoryModel userHistoryModel : userHistory){
            if(userHistoryModel.getId()==id){
                userHistory.remove(userHistoryModel);
                break;
            }
        }
    }
}
