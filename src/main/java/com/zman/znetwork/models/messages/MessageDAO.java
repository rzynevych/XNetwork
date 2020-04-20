package com.zman.znetwork.models.messages;

import com.zman.znetwork.models.users.AppUser;
import com.zman.znetwork.models.users.AppUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class MessageDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;


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

        String sql = "SELECT user_id, email, reg_date, username, validate, password, last_login " +
                "FROM users INNER JOIN chats ON (user_id=id_1 OR user_id=id_2) " +
                "WHERE (id_1=? OR id_2=?) AND NOT (user_id=? AND NOT id_1=id_2) ORDER BY last_used DESC LIMIT ?, 50";
        try {
            return jdbcTemplate.query(sql, new Object[]{user_id, user_id, user_id, offset}, new AppUserMapper());
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

    public void insertChat(int id1, int id2) {

        String sql1 = "SELECT count(*) FROM chats WHERE (id_1=? AND id_2=?) OR (id_1=? AND id_2=?)";
        String sql2 = "INSERT INTO chats (id_1, id_2) VALUES (?,?)";
        String sql3 = "UPDATE chats SET last_used=CURRENT_TIMESTAMP WHERE (id_1=? AND id_2=?) OR (id_1=? AND id_2=?)";

        try {
            int count = jdbcTemplate.queryForObject(sql1, new Object[]{id1, id2, id2, id1}, Integer.class);
            if (count == 0)
                jdbcTemplate.update(sql2, id1, id2);
            else
                jdbcTemplate.update(sql3, id1, id2, id2, id1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long insert(int parent_id, String text, String username, int receiver) {

        String sql = "INSERT INTO messages(parent_id,receiver,text,username) VALUES(?,?,?,?)";
        insertChat(parent_id, receiver);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement ps =
                                connection.prepareStatement(sql, new String[]{"message_id"});
                        ps.setString(1, Integer.toString(parent_id));
                        ps.setString(2, Integer.toString(receiver));
                        ps.setString(3, text);
                        ps.setString(4, username);
                        return ps;
                    },
                    keyHolder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyHolder.getKey().longValue();
    }
}
