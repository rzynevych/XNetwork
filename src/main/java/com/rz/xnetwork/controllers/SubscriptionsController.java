package com.rz.xnetwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rz.xnetwork.auth.UserHandler;
import com.rz.xnetwork.dto.UserListElem;
import com.rz.xnetwork.models.AppUser;
import com.rz.xnetwork.models.Subscription;
import com.rz.xnetwork.repos.AppUserRepository;
import com.rz.xnetwork.repos.SubscriptionRepository;
import com.rz.xnetwork.utils.Status;

import java.util.List;

@RestController
public class SubscriptionsController {

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @GetMapping("/getSubscriberList")
    public List<UserListElem> getSubscriberList(@RequestBody Integer offset) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<UserListElem> users = userRepository.getFriendsForUser(appUser.getUserID(), offset);
        return users;
    }

    private synchronized Subscription insertSubscription(Long subscriberID, Long userID) {

        if (subscriptionRepository.existsSubscription(subscriberID, userID))
            return null;
        Subscription s = subscriptionRepository.save(new Subscription(subscriberID, userID));
        return s;
    }

    @GetMapping("addSubscription")
    public Subscription addSubscription(@RequestParam String userID) {
        
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        return insertSubscription(appUser.getUserID(), Long.parseLong(userID));
    }

    @GetMapping("removeSubscription")
    public Status removeSubscription( @RequestParam String userID) {
        
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        subscriptionRepository.deleteByUsrIDAndSubID(appUser.getUserID(), Long.parseLong(userID));

        return new Status(true);
    }

    @GetMapping("/getUsersByQuery")
    public List<UserListElem> getUsersByQuery(@RequestParam String query) throws JsonProcessingException {

        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<UserListElem> users = userRepository.getUsersByQuery(query + "%", appUser.getUserID(), 0);
        return users;
    }
}