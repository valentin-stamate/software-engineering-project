package com.bfourclass.euopendata.notification.json;

public class Notification {
    public long id;
    public String message;
    public boolean read = false;

    public Notification(long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Notification(String message) {
        this.message = message;
    }

    public void markAsRead() {
        read = true;
    }

}
