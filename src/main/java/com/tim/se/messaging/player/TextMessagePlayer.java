package com.tim.se.messaging.player;

import com.tim.se.messaging.message.TextMessage;

import java.io.OutputStream;

public class TextMessagePlayer extends MessagePlayer {

    public TextMessagePlayer(final OutputStream outputStream) {
        super(outputStream);
    }

    public void play(final TextMessage message) {
        this.write(String.format("Printing text message: %s\n", message.getContent()).getBytes());
    }
}
