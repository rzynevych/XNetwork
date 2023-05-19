package com.rz.xnetwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rz.xnetwork.auth.UserHandler;
import com.rz.xnetwork.mappers.AppUserMapper;
import com.rz.xnetwork.repos.AppUserRepository;
import com.rz.xnetwork.services.AppUserService;
import com.rz.xnetwork.utils.Status;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserMapper appUserMapper;

    @GetMapping("/authCheck")
    public ResponseEntity<String> authCheck() throws JsonProcessingException {
        Map<String, Object> payload = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        if (UserHandler.isUserAuthorized())
        {
            payload.put("status", true);
            payload.put("user", appUserMapper.toDto(UserHandler.getAuthorizedUser().getAppUser()));
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                .body(objectMapper.writeValueAsString(payload));
        }

        payload.put("status", false);
        payload.put("user", null);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
            .body(objectMapper.writeValueAsString(payload));
    }


    @PostMapping("/register")
    public Status signUpHandler(@RequestParam Map<String, String> params) {

        String message = appUserService.registerUser(params);
        if (message != null) {
            return new Status(false);
        }
        return new Status(true);
    }

}