package com.bfourclass.euopendata.user_verification;

import com.bfourclass.euopendata.user.User;

import javax.persistence.*;

@Entity
@Table(name = "UserVerification")
public class UserVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String verificationKey;

    @OneToOne(cascade = CascadeType.ALL)
    public User user;

    public UserVerification(User user, String verificationKey) {
        this.user = user;
        this.verificationKey = verificationKey;
    }

    public UserVerification() { }

}
