package com.zman.znetwork.models.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;

@Repository
public class AppUserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AppUser getById (int id) {
        String sql = "SELECT * FROM users WHERE id=? LIMIT 1";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[] { id }, new AppUserMapper());
        } catch (Exception e) {
            return null;
        }
    }
}
