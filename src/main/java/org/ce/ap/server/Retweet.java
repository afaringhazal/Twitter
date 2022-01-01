package main.java.org.ce.ap.server;
import java.time.LocalDateTime;

/**
 * class : The type Retweet.
 * @author MohammadHdi sheikhEslami
 * @author Rezvan Afari
 * @version 1.0.0
 */
public class Retweet extends Message {
    /**
     * The Tweet.
     */
    Message tweet;


    /**
     * Instantiates a new Retweet.
     *
     * @param tweet         the tweet
     * @param retweetClient the retweet client
     * @param text          the text
     */
    public Retweet(Message tweet, String retweetClient,String text) {

        this.tweet = tweet;
        this.clientUsername = retweetClient;
       // this.retweetClient = retweetClient;
        this.date = LocalDateTime.now();
        this.text=text;

    }

    /**
     * Gets tweet.
     *
     * @return the tweet
     */
    public Message getTweet() {
        return tweet;
    }

    /**
     * Gets retweet client.
     *
     * @return the retweet client
     */
    public String getRetweetClient() {
        return clientUsername;
    }
}