package com.rz.xnetwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rz.xnetwork.dto.ChatListElem;
import com.rz.xnetwork.dto.SendMessageDto;
import com.rz.xnetwork.models.Message;
import com.rz.xnetwork.services.MessageService;
import com.rz.xnetwork.websockets.WebSocketInputMessage;
import com.rz.xnetwork.websockets.WebSocketOutputMessage;

import java.security.Principal;
import java.util.List;

@RestController
public class MessagesController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/getOwnPosts")
    public List<Message> getOwnPosts(@RequestParam String page, @RequestParam String size) {
        
        return messageService.getOwnPosts(Integer.parseInt(page), Integer.parseInt(size));
    }

    @GetMapping("/getPosts")
    public List<Message> getOPosts(@RequestParam String offset, @RequestParam String limit) {
        
        return messageService.getPosts(Integer.parseInt(offset), Integer.parseInt(limit));
    }

    @PostMapping("/uploadPost")
    public Message uploadPost(@RequestBody SendMessageDto sendMessageDTO)
    {
        return messageService.uploadPost(sendMessageDTO);
    }

    @GetMapping("getChatList")
    public List<ChatListElem> getChatList(@RequestParam String offset, @RequestParam String limit) {
        return messageService.getChatList(Integer.parseInt(offset), Integer.parseInt(limit));
    }

    @GetMapping("/getChatMessages")
    public List<Message> getChatMessages(@RequestParam String converserID, 
        @RequestParam String page, @RequestParam String size) {

        return messageService.getChatMessages(
            Long.parseLong(converserID), Integer.parseInt(page), Integer.parseInt(size));
    }

    @GetMapping("getNewMessages")
    public List<Message> getNewMessages(@RequestParam String converserId, @RequestParam String lastMessageId) {

        return messageService.getNewMessages(Long.parseLong(converserId), Long.parseLong(lastMessageId));
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
