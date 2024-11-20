package com.tim.se.messaging.message;

import com.tim.se.messaging.sender.Sender;
import lombok.Getter;

@Getter
public class TextMessage extends Message {
    private final String subject;
    private final String content;
    private final Attachment[] attachments;

    public TextMessage(final String subject, final String content, final Attachment[] attachments, final Sender sender) {
        super(sender);
        this.subject = subject;
        this.content = content;
        this.attachments = attachments;
    }
}
