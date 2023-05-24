package com.rz.xnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppUserRegistrationDto {
    private String username;
    private String email;
    private String password;
}
