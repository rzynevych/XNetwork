package com.zman.znetwork.models.messages;

import com.zman.znetwork.models.users.AppUser;
import com.zman.znetwork.models.users.AppUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class MessageDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public MessageDAO () {

    }

    public List<Message> selectItems(String key, int value, int offset) {

        String sql = "SELECT message_id,parent_id,receiver,username,text,DATE_FORMAT(`date`, '%H:%i') FROM messages WHERE " + key
                + "=? ORDER BY message_id DESC LIMIT ?, 50";

        try {
            return jdbcTemplate.query(sql, new Object[]{value, offset}, new MessageMapper());
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Message> selectPosts(int id, int offset) {

        String sql = "SELECT message_id,parent_id,receiver,username,text,DATE_FORMAT(`date`, '%H:%i') " +
                "FROM messages INNER JOIN friends ON friend_id=parent_id WHERE usr_id=? " +
                "AND receiver=0 ORDER BY message_id DESC LIMIT ?, 50";

        try {
            return jdbcTemplate.query(sql, new Object[]{id, offset}, new MessageMapper());
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<AppUser> getChatUsers(int user_id, int offset) {

        String sql = "SELECT user_id, email, reg_date, users.username, token, validate, password, last_login " +
                "FROM messages INNER JOIN users ON (user_id=receiver OR user_id=parent_id) " +
                "WHERE (parent_id=? OR receiver=?) AND NOT receiver=0 " +
                "GROUP BY user_id ORDER BY MAX(message_id) DESC LIMIT ?, 50";
        try {
            return jdbcTemplate.query(sql, new Object[]{user_id, user_id, offset}, new AppUserMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Message> getMessagesForChat(int id1, int id2, int offset) {

        String sql = "SELECT message_id,parent_id,receiver,username,text,DATE_FORMAT(`date`, '%H:%i') FROM messages " +
                "WHERE (parent_id=? AND receiver=?) OR (parent_id=? AND receiver=?) ORDER BY message_id DESC LIMIT ?, 50";
        try {
            List<Message> messages = jdbcTemplate.query(sql, new Object[]{id1, id2, id2, id1, offset}, new MessageMapper());
            return messages;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Message> getMessagesForUpdate(int id1, int id2, long last) {

        String sql = "SELECT message_id,parent_id,receiver,username,text,DATE_FORMAT(`date`, '%H:%i') FROM messages " +
                "WHERE (parent_id=? AND receiver=?) AND message_id > ? ORDER BY message_id ASC LIMIT 50";
        try {
            return jdbcTemplate.query(sql, new Object[]{id2, id1, last}, new MessageMapper());
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public long insert(int parent_id, String text, String username, int receiver) {

        String sql = "INSERT INTO messages(parent_id,receiver,text,username) VALUES(?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(sql, new String[] {"message_id"});
                    ps.setString(1, Integer.toString(parent_id));
                    ps.setString(2, Integer.toString(receiver));
                    ps.setString(3, text);
                    ps.setString(4, username);
                    return ps;
                },
                keyHolder);
        return keyHolder.getKey().longValue();
    }
}
