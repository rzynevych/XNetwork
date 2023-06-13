package com.rz.xnetwork.services;

import com.rz.xnetwork.dto.SendMessageDto;
import com.rz.xnetwork.models.AppUser;
import com.rz.xnetwork.models.Message;
import com.rz.xnetwork.repos.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MessageServiceTest {

    @Autowired
    MessageService messageService;
    @Autowired
    AppUserRepository appUserRepository;

    Logger logger = LoggerFactory.getLogger(MessageServiceTest.class);

    @Test
    void getOwnPostsTest() {
        List<Message> messages = messageService.getOwnPosts(1L, 0, 50);
        assertTrue(messages.size() > 0);
        logger.info("Messages list size: {}", messages.size());
        logger.info(messages.get(0).toString());
    }

    @Test
    void getPostsTest() {
        List<Message> messages = messageService.getPosts(1L, 0, 50);
        assertTrue(messages.size() > 0);
        logger.info("Messages list size: {}", messages.size());
        logger.info(messages.get(0).toString());
    }

    @Test
    void uploadPostTest() {
        SendMessageDto dto = new SendMessageDto();
        dto.setConverserId(0L);
        dto.setText("Test post " + new Date());
        AppUser user = appUserRepository.findByUserId(1L);
        Message post = messageService.uploadPost(dto, user);
        assertNotNull(post);
        assertNotNull(post.getMessageId());
        logger.info(post.toString());
    }

    @Test
    void getChatMessagesTest() {
        List<Message> messages = messageService.getChatMessages(1L, 2L, 0, 50);
        assertTrue(messages.size() > 0);
        logger.info("Messages list size: {}", messages.size());
        logger.info(messages.get(0).toString());
    }

    @Test
    void getNewMessagesTest() {
        List<Message> messages = messageService.getNewMessages(3L, 1L, 11L);
        assertTrue(messages.size() > 0);
        logger.info("Messages list size: {}", messages.size());
        logger.info(messages.get(0).toString());
    }

    @Test
    void uploadMessageTest() {
        SendMessageDto dto = new SendMessageDto();
        dto.setConverserId(2L);
        dto.setText("Test message " + new Date());
        AppUser user = appUserRepository.findByUserId(1L);
        Message message = messageService.uploadPost(dto, user);
        assertNotNull(message);
        assertNotNull(message.getMessageId());
        logger.info(message.toString());
    }
}
