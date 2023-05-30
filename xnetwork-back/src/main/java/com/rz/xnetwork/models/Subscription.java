package com.rz.xnetwork.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subscriptionId;
    private Long subscriberId;
    private Long userId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Subscription() {}

    public Subscription(Long subscriberId, Long userId) {
        this.subscriberId = subscriberId;
        this.userId = userId;
    }
}
