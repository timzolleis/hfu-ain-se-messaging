package com.tim.se.messaging.message;

import lombok.Getter;

@Getter
public class Attachment {
    private final String name;
    private final int size;
    private final String type;
    private final Byte[] rawData;

    public Attachment(final String name, final int size, final String type, final Byte[] rawData) {
        this.name = name;
        this.size = size;
        this.type = type;
        this.rawData = rawData;
    }
}

