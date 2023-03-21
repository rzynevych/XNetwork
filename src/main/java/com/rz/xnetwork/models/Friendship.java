package com.rz.xnetwork.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long noteID;
    Integer usrID;
    Integer friendID;
    @Temporal(TemporalType.TIMESTAMP)
    Date addDate;

    public Friendship() {}

    public Friendship(int usrID, int friendID) {
        this.usrID = usrID;
        this.friendID = friendID;
    }

    public Long getNoteID() {
        return noteID;
    }

    public void setNoteID(Long noteID) {
        this.noteID = noteID;
    }

    public Integer getUsrID() {
        return usrID;
    }

    public void setUsrID(Integer usrID) {
        this.usrID = usrID;
    }

    public Integer getFriendID() {
        return friendID;
    }

    public void setFriendID(Integer friendID) {
        this.friendID = friendID;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
}
