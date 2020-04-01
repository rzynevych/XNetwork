package com.zman.znetwork.web;

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

@Controller
public class MainController {

    @Autowired
    private AppUserDAO appUserDAO;

    @Autowired
    private MessageDAO messageDAO;

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
        AppUser appUser = appUserDAO.getById(2);
        ArrayList<Message> messages = messageDAO.selectItems(0, 0);
        model.addAttribute("messages", messages);
        return "posts";
    }

    @PostMapping("/posts")
    public String send(@RequestParam String message_text, Model model) {

        AppUser appUser = appUserDAO.getById(2);
        messageDAO.insert(2, message_text, appUser.getUsername(), 0);
        ArrayList<Message> messages = messageDAO.selectItems(0, 0);
        model.addAttribute("messages", messages);
        return "posts";
    }
}