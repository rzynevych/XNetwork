package com.rz.xnetwork.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long chatID;
    Long userID1;
    Long userID2;
    @Temporal(TemporalType.TIMESTAMP)
    Date lastUsed;

    public Chat(){}

    public Chat(Long id1, Long id2, Date date) {
        this.userID1 = id1;
        this.userID2 = id2;
        this.lastUsed = date;
    }
}
