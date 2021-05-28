package com.bfourclass.smartbooking.user_history;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistoryModel, Long>
{
    @Query("SELECT h FROM UserHistoryModel h")
    List<UserHistoryModel> getAll();
}
