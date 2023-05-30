package com.rz.xnetwork.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rz.xnetwork.dto.Status;
import com.rz.xnetwork.services.SubscriptionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SubscriptionsController {
 
    private final SubscriptionService subscriptionService;

    @GetMapping("/addSubscription")
    public Status addSubscription(@RequestParam Long userId) {
        
        return subscriptionService.addSubscription(userId);
    }

    @GetMapping("/removeSubscription")
    public Status removeSubscription(@RequestParam Long userId) {
        
        return subscriptionService.removeSubscription(userId);
    }
}
