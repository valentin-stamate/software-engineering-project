package com.bfourclass.euopendata.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    @Query("SELECT u FROM UserModel u WHERE u.email = ?1")
    UserModel findUserByEmail(String email);

    @Query("SELECT u FROM UserModel u WHERE u.username = ?1")
    UserModel findUserByUsername(String username);
}
