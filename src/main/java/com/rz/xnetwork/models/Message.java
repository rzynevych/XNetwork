package com.rz.xnetwork.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long messageId;
    private Long senderId;
    private Long receiver;
    private String senderName;
    private String text;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Message(Long senderId, Long receiver, String senderName, String text) {
        this.senderId = senderId;
        this.receiver = receiver;
        this.senderName = senderName;
        this.text = text;
        date = new Date();
    }
}
