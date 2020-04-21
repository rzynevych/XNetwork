package com.zman.znetwork.auth;

import com.zman.znetwork.models.AppUser;
import com.zman.znetwork.repos.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Registration {

    @Autowired
    private AppUserRepository appUserRepository;
    private BCryptPasswordEncoder encoder;

    public Registration() {

        encoder = new BCryptPasswordEncoder();
    }

    public String handleData(Map<String, String> data) {

        AppUser user;
        user = appUserRepository.findByUsername(data.get("username"));
        if (user != null)
            return "User exists";
        user = appUserRepository.findByEmail(data.get("email"));
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
        user = new AppUser();
        user.setEmail(data.get("email"));
        user.setUsername(data.get("username"));
        user.setPassword(encoder.encode(data.get("password")));
        appUserRepository.save(user);
        return null;
    }
}
