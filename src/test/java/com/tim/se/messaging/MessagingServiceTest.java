package com.tim.se.messaging;

import com.tim.se.messaging.message.Attachment;
import com.tim.se.messaging.message.TextMessage;
import com.tim.se.messaging.recipient.Recipient;
import com.tim.se.messaging.sender.Sender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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


    @Test
    void sendToAllRecipients() {
        final ByteArrayOutputStream testOutputStream = new ByteArrayOutputStream();
        final Recipient recipient = new Recipient("alice", testOutputStream);
        this.messagingService.registerRecipient(recipient);
        final Sender sender = new Sender("bob");
        final TextMessage textMessage = sender.createTextMessage("subject", "content", new Attachment[0]);
        this.messagingService.send(textMessage);
        String output = testOutputStream.toString();
        assertEquals("Printing text message: content\n", output);

    }

    @Test
    void sendToSingleRecipient() {
        final ByteArrayOutputStream testOutputStream = new ByteArrayOutputStream();
        final ByteArrayOutputStream testEmptyOutputStream = new ByteArrayOutputStream();

        final Recipient targetRecipient = new Recipient("alice", testOutputStream);
        final Recipient otherRecipient = new Recipient("bob", testEmptyOutputStream);

        this.messagingService.registerRecipient(targetRecipient);
        this.messagingService.registerRecipient(otherRecipient);

        final Sender sender = new Sender("bob-sender");
        final TextMessage textMessage = sender.createTextMessage("subject", "content", new Attachment[0]);
        this.messagingService.send(textMessage, "alice");
        assertEquals("Printing text message: content\n", testOutputStream.toString());
        assertEquals("", testEmptyOutputStream.toString());

    }


}