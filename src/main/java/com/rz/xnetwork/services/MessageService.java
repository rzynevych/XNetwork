package com.rz.xnetwork.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.rz.xnetwork.dto.SendMessageDto;
import com.rz.xnetwork.dto.WebSocketOutputMessage;
import com.rz.xnetwork.models.AppUser;
import com.rz.xnetwork.models.Chat;
import com.rz.xnetwork.models.Message;
import com.rz.xnetwork.repos.AppUserRepository;
import com.rz.xnetwork.repos.ChatRepository;
import com.rz.xnetwork.repos.MessageRepository;
import com.rz.xnetwork.security.UserHandler;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final AppUserRepository appUserRepository;
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    
    public List<Message> getOwnPosts(int page, int size) {
        AppUser user = UserHandler.getAuthorizedUser().getAppUser();
        List<Message> posts = messageRepository.selectOwnPosts(user.getUserId(),
            PageRequest.of(page, size, Sort.by("messageId").descending()));
        return posts;
    }

    public List<Message> getPosts(int page, int size) {
        AppUser user = UserHandler.getAuthorizedUser().getAppUser();
        List<Message> posts = messageRepository.selectPosts(user.getUserId(), PageRequest.of(page, size, Sort.by("messageId").descending()));
        return posts;
    }

    public Message uploadPost(SendMessageDto sendMessageDTO)
    {
        AppUser user = UserHandler.getAuthorizedUser().getAppUser();
        Message message = new Message(user.getUserId(), 0L, user.getUsername(), sendMessageDTO.getText());
        return messageRepository.save(message);
    }

    public List<Message> getChatMessages(Long converserID, int page, int size) {
        
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<Message> messages = messageRepository.getMessagesForChat(
                appUser.getUserId(), converserID, 
                PageRequest.of(page, size, Sort.by("messageId").ascending()));
        return messages;
    }

    public List<Message> getNewMessages(Long converserID, Long lastMessageID) {

        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<Message> messages = messageRepository.getMessagesForUpdate(converserID,
            appUser.getUserId(), lastMessageID, Sort.by("messageId").descending());
        return messages;
    }

    public Message uploadMessage(SendMessageDto sendMessageDto)
    {
        AppUser user = UserHandler.getAuthorizedUser().getAppUser();
        Long converserID = sendMessageDto.getConverserId();
        AppUser converser = appUserRepository.findByUserId(converserID) ;
        if (chatRepository.existsChat(user.getUserId(), converserID))
            chatRepository.updateChat(user.getUserId(), converserID);
        else
            chatRepository.save(new Chat(user.getUserId(), converserID, new Date()));
        Message message = new Message(
            user.getUserId(), 
            converserID, 
            user.getUsername(), 
            sendMessageDto.getText()
        );
        Message savedMessage =  messageRepository.save(message);
        WebSocketOutputMessage out = new WebSocketOutputMessage("message", user.getUserId()); 
        simpMessagingTemplate.convertAndSendToUser(
            converser.getEmail(), "/queue/notifications", out); 
        return savedMessage;
    }

}
