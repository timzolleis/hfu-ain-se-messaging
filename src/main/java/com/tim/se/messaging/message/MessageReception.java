package com.tim.se.messaging.message;

import lombok.Getter;

import java.util.Date;

@Getter
public class MessageReception {
    private final String recipientName;
    private final boolean read;
    private final Date timestamp;

    public MessageReception(final String recipientName, final boolean read, final Date timestamp) {
        this.recipientName = recipientName;
        this.read = read;
        this.timestamp = timestamp;
    }

}
