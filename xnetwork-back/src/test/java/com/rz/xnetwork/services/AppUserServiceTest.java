package com.rz.xnetwork.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.rz.xnetwork.dto.UserListElem;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AppUserServiceTest {

    @Autowired AppUserService appUserService;

    Logger logger = LoggerFactory.getLogger(AppUserServiceTest.class);

    @Test
    void getUsersByQueryTest() {
        List<UserListElem> users = appUserService.getUsersByQuery("a", 1L, 0, 50);
        assertTrue(users.size() > 0);
        logger.info(users.get(0).toString());
    }
}
