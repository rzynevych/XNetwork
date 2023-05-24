package com.rz.xnetwork.services;

import java.util.Date;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rz.xnetwork.dto.AppUserRegistrationDto;
import com.rz.xnetwork.dto.AuthenticationDto;
import com.rz.xnetwork.dto.Status;
import com.rz.xnetwork.mappers.AppUserMapper;
import com.rz.xnetwork.models.AppUser;
import com.rz.xnetwork.repos.AppUserRepository;
import com.rz.xnetwork.security.UserHandler;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
    private final BCryptPasswordEncoder encoder;    
    
    public AuthenticationDto getAuthenticationInfo() {
        if (UserHandler.isUserAuthorized())
        {
            return new AuthenticationDto(true, 
                appUserMapper.toDto(UserHandler.getAuthorizedUser().getAppUser()));
        }
        return new AuthenticationDto(false, null);
    }

    public Status registerUser(AppUserRegistrationDto data) {

        AppUser user;
        user = appUserRepository.findByUsername(data.getUsername());
        if (user != null)
            return new Status(false,  "User with given username alredy exists");
        user = appUserRepository.findByEmail(data.getEmail());
        if (user != null)
            return new Status(false, "Email already registered");
        String password = data.getPassword();
        if (password.equals(""))
            return new Status(false, "Password is empty");
        if (!EmailValidator.getInstance().isValid(data.getEmail()))
            return new Status(false, "Invalid email");
        if (data.getUsername().equals(""))
            return new Status(false, "Empty username");
        user = new AppUser();
        user.setEmail(data.getEmail());
        user.setUsername(data.getUsername());
        user.setPassword(encoder.encode(data.getPassword()));
        user.setRegDate(new Date());
        appUserRepository.save(user);
        return new Status(true, "");
    }

}
