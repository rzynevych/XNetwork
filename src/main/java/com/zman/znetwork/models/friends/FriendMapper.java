package com.zman.znetwork.models.friends;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendMapper implements RowMapper<Friend> {

    @Override
    public Friend mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new Friend(
                rs.getInt("user_id"),
                rs.getString("email"),
                rs.getString("reg_date"),
                rs.getString("username"),
                rs.getString("token"),
                rs.getByte("validate"),
                rs.getString("password"),
                rs.getString("last_login"));
    }
}
