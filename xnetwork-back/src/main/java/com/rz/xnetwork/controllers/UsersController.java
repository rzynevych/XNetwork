package com.rz.xnetwork.controllers;

import com.rz.xnetwork.models.AppUser;
import com.rz.xnetwork.security.UserHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rz.xnetwork.dto.UserListElem;
import com.rz.xnetwork.services.AppUserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController {

    private final AppUserService appUserService;

    @GetMapping("/getSubscriberList")
    public List<UserListElem> getSubscriberList(@RequestParam int page, @RequestParam int size) {

        AppUser user = UserHandler.getAuthorizedAppUser();
        return appUserService.getSubscriberList(user.getUserId(), page, size);
    }
    
    @GetMapping("/getSubscriptionList")
    public List<UserListElem> getSubscriptionList(@RequestParam int page, @RequestParam int size) {

        AppUser user = UserHandler.getAuthorizedAppUser();
        return appUserService.getSubscriberList(user.getUserId(), page, size);
    }

    @GetMapping("/getUsersByQuery")
    public List<UserListElem> getUsersByQuery(@RequestParam String query, @RequestParam int page, @RequestParam int size) {

        AppUser user = UserHandler.getAuthorizedAppUser();
        return appUserService.getUsersByQuery(query, user.getUserId(), page, size);
    }
}