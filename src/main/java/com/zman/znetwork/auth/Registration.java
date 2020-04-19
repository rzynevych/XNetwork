package com.zman.znetwork.auth;

import com.zman.znetwork.models.users.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Registration {

    private AppUserDAO appUserDAO;
    private BCryptPasswordEncoder encoder;

    public Registration(@Autowired AppUserDAO appUserDAO) {

        this.appUserDAO = appUserDAO;
        encoder = new BCryptPasswordEncoder();
    }

    public String handleData(Map<String, String> data) {

        AppUser user;
        user = appUserDAO.getByUsername(data.get("username"));
        if (user != null)
            return "User exists";
        user = appUserDAO.getByEmail(data.get("email"));
        if (user != null)
            return "Email already registered";
        String password = data.get("password");
        String passwordRepeat = data.get("password_repeat");
        if (!password.equals(passwordRepeat))
            return "Passwords do not match";
        if (password.equals(""))
            return "Password is empty";
        if (data.get("email").equals(""))
            return "Invalid email";
        if (data.get("username").equals(""))
            return "Invalid username";
        user = new AppUser(
                0,
                data.get("email"),
                null,
                data.get("username"),
                (byte)1,
                encoder.encode(data.get("password")),
                null
        );
        appUserDAO.insert(user);
        return null;
    }
}
