package com.zman.znetwork;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @GetMapping({"/", "/welcome"})
    public String greeting(Model model) {
        model.addAttribute("name", "zman");
        return "greeting";
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("name", "zman");
        return "posts";
    }

}