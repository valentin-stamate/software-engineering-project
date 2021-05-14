package com.bfourclass.euopendata.notification;

import com.bfourclass.euopendata.notification.json.Notification;
import com.bfourclass.euopendata.requests.ResponseError;
import com.bfourclass.euopendata.requests.ResponseSucces;
import com.bfourclass.euopendata.user.UserModel;
import com.bfourclass.euopendata.user.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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

        List<Notification> notifications = notificationService.getUserNotifications(userModel);

        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @DeleteMapping("/notification")
    public ResponseEntity<Object> deleteNotification(@RequestHeader(name = "Authorization") String token,
                                                     @RequestParam(name = "id") long notificationId) {
        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        UserModel userModel = userService.getUserFromToken(token);
        NotificationModel notificationModel = notificationService.getById(notificationId);

        if (notificationModel == null) {
            return new ResponseEntity<>(new ResponseError("You don't have this notification"), HttpStatus.BAD_REQUEST);
        }

        if (!notificationService.deleteUserNotification(userModel, notificationModel)) {
            return new ResponseEntity<>(new ResponseError("Error deleting notification"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new ResponseSucces("Notification deleted successfully"), HttpStatus.OK);
    }

    @PutMapping("/notification")
    public ResponseEntity<Object> markAsRead(@RequestHeader(name = "Authorization") String token, @RequestParam(name = "id") long notificationId) {
        ResponseEntity<Object> errorResponse = userService.checkUserToken(token);
        if (errorResponse != null) {
            return errorResponse;
        }

        NotificationModel notificationModel = notificationService.getById(notificationId);
        notificationService.markAsRead(notificationModel);

        return new ResponseEntity<>(new ResponseSucces("Notification marked as read"), HttpStatus.OK);
    }

    @MessageMapping("/notification")
    @SendTo("/view/notifications")
    public Notification sendNotification(Notification notification) {

        NotificationModel notificationModel = notificationService.getById(notification.id);

        List<UserModel> userModels = userService.getAll();
        for (UserModel userModel : userModels) {
            notificationService.addUserNotification(userModel, notificationModel);
        }

        return notification;
    }
}
