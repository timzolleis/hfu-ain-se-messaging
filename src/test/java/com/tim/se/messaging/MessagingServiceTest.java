package com.tim.se.messaging;

import com.tim.se.messaging.message.Attachment;
import com.tim.se.messaging.message.MessageReception;
import com.tim.se.messaging.message.TextMessage;
import com.tim.se.messaging.recipient.Recipient;
import com.tim.se.messaging.sender.Sender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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


    @Test
    void sendToAllRecipients() {
        final ByteArrayOutputStream aliceOutputStream = new ByteArrayOutputStream();
        final ByteArrayOutputStream bobOutputStream = new ByteArrayOutputStream();

        final Recipient alice = new Recipient("alice", aliceOutputStream);
        final Recipient bob = new Recipient("bob", bobOutputStream);

        this.messagingService.registerRecipient(alice);
        this.messagingService.registerRecipient(bob);

        final Sender sender = new Sender("bob-sender");
        final TextMessage textMessage = sender.createTextMessage("subject", "content", new Attachment[0]);
        final List<MessageReception> receptions = this.messagingService.send(textMessage);
        assertEquals(2, receptions.size());
        assertTrue(receptions.stream().map(MessageReception::getRecipientName).collect(Collectors.toSet()).containsAll(Set.of("alice", "bob")));

        final String expectedOutput = "Printing text message: content\n";

        assertEquals(expectedOutput, aliceOutputStream.toString());
        assertEquals(expectedOutput, bobOutputStream.toString());

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
        final MessageReception reception = this.messagingService.send(textMessage, "alice");
        assertEquals(targetRecipient.getName(), reception.getRecipientName());
        assertEquals(true, reception.getRead());
        assertEquals("Printing text message: content\n", testOutputStream.toString());
        assertEquals("", testEmptyOutputStream.toString());

    }


}