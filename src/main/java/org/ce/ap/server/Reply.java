package main.java.org.ce.ap.server;

import main.java.org.ce.ap.client.Client;

import java.time.LocalDateTime;

public class Reply extends Message {
    Message message;

    public Client getReplier() {
        return replier;
    }

    Client replier;
    boolean repliedOnAReply=false;

    public Reply(Message tweet, Client replier, String text) {

        this.message = tweet;



         if(tweet instanceof Reply) {
             repliedOnAReply = true;
         }

        this.replier = replier;
        this.date = LocalDateTime.now();
        this.text = text;

    }



    /** returns the first message that was replied
     * @return the first message that was replied
     */
    public Tweet getTweetOrigin() {
        Message temp = message;
        if (repliedOnAReply) {
            while (temp instanceof Reply) {
                temp = ((Reply) temp).message;
            }

        }
        return (Tweet) temp;
    }



}