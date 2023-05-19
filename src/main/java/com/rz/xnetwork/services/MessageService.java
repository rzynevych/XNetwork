package com.rz.xnetwork.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.rz.xnetwork.auth.UserHandler;
import com.rz.xnetwork.dto.ChatListElem;
import com.rz.xnetwork.dto.SendMessageDto;
import com.rz.xnetwork.models.AppUser;
import com.rz.xnetwork.models.Chat;
import com.rz.xnetwork.models.Message;
import com.rz.xnetwork.repos.AppUserRepository;
import com.rz.xnetwork.repos.ChatRepository;
import com.rz.xnetwork.repos.MessageRepository;
import com.rz.xnetwork.websockets.WebSocketOutputMessage;


@Service
@Transactional
public class MessageService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public List<Message> getOwnPosts(int page, int size) {
        AppUser user = UserHandler.getAuthorizedUser().getAppUser();
        List<Message> posts = messageRepository.selectOwnPosts(user.getUserId(),
            PageRequest.of(page, size, Sort.by("messageId").descending()));
        return posts;
    }

    public Message uploadPost(SendMessageDto sendMessageDTO)
    {
        AppUser user = UserHandler.getAuthorizedUser().getAppUser();
        Message message = new Message(user.getUserId(), 0L, user.getUsername(), sendMessageDTO.getText());
        return messageRepository.save(message);
    }

    public List<ChatListElem> getChatList(int offset) {
       
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<ChatListElem> users = chatRepository.getChatList(appUser.getUserId(), offset);
        return users;
    }

    public List<Message> getChatMessages(Long converserID, int page, int size) {
        
        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<Message> messages = messageRepository.getMessagesForChat(
                appUser.getUserId(), converserID, 
                PageRequest.of(page, size, Sort.by("messageId").descending()));
        return messages;
    }

    public List<Message> getNewMessages(Long converserID, Long lastMessageID) {

        AppUser appUser = UserHandler.getAuthorizedUser().getAppUser();
        List<Message> messages = messageRepository.getMessagesForUpdate(converserID,
            appUser.getUserId(), lastMessageID, Sort.by("messageId").descending());
        return messages;
    }

    public Message uploadMessage(SendMessageDto sendMessageDTO)
    {
        AppUser user = UserHandler.getAuthorizedUser().getAppUser();
        Long converserID = sendMessageDTO.getConverserId();
        AppUser converser = appUserRepository.findByUserId(converserID) ;
        if (chatRepository.existsChat(user.getUserId(), converserID))
            chatRepository.updateChat(user.getUserId(), converserID);
        else
            chatRepository.save(new Chat(user.getUserId(), converserID, new Date()));
        Message message = new Message(
            user.getUserId(), 
            converserID, 
            user.getUsername(), 
            sendMessageDTO.getText()
        );
        Message savedMessage =  messageRepository.save(message);
        WebSocketOutputMessage out = new WebSocketOutputMessage("message", user.getUserId()); 
        simpMessagingTemplate.convertAndSendToUser(
            converser.getEmail(), "/queue/notifications", out); 
        return savedMessage;
    }

}
