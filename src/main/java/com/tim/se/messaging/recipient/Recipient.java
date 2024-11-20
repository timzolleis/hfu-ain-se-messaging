package com.tim.se.messaging.recipient;

import com.tim.se.messaging.message.*;
import com.tim.se.messaging.player.AudioMessagePlayer;
import com.tim.se.messaging.player.TextMessagePlayer;
import lombok.Getter;

import java.io.OutputStream;
import java.util.Date;

@Getter
public class Recipient {
    private final String name;
    private final OutputStream outputStream;

    public Recipient(final String name, final OutputStream outputStream) {
        this.name = name;
        this.outputStream = outputStream;
    }

    public final MessageReception onMessageReceived(final Message message) {
        try {
            this.handlePlayMessage(message);
        } catch (IllegalArgumentException e) {
            System.out.println("Unable to play message: " + e.getMessage());
            return new MessageReception(this.name, false, new Date());
        }
        return new MessageReception(this.name, true, new Date());
    }

    private void handlePlayMessage(final Message message) {
        try {
            if (message instanceof TextMessage textMessage) {
                final TextMessagePlayer player = new TextMessagePlayer(this.outputStream);
                player.play(textMessage);
                return;
            }
            if (message instanceof AudioMessage audioMessage) {
                final AudioMessagePlayer player = new AudioMessagePlayer(this.outputStream);
                player.play(audioMessage);
                return;
            }
        } catch (Exception e) {
            System.out.println("Unable to play message: " + e.getMessage());
        }
        throw new IllegalArgumentException("Unknown message type");
    }
}
