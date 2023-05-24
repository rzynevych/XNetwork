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

    public Status addSubscription(Long userId) {
        
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        if (subscriptionRepository.existsSubscription(appUser.getUserId(), userId))
            return new Status(false, "Subscription alredy exists");
        subscriptionRepository.save(new Subscription(appUser.getUserId(), userId));
        return new Status(true, "");
    }

    public Status removeSubscription(Long userId) {
        
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        subscriptionRepository.deleteByUsrIdAndSubId(appUser.getUserId(), userId);
        return new Status(true, "");
    }
}
