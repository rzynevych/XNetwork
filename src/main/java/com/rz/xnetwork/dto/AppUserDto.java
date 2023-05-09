package com.rz.xnetwork.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {
    private Long userID;
    private String email;
    private String username;
    private Date lastLogin;
    private Date regDate;
}
