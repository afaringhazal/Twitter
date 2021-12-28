package main.java.org.ce.ap.server;

import java.time.LocalDateTime;

public class Retweet extends Message {
    Tweet tweet;
    String retweetClient;

    public Retweet(Tweet tweet, String retweetClient,String text) {

        this.tweet = tweet;
        this.retweetClient = retweetClient;
        this.date = LocalDateTime.now();
        this.text=text;

    }

    public Tweet getTweet() {
        return tweet;
    }

    public String getRetweetClient() {
        return retweetClient;
    }
}