package main.java.org.ce.ap.server;

public interface TweetingService {



    public boolean addTweet(Tweet tweet);

    public void deleteTweet(Tweet tweet) ;

    public void like(Client client, Tweet tweet, Client mine) ;


}