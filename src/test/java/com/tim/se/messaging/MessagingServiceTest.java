package com.tim.se.messaging;

import com.tim.se.messaging.recipient.Recipient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessagingServiceTest {

    private MessagingService messagingService = new MessagingService();

    @BeforeEach
    void setUp() {
        this.messagingService = new MessagingService();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void registerRecipient() {
        final Recipient recipient = new Recipient("alice", null);
        this.messagingService.registerRecipient(recipient);
        assertEquals(recipient, this.messagingService.getRecipient("alice"));
    }

    @Test
    void unregisterRecipient() {
        final Recipient recipient = new Recipient("alice", null);
        this.messagingService.registerRecipient(recipient);
        assertEquals(recipient, this.messagingService.getRecipient("alice"));
        this.messagingService.unregisterRecipient(recipient);
        assertThrows(IllegalArgumentException.class, () -> this.messagingService.getRecipient("alice"));
    }


}