package main.java.org.ce.ap.server;

import java.time.LocalDateTime;

/**
 * The type Reply.
 */
public class Reply extends Message {
    /**
     * The Message.
     */
    Message message;

    /**
     * Instantiates a new Reply.
     *
     * @param tweet          the tweet
     * @param text           the text
     * @param clientUsername the client username
     */
    public Reply(Message tweet, String text,String clientUsername) {
        this.clientUsername=clientUsername;
        this.message = tweet;
        this.date = LocalDateTime.now();
        this.text = text;

    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public Message getMessage() {
        return message;
    }
}