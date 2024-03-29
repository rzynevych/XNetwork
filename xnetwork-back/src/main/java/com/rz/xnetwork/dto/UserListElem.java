package com.rz.xnetwork.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserListElem {

    private AppUserDto user;
    private Boolean subscribed;
    private Boolean inSubscriptions;


    public UserListElem(Long userId, String email, String username, Date lastLogin, Date regDate, 
        Boolean subscribed, Boolean inSubscriptions) {
        
        user = new AppUserDto(userId, email, username, lastLogin, regDate);
        this.subscribed = subscribed;
        this.inSubscriptions = inSubscriptions;
    }
}
