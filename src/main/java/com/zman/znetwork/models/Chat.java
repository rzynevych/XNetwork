package com.zman.znetwork.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long chatID;
    int id1;
    int id2;
    @Temporal(TemporalType.TIMESTAMP)
    Date lastUsed;

    public Chat(){}

    public Chat(int id1, int id2, Date date) {
        this.id1 = id1;
        this.id2 = id2;
        this.lastUsed = date;
    }

    public long getChatID() {
        return chatID;
    }

    public void setChatID(long chatID) {
        this.chatID = chatID;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
}
