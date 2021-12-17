import java.time.LocalDateTime;

public class Retweet extends Message {
    Tweet tweet;
    Client retweetClient;

    public Retweet(Tweet tweet, Client retweetClient) {

        this.tweet = tweet;
        this.retweetClient = retweetClient;
        this.date = LocalDateTime.now();

    }
}