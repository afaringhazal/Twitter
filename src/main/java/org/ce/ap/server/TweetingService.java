package main.java.org.ce.ap.server;

import main.java.org.ce.ap.ParameterValue;

public interface TweetingService {



    public boolean addTweet(Tweet tweet);

    public void deleteTweet(Tweet tweet) ;

    public void like(String clientUsername, Message tweet, String mine) ;

    public ParameterValue findMessage(int id);
    public void LikeRetweet(String clientUsername, Message tweet, String mine);

    public void LikeReply(String clientUsername, Message tweet, String mine);
}