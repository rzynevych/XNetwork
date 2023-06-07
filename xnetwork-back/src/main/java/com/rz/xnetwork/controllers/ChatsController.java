package com.rz.xnetwork.controllers;

import java.util.List;

import com.rz.xnetwork.models.AppUser;
import com.rz.xnetwork.security.UserHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rz.xnetwork.dto.ChatListElem;
import com.rz.xnetwork.services.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChatsController {
    
    private final ChatService chatService;

    @GetMapping("getChatList")
    public List<ChatListElem> getChatList(@RequestParam int page, @RequestParam int size) {

        AppUser user = UserHandler.getAuthorizedAppUser();
        return chatService.getChatList(user.getUserId(), page, size);
    }
}
