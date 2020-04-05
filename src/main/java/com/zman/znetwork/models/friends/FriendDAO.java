package com.zman.znetwork.models.friends;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Friend> getFriendsForUser(int userID, int offset) {

        String sql = "select user_id, email, reg_date, username, last_login, friend_id from users inner join " +
                "(select * from friends where usr_id=?) t1 on t1.friend_id=users.user_id  LIMIT ?, 50";
        try {

           return jdbcTemplate.query(sql, new Object[]{userID, offset}, new FriendMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Friend> getUsersByQuery(String query, int user_id, int offset) {

        String sql = "select user_id, email, reg_date, username, last_login, friend_id from users left join " +
                "(select * from friends where usr_id=?) t1 on t1.friend_id=users.user_id WHERE NOT user_id=? " +
                "AND (username LIKE ? OR email LIKE ?) LIMIT ?, 50";
        try {
            if (query.equals(""))
                query = "%";
            return jdbcTemplate.query(sql, new Object[]{user_id, user_id, query, query, offset}, new FriendMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void insertFriend(int id1, int id2) throws FriendInsertException {

        String check = "SELECT count(*) FROM friends WHERE usr_id=? AND friend_id=?";
        String sql = "INSERT INTO friends (usr_id, friend_id) VALUES (?,?)";
        int count;

        try {
            count = jdbcTemplate.queryForObject(check, new Object[]{id1, id2}, Integer.class);
            if (count == 0)
                jdbcTemplate.update(sql, id1, id2);
            else throw new FriendInsertException("Note in table already exists");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeFriend(int id1, int id2) {

        String sql = "DELETE FROM friends WHERE usr_id=? AND friend_id=?";

        try {
            jdbcTemplate.update(sql, id1, id2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
