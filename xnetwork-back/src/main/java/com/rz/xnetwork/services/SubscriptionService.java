package com.rz.xnetwork.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rz.xnetwork.dto.Status;
import com.rz.xnetwork.models.AppUser;
import com.rz.xnetwork.models.Subscription;
import com.rz.xnetwork.repos.SubscriptionRepository;
import com.rz.xnetwork.security.UserHandler;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionService {
    
    private final SubscriptionRepository subscriptionRepository;

    public Status addSubscription(Long subscriberId, Long userId) {
        
        if (subscriptionRepository.existsSubscription(subscriberId, userId))
            return new Status(false, "Subscription already exists");
        subscriptionRepository.save(new Subscription(subscriberId, userId));
        return new Status(true, "");
    }

    public Status removeSubscription(Long subscriberId, Long userId) {
        
        subscriptionRepository.deleteByUsrIdAndSubId(subscriberId, userId);
        return new Status(true, "");
    }
}
