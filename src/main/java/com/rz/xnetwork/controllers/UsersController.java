package com.rz.xnetwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rz.xnetwork.dto.UserListElem;
import com.rz.xnetwork.services.AppUserService;
import com.rz.xnetwork.utils.Status;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    AppUserService appUserService;

    @GetMapping("/getSubscriberList")
    public List<UserListElem> getSubscriberList(@RequestParam String offset, @RequestParam String limit) {
       
        return appUserService.getSubscriberList(Integer.parseInt(offset), Integer.parseInt(limit));
    }
    
    @GetMapping("/getSubscriptionList")
    public List<UserListElem> getSubscriptionList(@RequestParam String offset, @RequestParam String limit) {
       
        return appUserService.getSubscriberList(Integer.parseInt(offset), Integer.parseInt(limit));
    }

    @GetMapping("/addSubscription")
    public Status addSubscription(@RequestParam String userId) {
        
        return appUserService.addSubscription(Long.parseLong(userId));
    }

    @GetMapping("/removeSubscription")
    public Status removeSubscription(@RequestParam String userId) {
        
        return appUserService.removeSubscription(Long.parseLong(userId));
    }

    @GetMapping("/getUsersByQuery")
    public List<UserListElem> getUsersByQuery(@RequestParam String query, @RequestParam String offset, @RequestParam String limit) {

        return appUserService.getUsersByQuery(query, Integer.parseInt(offset), Integer.parseInt(limit));
    }
}