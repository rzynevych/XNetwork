package com.rz.xnetwork.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rz.xnetwork.auth.UserHandler;
import com.rz.xnetwork.dto.UserListElem;
import com.rz.xnetwork.models.AppUser;
import com.rz.xnetwork.models.Subscription;
import com.rz.xnetwork.repos.AppUserRepository;
import com.rz.xnetwork.repos.SubscriptionRepository;
import com.rz.xnetwork.utils.Status;

@Service
@Transactional
public class AppUserService {
    
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    
    private BCryptPasswordEncoder encoder;
    private EmailValidator emailValidator;

    public AppUserService() {

        encoder = new BCryptPasswordEncoder();
        emailValidator = EmailValidator.getInstance();
    }

    public String registerUser(Map<String, String> data) {

        AppUser user;
        user = appUserRepository.findByUsername(data.get("username"));
        if (user != null)
            return "User exists";
        user = appUserRepository.findByEmail(data.get("email"));
        if (user != null)
            return "Email already registered";
        String password = data.get("password");
        if (password.equals(""))
            return "Password is empty";
        if (!emailValidator.isValid(data.get("email")))
            return "Invalid email";
        if (data.get("username").equals(""))
            return "Empty username";
        user = new AppUser();
        user.setEmail(data.get("email"));
        user.setUsername(data.get("username"));
        user.setPassword(encoder.encode(data.get("password")));
        user.setRegDate(new Date());
        appUserRepository.save(user);
        return null;
    }

    public List<UserListElem> getSubscriberList(int offset, int limit) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<UserListElem> users = appUserRepository.getSubscribersForUser(appUser.getUserId(), offset, limit);
        return users;
    }

    public List<UserListElem> getSubscriptionList(int offset, int limit) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<UserListElem> users = appUserRepository.getSubscribersForUser(appUser.getUserId(), offset, limit);
        return users;
    }

    public Status addSubscription(Long userId) {
        
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        if (subscriptionRepository.existsSubscription(appUser.getUserId(), userId))
            return new Status(false);
        subscriptionRepository.save(new Subscription(appUser.getUserId(), userId));
        return new Status(true);
    }

    public Status removeSubscription(Long userId) {
        
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        subscriptionRepository.deleteByUsrIdAndSubId(appUser.getUserId(), userId);
        return new Status(true);
    }

    public List<UserListElem> getUsersByQuery(String query, int offset, int limit) {

        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<UserListElem> users = appUserRepository.getUsersByQuery(query + "%", appUser.getUserId(), offset, limit);
        return users;
    }
}
