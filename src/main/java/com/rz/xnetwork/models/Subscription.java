package com.rz.xnetwork.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long subscriptionID;
    Long subscriberID;
    Long userID;
    @Temporal(TemporalType.TIMESTAMP)
    Date date;

    public Subscription() {}

    public Subscription(Long userID, Long subscriberID) {
        this.userID = userID;
        this.subscriberID = subscriberID;
    }
}
