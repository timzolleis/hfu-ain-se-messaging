package com.tim.se.messaging;


import com.tim.se.messaging.message.Message;
import com.tim.se.messaging.message.MessageReception;
import com.tim.se.messaging.recipient.Recipient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagingService {
    private final Map<String, Recipient> registeredRecipients = new HashMap<>();


    public void registerRecipient(final Recipient recipient) {
        if (this.registeredRecipients.containsKey(recipient.getName())) {
            throw new IllegalArgumentException("Recipient with name " + recipient.getName() + " already exists");
        }
        this.registeredRecipients.put(recipient.getName(), recipient);
    }

    public void unregisterRecipient(final Recipient recipient) {
        if (!this.registeredRecipients.containsKey(recipient.getName())) {
            throw new IllegalArgumentException("Recipient with name " + recipient.getName() + " does not exist");
        }
        this.registeredRecipients.remove(recipient.getName());
    }


    public final Recipient getRecipient(final String recipientName) {
        if (!this.registeredRecipients.containsKey(recipientName)) {
            throw new IllegalArgumentException("Recipient with name " + recipientName + " does not exist");
        }
        return this.registeredRecipients.get(recipientName);
    }

    public final MessageReception send(final Message message, final String recipientName) {
        final Recipient recipient = this.getRecipient(recipientName);
        return recipient.onMessageReceived(message);
    }

    public final List<MessageReception> send(final Message message) {
        return this.registeredRecipients.values().stream()
                .map(recipient -> recipient.onMessageReceived(message))
                .toList();
    }

}
