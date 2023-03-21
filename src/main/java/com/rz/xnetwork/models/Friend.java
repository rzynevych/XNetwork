package com.rz.xnetwork.models;

import javax.persistence.Entity;

@Entity
public class Friend extends AppUser {

    private Integer friend;

    public Integer getFriend() {
        return friend;
    }

    public void setFriend(Integer friend) {
        this.friend = friend;
    }

    public boolean isFriend() {
        return friend != null && friend > 0;
    }
}
