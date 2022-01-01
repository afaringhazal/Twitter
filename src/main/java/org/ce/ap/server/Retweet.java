package main.java.org.ce.ap.server;

import java.time.LocalDateTime;

public class Retweet extends Message {
    Message tweet;


    public Retweet(Message tweet, String retweetClient,String text) {

        this.tweet = tweet;
        this.clientUsername = retweetClient;
       // this.retweetClient = retweetClient;
        this.date = LocalDateTime.now();
        this.text=text;

    }

    public Message getTweet() {
        return tweet;
    }

    public String getRetweetClient() {
        return clientUsername;
    }
}