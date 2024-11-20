package com.tim.se.messaging.player;

import com.tim.se.messaging.message.AudioMessage;

import java.io.OutputStream;

public class AudioMessagePlayer extends MessagePlayer {


    public AudioMessagePlayer(OutputStream stream) {
        super(stream);
    }

    public void play(final AudioMessage message) {
        this.write(String.format("Playing audio message: %d bytes, %d seconds\n", message.getSize(), message.getDuration()).getBytes());
    }
}
