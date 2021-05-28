package com.bfourclass.smartbooking.user_verification;

import com.bfourclass.smartbooking.user.UserModel;

import javax.persistence.*;

@Entity
@Table(name = "UserVerification")
public class UserVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String verificationKey;

    @OneToOne(cascade = CascadeType.ALL)
    public UserModel userModel;

    public UserVerification(UserModel userModel, String verificationKey) {
        this.userModel = userModel;
        this.verificationKey = verificationKey;
    }

    public UserVerification() { }

}
