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
    
    public List<Message> getOwnPosts(Long userId, int page, int size) {
        List<Message> posts = messageRepository.selectOwnPosts(userId,
            PageRequest.of(page, size, Sort.by("messageId").descending()));
        return posts;
    }

    public List<Message> getPosts(Long userId, int page, int size) {
        List<Message> posts = messageRepository.selectPosts(userId,
                PageRequest.of(page, size, Sort.by("messageId").descending()));
        return posts;
    }

    public Message uploadPost(SendMessageDto sendMessageDTO, AppUser user)
    {
        Message message = new Message(user.getUserId(), 0L, user.getUsername(), sendMessageDTO.getText());
        return messageRepository.save(message);
    }

    public List<Message> getChatMessages(Long converserId, Long userId, int page, int size) {
        
        List<Message> messages = messageRepository.getMessagesForChat(
                userId, converserId,
                PageRequest.of(page, size, Sort.by("messageId").ascending()));
        return messages;
    }

    public List<Message> getNewMessages(Long converserId, Long userId, Long lastMessageID) {

        List<Message> messages = messageRepository.getMessagesForUpdate(converserId,
            userId, lastMessageID, PageRequest.of(0, 50, Sort.by("messageId").ascending()));
        return messages;
    }

    public Message uploadMessage(SendMessageDto sendMessageDto, AppUser user)
    {
        Long converserId = sendMessageDto.getConverserId();
        AppUser converser = appUserRepository.findByUserId(converserId) ;
        if (chatRepository.existsChat(user.getUserId(), converserId))
            chatRepository.updateChat(user.getUserId(), converserId);
        else
            chatRepository.save(new Chat(user.getUserId(), converserId, new Date()));
        Message message = new Message(
            user.getUserId(), 
            converserId,
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
