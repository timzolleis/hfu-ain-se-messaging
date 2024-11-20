package com.tim.se.messaging.sender;

import com.tim.se.messaging.message.Attachment;
import com.tim.se.messaging.message.AudioMessage;
import com.tim.se.messaging.message.TextMessage;

public class Sender {
    private final String name;

    public Sender(String name) {
        this.name = name;
    }

    public final TextMessage createTextMessage(final String subject, final String content, final Attachment[] attachments) {
        return new TextMessage(subject, content, attachments, this);
    }

    public final AudioMessage createAudioMessage(final Byte[] rawData) {
        final int size = rawData.length;
        //Fake duration
        final int duration = 0;
        return new AudioMessage(size, duration, rawData, this);
    }
}
