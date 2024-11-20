package com.tim.se.messaging.sender;

import com.tim.se.messaging.message.Attachment;
import com.tim.se.messaging.message.AudioMessage;
import com.tim.se.messaging.message.TextMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SenderTest {

    private final Sender sender = new Sender("alice");

    @Test
    void createTextMessageWithoutAttachments() {
        final Attachment[] attachments = new Attachment[0];
        final TextMessage textMessage = this.sender.createTextMessage("subject", "content", attachments);
        assertEquals("subject", textMessage.getSubject());
        assertEquals("content", textMessage.getContent());
        assertEquals(0, textMessage.getAttachments().length);
        assertEquals(this.sender, textMessage.getSender());
    }

    @Test
    void createTextMessageWithAttachments() {
        final Attachment attachment = new Attachment("file", 120, "png", null);
        final Attachment[] attachments = new Attachment[]{attachment};
        final TextMessage textMessage = this.sender.createTextMessage("subject", "content", attachments);
        assertEquals("subject", textMessage.getSubject());
        assertEquals("content", textMessage.getContent());
        assertEquals(1, textMessage.getAttachments().length);
        assertEquals(attachment, textMessage.getAttachments()[0]);
        assertEquals(this.sender, textMessage.getSender());
    }

    @Test
    void createAudioMessage() {
        final Byte[] rawData = new Byte[]{1, 2, 3, 4, 5};
        final AudioMessage audioMessage = this.sender.createAudioMessage(rawData);
        assertEquals(5, audioMessage.getSize());
        assertEquals(0, audioMessage.getDuration());
        assertEquals(rawData, audioMessage.getRawData());
        assertEquals(this.sender, audioMessage.getSender());
    }
}