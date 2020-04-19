package com.zman.znetwork.models.friends;

import com.zman.znetwork.models.users.AppUser;

public class Friend extends AppUser {

    private boolean friend;

    public boolean isFriend() {
        return friend;
    }

    public void setFriend(boolean friend) {
        this.friend = friend;
    }
}
