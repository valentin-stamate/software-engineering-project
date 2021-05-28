package com.bfourclass.smartbooking.user_history;

import com.bfourclass.smartbooking.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHistoryService
{
    private final UserHistoryRepository userHistoryRepository;

    @Autowired
    public UserHistoryService(UserHistoryRepository userHistoryRepository)
    {
        this.userHistoryRepository = userHistoryRepository;
    }

    public List<UserHistoryModel> getUserHistory(UserModel userModel)
    {
        return userHistoryRepository.getAll();
    }
}
