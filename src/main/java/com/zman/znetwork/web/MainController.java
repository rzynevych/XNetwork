package com.zman.znetwork.web;

import com.zman.znetwork.auth.Registration;
import com.zman.znetwork.auth.UserHandler;
import com.zman.znetwork.models.messages.Message;
import com.zman.znetwork.models.messages.MessageDAO;
import com.zman.znetwork.models.users.AppUser;
import com.zman.znetwork.models.users.AppUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private AppUserDAO appUserDAO;

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private Registration registration;

    @GetMapping({"/", "/welcome"})
    public String greeting(Model model) {
        model.addAttribute("name", "zman");
        return "greeting";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        ArrayList<Message> messages = messageDAO.selectItems(0, 0);
        model.addAttribute("messages", messages);
        return "posts";
    }

    @PostMapping("/posts")
    public String send(@RequestParam String message_text, Model model) {

        AppUser user = UserHandler.getAuthorizedUser().getAppUser();
        messageDAO.insert(user.getId(), message_text, user.getUsername(), 0);
        ArrayList<Message> messages = messageDAO.selectItems(0, 0);
        model.addAttribute("messages", messages);
        return "posts";
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