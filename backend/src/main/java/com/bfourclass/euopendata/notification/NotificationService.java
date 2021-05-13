package com.bfourclass.euopendata.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void deleteNotification(long notificationId) {
        NotificationModel notificationModel = getById(notificationId);
        if(notificationModel != null)
            notificationRepository.delete(notificationModel);
    }

    public void createNotification(long notificationId, String notificationMessage) {
        NotificationModel model = new NotificationModel(notificationMessage);
        notificationRepository.save(model);
    }

    public void updateNotification(long notificationId, String newNotificationMessage) {
        NotificationModel model = getById(notificationId);
        if(model != null) {
            model.setMessage(newNotificationMessage);
            model.setRead(false);
            notificationRepository.save(model);
        }
    }

    public void markAsRead(long notificationId) {
        NotificationModel notificationModel = getById(notificationId);
        if(notificationModel != null)
            notificationModel.setRead(true);
    }

    public NotificationModel getById(long notificationId) {
        if(notificationRepository.findById(notificationId).isPresent())
            return notificationRepository.findById(notificationId).get();
        return null;
    }

    public void save(NotificationModel notification) {
        notificationRepository.save(notification);
    }
}
