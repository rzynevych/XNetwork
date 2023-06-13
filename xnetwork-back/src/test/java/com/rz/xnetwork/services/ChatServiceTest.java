package com.rz.xnetwork.services;

import com.rz.xnetwork.dto.ChatListElem;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ChatServiceTest {

    @Autowired
    ChatService chatService;

    Logger logger = LoggerFactory.getLogger(ChatServiceTest.class);

    @Test
    void getChatListTest() {

        List<ChatListElem> chats = chatService.getChatList(1L, 0, 50);
        assertTrue(chats.size() > 0);
        logger.info(chats.get(0).toString());
    }
}
