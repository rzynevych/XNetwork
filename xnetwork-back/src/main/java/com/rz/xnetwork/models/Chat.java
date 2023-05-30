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
    private Long chatId;
    private Long userId1;
    private Long userId2;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUsed;

    public Chat(){}

    public Chat(Long id1, Long id2, Date date) {
        this.userId1 = id1;
        this.userId2 = id2;
        this.lastUsed = date;
    }
}
