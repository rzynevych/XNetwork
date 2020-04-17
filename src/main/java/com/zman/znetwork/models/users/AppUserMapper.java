package com.zman.znetwork.models.users;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class AppUserMapper implements RowMapper<AppUser> {

    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new AppUser(
                rs.getInt("user_id"),
                rs.getString("email"),
                rs.getString("reg_date"),
                rs.getString("username"),
                rs.getByte("validate"),
                rs.getString("password"),
                rs.getString("last_login"));
    }
}