package com.rz.xnetwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rz.xnetwork.auth.UserHandler;
import com.rz.xnetwork.dto.SendMessageDTO;
import com.rz.xnetwork.models.AppUser;
import com.rz.xnetwork.models.Message;
import com.rz.xnetwork.repos.MessageRepository;

import java.util.List;

@RestController
public class PostsController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/posts")
    public String posts() {
        return "posts";
    }

    @GetMapping("/getOwnPosts")
    public List<Message> getOwnPosts(@RequestParam String page, @RequestParam String size) {
        AppUser user = UserHandler.getAuthorizedUser().getAppUser();
        List<Message> posts = messageRepository.selectOwnPosts(user.getUserID(),
            PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), Sort.by("messageID").descending()));
        return posts;
    }

    @PostMapping("/uploadPost")
    public Message uploadPost(@RequestBody SendMessageDTO sendMessageDTO)
    {
        System.out.println("uploadPost");
        AppUser user = UserHandler.getAuthorizedUser().getAppUser();
        Message message = new Message(user.getUserID(), 0L, user.getUsername(), sendMessageDTO.getText());
        System.out.println("uploadPost");
        return messageRepository.save(message);
    }

    

}
