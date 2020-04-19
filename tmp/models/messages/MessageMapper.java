package com.zman.znetwork.models.messages;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class MessageMapper implements RowMapper<Message> {

    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new Message(
                rs.getLong("message_id"),
                rs.getInt("parent_id"),
                rs.getInt("receiver"),
                rs.getString("username"),
                rs.getString("text"),
                rs.getString("DATE_FORMAT(`date`, '%H:%i')")
        );
    }
}
