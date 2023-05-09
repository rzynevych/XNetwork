package com.rz.xnetwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rz.xnetwork.auth.UserHandler;
import com.rz.xnetwork.dto.AppUserDto;
import com.rz.xnetwork.dto.SendMessageDTO;
import com.rz.xnetwork.models.AppUser;
import com.rz.xnetwork.models.Chat;
import com.rz.xnetwork.models.Message;
import com.rz.xnetwork.repos.AppUserRepository;
import com.rz.xnetwork.repos.ChatRepository;
import com.rz.xnetwork.repos.MessageRepository;
import com.rz.xnetwork.websockets.WebSocketInputMessage;
import com.rz.xnetwork.websockets.WebSocketOutputMessage;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
public class MessagesController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("getChatList")
    public List<AppUserDto> getChatList(@RequestParam String offset) {
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<AppUserDto> users = appUserRepository.getChatUsers(appUser.getUserID(), Integer.parseInt(offset));
        return users;
    }

    @GetMapping("/getChatMessages")
    public List<Message> getChatMessages(@RequestParam String converserID, 
        @RequestParam String page, @RequestParam String size) {

        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<Message> messages = messageRepository.getMessagesForChat(
                appUser.getUserID(), Long.parseLong(converserID), 
                PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), Sort.by("messageID").descending()));
        return messages;
    }

    @GetMapping("getNewMessages")
    public List<Message> getNewMessages(@RequestParam String converserID, @RequestParam String lastMessageID) {

        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<Message> messages = messageRepository.getMessagesForUpdate(Long.parseLong(converserID),
            appUser.getUserID(), Long.parseLong(lastMessageID));
        return messages;
    }

    @PostMapping("uploadMessage")
    public Message uploadMessage(@RequestBody SendMessageDTO sendMessageDTO)
    {
        AppUser user = UserHandler.getAuthorizedUser().getAppUser();
        Long converserID = sendMessageDTO.getConverserID();
        AppUser converser = appUserRepository.findByUserID(converserID) ;
        if (chatRepository.existsChat(user.getUserID(), converserID))
            chatRepository.updateChat(user.getUserID(), converserID);
        else
            chatRepository.save(new Chat(user.getUserID(), converserID, new Date()));
        Message message = new Message(
            user.getUserID(), 
            converserID, 
            user.getUsername(), 
            sendMessageDTO.getText()
        );
        Message savedMessage =  messageRepository.save(message);
        WebSocketOutputMessage out = new WebSocketOutputMessage("message", user.getUserID()); 
        simpMessagingTemplate.convertAndSendToUser(
            converser.getEmail(), "/queue/notifications", out); 
        return savedMessage;
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

