package com.bfourclass.euopendata.notification;

import javax.persistence.*;

@Entity
@Table(name = "notifications")
public class NotificationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
}
