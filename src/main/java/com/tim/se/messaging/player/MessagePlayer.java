package com.tim.se.messaging.player;

import java.io.OutputStream;

public class MessagePlayer {
    private OutputStream stream = null;

    public MessagePlayer(final OutputStream stream) {
        this.stream = stream;
    }

    protected void write(final byte[] content) {
        if (this.stream == null) {
            throw new IllegalArgumentException("Stream is null");
        }
        try {
            this.stream.write(content);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to write message: " + e.getMessage());
        }
    }


}
