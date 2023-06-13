package com.rz.xnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationDto {

    private Boolean status;
    private AppUserDto user;
}
