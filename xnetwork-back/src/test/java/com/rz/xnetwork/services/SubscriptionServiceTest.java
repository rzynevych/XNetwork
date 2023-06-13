package com.rz.xnetwork.services;

import com.rz.xnetwork.dto.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SubscriptionServiceTest {

    @Autowired
    SubscriptionService subscriptionService;

    @Test
    void addSubscriptionTest() {
        Status status = subscriptionService.addSubscription(1L, 2L);
        assertNotNull(status);
        assertTrue(status.getStatus());
    }

    @Test
    void removeSubscriptionTest() {
        Status status = subscriptionService.removeSubscription(1L, 2L);
        assertNotNull(status);
        assertTrue(status.getStatus());
    }
}
