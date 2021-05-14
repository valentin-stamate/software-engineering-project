package com.bfourclass.euopendata.notification;

import com.bfourclass.euopendata.user.UserModel;
import com.bfourclass.euopendata.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public void addUserNotification(UserModel userModel, NotificationModel notificationModel) {
        userModel.getNotifications().add(notificationModel);
        userRepository.save(userModel);
    }

    public void markAsRead(NotificationModel notificationModel) {
        notificationModel.markAsRead();
        notificationRepository.save(notificationModel);
    }

    public NotificationModel getById(long notificationId) {
        if(notificationRepository.findById(notificationId).isPresent())
            return notificationRepository.findById(notificationId).get();
        return null;
    }

    public void save(NotificationModel notification) {
        notificationRepository.save(notification);
    }

    public List<NotificationModel> getUserNotifications(UserModel userModel) {
        return userModel.getNotifications();
    }

    public boolean deleteUserNotification(UserModel userModel, NotificationModel notificationModel) {
        if (!userModel.deleteNotification(notificationModel)) {
            return false;
        }

        userRepository.save(userModel);
        notificationRepository.delete(notificationModel);

        return true;
    }
}
