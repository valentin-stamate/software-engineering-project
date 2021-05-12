package com.bfourclass.euopendata.notification;

import javax.persistence.*;

@Entity
@Table(name = "notifications")
public class NotificationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean read;

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
