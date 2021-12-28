package main.java.org.ce.ap.server;

import java.time.LocalDateTime;

public class Reply extends Message {
    Message message;

    public String getReplier() {
        return replier;
    }

    String replier;
    boolean repliedOnAReply=false;

    public Reply(Message tweet, String replier, String text) {

        this.message = tweet;



         if(tweet instanceof Reply) {
             repliedOnAReply = true;
         }

        this.replier = replier;
        this.date = LocalDateTime.now();
        this.text = text;

    }



}