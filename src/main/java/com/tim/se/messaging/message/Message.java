package com.tim.se.messaging.message;

import com.tim.se.messaging.sender.Sender;
import lombok.Getter;

import java.util.Date;

@Getter
public class Message {

    private final String id;
    private final Date createdAt;
    private final Sender sender;

    public Message(final Sender sender) {
        this.createdAt = new Date();
        //Fake ID
        this.id = String.valueOf(createdAt.getTime());
        this.sender = sender;
    }
}
