package com.zman.znetwork.web;

import com.zman.znetwork.auth.Registration;
import com.zman.znetwork.auth.UserHandler;
import com.zman.znetwork.models.AppUser;
import com.zman.znetwork.repos.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    private Registration registration;

    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping("/login")
    public String login() {

        if (UserHandler.isUserAuthorized())
            return "redirect:/posts";
        return "login";
    }

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpHandler(@RequestParam Map<String, String> params, Model model) {

        String message = registration.handleData(params);
        if (message != null) {
            model.addAttribute("message", message);
            return "signup";
        }
        return "redirect:/welcome";
    }

    @GetMapping("/account")
    public String account(@RequestParam int id, Model model) {

        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        AppUser user;
        if (id == 0)
            user = appUser;
        else
            user = appUserRepository.findByUserID(id);
        if (user == null) {
            model.addAttribute("message", "User not found");
            return "user";
        }
        model.addAttribute("user", user);
        if (user.getUserID() == appUser.getUserID())
            model.addAttribute("authority", true);
        return "user";
    }
}
