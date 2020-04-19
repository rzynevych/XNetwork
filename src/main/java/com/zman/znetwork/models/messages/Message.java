package com.zman.znetwork.models.messages;

import javax.persistence.*;
import java.util.Date;

public class Message {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long messageID;
    private Integer parentID;
    private Integer receiver;
    private String username;
    private String text;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getMessageID() {
        return messageID;
    }

    public void setMessageID(Long messageID) {
        this.messageID = messageID;
    }

    public Integer getParentID() {
        return parentID;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
