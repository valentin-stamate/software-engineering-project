package com.bfourclass.euopendata.notification;

import com.bfourclass.euopendata.notification.json.Notification;
import com.bfourclass.euopendata.requests.APIError;
import com.bfourclass.euopendata.requests.APISuccess;
import com.bfourclass.euopendata.user.UserModel;
import com.bfourclass.euopendata.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationController {

    private final UserService userService;
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService, UserService userService) {
        this.notificationService = notificationService;
        this.userService = userService;
    }

    @GetMapping("/notifications")
    public ResponseEntity<Object> getNotifications(@RequestHeader(name = "Authorization") String token) {
        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        UserModel userModel = userService.getUserFromToken(token);

        List<Notification> notifications = userService.getUserNotifications(userModel);

        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @DeleteMapping("/notifications")
    public ResponseEntity<Object> deleteNotification(@RequestHeader(name = "Authorization") String token, @RequestParam(name = "notification_id") long notificationId) {
        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        UserModel userModel = userService.getUserFromToken(token);

        if (userService.deleteUserNotification(userModel, notificationId)) {
            notificationService.deleteNotification(notificationId);
            return new ResponseEntity<>(new APISuccess("Notification deleted successfully"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new APIError("Error deleting notification"), HttpStatus.OK);
    }

    @PostMapping("/notifications")
    public ResponseEntity<Object> markAsRead(@RequestHeader(name = "Authorization") String token, @RequestParam(name = "notification_id") long notificationId) {
        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        notificationService.markAsRead(notificationId);

        return new ResponseEntity<>(new APISuccess("Notification marked as read"), HttpStatus.OK);
    }
}
