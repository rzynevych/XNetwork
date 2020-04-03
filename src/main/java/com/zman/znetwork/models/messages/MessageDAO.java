package com.zman.znetwork.models.messages;

import com.zman.znetwork.models.users.AppUser;
import com.zman.znetwork.models.users.AppUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
            List<Message> items = jdbcTemplate.query(sql, new Object[]{value, offset}, new MessageMapper());
            //Collections.reverse(items);
            return items;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<AppUser> getChatUsers(int user_id, int offset) {

        String sql = "SELECT user_id, email, reg_date, users.username, token, validate, password, last_login FROM messages " +
                "INNER JOIN users ON (user_id=receiver OR user_id=parent_id) " +
                "WHERE (parent_id=? OR receiver=?) AND NOT receiver=0 GROUP BY user_id ORDER BY MAX(message_id) DESC LIMIT ?, 50";
        List<AppUser> chatUsers = jdbcTemplate.query(sql, new Object[]{user_id, user_id, offset}, new AppUserMapper());
        return chatUsers;
    }

    public List<Message> getMessagesForChat(int id1, int id2, int offset) {

        String sql = "SELECT message_id,parent_id,receiver,username,text,DATE_FORMAT(`date`, '%H:%i') FROM messages " +
                "WHERE (parent_id=? AND receiver=?) OR (parent_id=? AND receiver=?) ORDER BY message_id DESC LIMIT ?, 50";

        try {
            List<Message> items = jdbcTemplate.query(sql, new Object[]{id1, id2, id2, id1, offset}, new MessageMapper());
            Collections.reverse(items);
            return items;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insert (int parent_id, String text, String username, int receiver) {

        int update = jdbcTemplate.update("INSERT INTO messages(parent_id,receiver,text,username) VALUES(?,?,?,?)", parent_id, receiver, text, username);

        return update > 0;
    }
}
