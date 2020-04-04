package com.zman.znetwork.models.friends;

import com.zman.znetwork.models.users.AppUser;

public class Friend extends AppUser {

    private boolean friend;

    public Friend(int id, String email, String regDate, String username, String token, byte validate, String password, String last_login) {
        super(id, email, regDate, username, token, validate, password, last_login);
    }

    public Friend(int id, String email, String regDate, String username, String last_login, int usr_id) {
        super(id, email, regDate, username, null, (byte)0, null, last_login);
        friend = usr_id != 0;
    }

    public boolean isFriend() {
        return friend;
    }

    public void setFriend(boolean friend) {
        this.friend = friend;
    }
}
