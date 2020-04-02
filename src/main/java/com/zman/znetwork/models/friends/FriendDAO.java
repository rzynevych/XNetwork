package com.zman.znetwork.models.friends;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;

public class FriendDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ArrayList<Friend> getFriendsForUser(int userID){

        String sql = "SELECT user_id, email, reg_date, username, token, validate, password, last_login " +
                "FROM friends INNER JOIN users ON user_id=friend_id WHERE usr_id=?";
        try {

           return (ArrayList<Friend>) jdbcTemplate.query(sql, new Object[]{userID}, new FriendMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
