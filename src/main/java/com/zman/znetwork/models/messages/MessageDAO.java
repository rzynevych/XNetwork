package com.zman.znetwork.models.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class MessageDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

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
