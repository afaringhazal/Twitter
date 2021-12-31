package main.java.org.ce.ap.server;

import java.time.LocalDateTime;

public class Reply extends Message {
    Message message;
    public Reply(Message tweet, String text,String clientUsername) {
        this.clientUsername=clientUsername;
        this.message = tweet;
        this.date = LocalDateTime.now();
        this.text = text;

    }

    public Message getMessage() {
        return message;
    }
}