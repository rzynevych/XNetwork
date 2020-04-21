package com.zman.znetwork.auth;

import com.zman.znetwork.models.UserInfo;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserHandler {

    public static boolean isUserAuthorized() {
        return !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    public static UserInfo getAuthorizedUser() {
        return (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
