package com.rz.xnetwork.services;

import com.rz.xnetwork.dto.UserListElem;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AppUserServiceTest {

    @Autowired AppUserService appUserService;

    Logger logger = LoggerFactory.getLogger(AppUserServiceTest.class);

    @Test
    void getSubscriberListTest() {
        List<UserListElem> users = appUserService.getSubscriberList(1L, 0, 50);
        assertTrue(users.size() > 0);
        logger.info(users.get(0).toString());
    }

    @Test
    void getSubscriptionListTest() {
        List<UserListElem> users = appUserService.getSubscriptionList(1L, 0, 50);
        assertTrue(users.size() > 0);
        logger.info(users.get(0).toString());
    }

    @Test
    void getUsersByQueryTest() {
        List<UserListElem> users = appUserService.getUsersByQuery("a", 1L, 0, 50);
        assertTrue(users.size() > 0);
        logger.info(users.get(0).toString());
    }
}
