package com.bfourclass.euopendata.user_history;

import com.bfourclass.euopendata.user.UserModel;
import com.bfourclass.euopendata.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Controller
public class UserHistoryController {
    private final UserHistoryService userHistoryService;
    private final UserService userService;

    @Autowired
    public UserHistoryController(UserHistoryService userHistoryService, UserService userService) {
        this.userHistoryService = userHistoryService;
        this.userService = userService;
    }

    @GetMapping("/user_history")
    public ResponseEntity<Object> getUserHistory(@RequestHeader(name = "Authorization") String token) {
        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        UserModel userModel = userService.getUserFromToken(token);
        List<UserHistoryModel> userHistory = userHistoryService.getUserHistory(userModel);

        return new ResponseEntity<>(userHistory, HttpStatus.OK);
    }
}
