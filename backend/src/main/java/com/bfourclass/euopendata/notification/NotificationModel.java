package com.bfourclass.euopendata.notification;

import javax.persistence.*;

@Entity
@Table(name = "notifications")
public class NotificationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String message;
    private boolean read = false;

    public NotificationModel(String message) {
        this.message = message;
    }

    public NotificationModel() { }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public boolean isRead() {
        return read;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
