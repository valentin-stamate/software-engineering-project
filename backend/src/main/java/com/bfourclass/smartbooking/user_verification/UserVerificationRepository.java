package com.bfourclass.smartbooking.user_verification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVerificationRepository extends JpaRepository<UserVerification, Long> {

    @Query("SELECT u FROM UserVerification u WHERE u.verificationKey = ?1")
    UserVerification findInstanceByKey(String key);

}
