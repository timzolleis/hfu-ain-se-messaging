package com.tim.se.messaging.message;

import com.tim.se.messaging.sender.Sender;
import lombok.Getter;

@Getter
public class AudioMessage extends Message {
    private final int size;
    private final int duration;
    private final Byte[] rawData;

    public AudioMessage(final int size, final int duration, final Byte[] rawData, final Sender sender) {
        super(sender);
        this.size = size;
        this.duration = duration;
        this.rawData = rawData;
    }
}
