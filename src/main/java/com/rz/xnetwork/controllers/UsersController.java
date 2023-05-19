package com.rz.xnetwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rz.xnetwork.dto.UserListElem;
import com.rz.xnetwork.services.AppUserService;
import com.rz.xnetwork.utils.Status;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    AppUserService appUserService;

    @GetMapping("/getSubscriberList")
    public List<UserListElem> getSubscriberList(@RequestParam String offset) {
       
        return appUserService.getSubscriberList(Integer.parseInt(offset));
    }

    @GetMapping("addSubscription")
    public Status addSubscription(@RequestParam String userId) {
        
        return appUserService.addSubscription(Long.parseLong(userId));
    }

    @GetMapping("removeSubscription")
    public Status removeSubscription(@RequestParam String userID) {
        
        return appUserService.removeSubscription(Long.parseLong(userID));
    }

    @GetMapping("/getUsersByQuery")
    public List<UserListElem> getUsersByQuery(@RequestParam String query) throws JsonProcessingException {

        return getUsersByQuery(query);
    }
}