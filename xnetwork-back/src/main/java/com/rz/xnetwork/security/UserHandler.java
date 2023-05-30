package com.rz.xnetwork.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.rz.xnetwork.models.AppUser;

public class UserHandler {

    public static boolean isUserAuthorized() {
        return !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    public static UserInfo getAuthorizedUser() {
        return (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static AppUser getAuthorizedAppUser() {
        return getAuthorizedUser().getAppUser();
    }
    
}
