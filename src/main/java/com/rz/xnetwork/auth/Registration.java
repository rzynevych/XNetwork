package com.rz.xnetwork.auth;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rz.xnetwork.models.AppUser;
import com.rz.xnetwork.repos.AppUserRepository;

import java.util.Map;

@Service
public class Registration {

    @Autowired
    private AppUserRepository appUserRepository;
    private BCryptPasswordEncoder encoder;
    private EmailValidator emailValidator;

    public Registration() {

        encoder = new BCryptPasswordEncoder();
        emailValidator = EmailValidator.getInstance();
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
        if (!emailValidator.isValid(data.get("email")))
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
