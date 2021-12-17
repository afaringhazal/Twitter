import java.time.LocalDateTime;

public class Retweet extends Message {
    Tweet tweet;
    Client retweetClient;

    /** constructor of retweet
     * @param tweet the retweeted tweet
     * @param retweetClient the person who retweeted
     */
    public Retweet(Tweet tweet, Client retweetClient) {

        this.tweet = tweet;
        this.retweetClient = retweetClient;
        this.date = LocalDateTime.now();

    }
}