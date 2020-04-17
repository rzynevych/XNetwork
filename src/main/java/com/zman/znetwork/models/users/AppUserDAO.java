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
        String sql = "SELECT * FROM users WHERE user_id=? LIMIT 1";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[] { id }, new AppUserMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public AppUser getByUsername (String username) {
        String sql = "SELECT * FROM users WHERE username=? LIMIT 1";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[] { username }, new AppUserMapper());
        } catch (Exception e) {
            return null;
        }
    }

    public AppUser getByEmail (String email) {
        String sql = "SELECT * FROM users WHERE email=? LIMIT 1";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[] { email }, new AppUserMapper());
        } catch (Exception e) {
            return null;
        }
    }

    public void insert(AppUser user) {

        String sql = "INSERT INTO users (email, username, validate, password) VALUES (?,?,?,?)";

        try {
            jdbcTemplate.update(sql, user.getEmail(), user.getUsername(), user.getValidate(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
