package com.rz.xnetwork.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rz.xnetwork.dto.AppUserRegistrationDto;
import com.rz.xnetwork.dto.AuthenticationDto;
import com.rz.xnetwork.dto.Status;
import com.rz.xnetwork.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @GetMapping("/authCheck")
    public AuthenticationDto authCheck() {
       
        return authenticationService.getAuthenticationInfo();
    }

    @PostMapping("/register")
    public Status signUpHandler(@RequestBody AppUserRegistrationDto userInfo) {

        return authenticationService.registerUser(userInfo);
    }
}
