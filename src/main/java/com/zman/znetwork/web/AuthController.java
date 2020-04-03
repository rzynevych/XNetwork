package com.zman.znetwork.web;

import com.zman.znetwork.auth.Registration;
import com.zman.znetwork.auth.UserHandler;
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
}
