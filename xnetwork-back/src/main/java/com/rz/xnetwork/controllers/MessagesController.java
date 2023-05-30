package com.rz.xnetwork.controllers;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rz.xnetwork.dto.SendMessageDto;
import com.rz.xnetwork.dto.WebSocketInputMessage;
import com.rz.xnetwork.dto.WebSocketOutputMessage;
import com.rz.xnetwork.models.Message;
import com.rz.xnetwork.services.MessageService;

import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessagesController {

    private final MessageService messageService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/getOwnPosts")
    public List<Message> getOwnPosts(@RequestParam int page, @RequestParam int size) {
        
        return messageService.getOwnPosts(page, size);
    }

    @GetMapping("/getPosts")
    public List<Message> getOPosts(@RequestParam int page, @RequestParam int size) {
        
        return messageService.getPosts(page, size);
    }

    @PostMapping("/uploadPost")
    public Message uploadPost(@RequestBody SendMessageDto sendMessageDTO)
    {
        return messageService.uploadPost(sendMessageDTO);
    }

    @GetMapping("/getChatMessages")
    public List<Message> getChatMessages(@RequestParam Long converserId, 
        @RequestParam int page, @RequestParam int size) {

        return messageService.getChatMessages(converserId, page, size);
    }

    @GetMapping("getNewMessages")
    public List<Message> getNewMessages(@RequestParam Long converserId, @RequestParam Long lastMessageId) {

        return messageService.getNewMessages(converserId, lastMessageId);
    }

    @PostMapping("uploadMessage")
    public Message uploadMessage(@RequestBody SendMessageDto sendMessageDTO)
    {
        
        return messageService.uploadMessage(sendMessageDTO);
    }

    @MessageMapping("/messaging") 
    public void sendSpecific(@Payload WebSocketInputMessage msg, Principal user, 
        @Header("simpSessionId") String sessionId) throws Exception { 
            
        WebSocketOutputMessage out = new WebSocketOutputMessage(msg.getText(), 0L); 
        System.out.println("msg.text: " + msg.getText());
        simpMessagingTemplate.convertAndSendToUser(
            msg.getUsername(), "/queue/notifications", out); 
    }

}
