package main.java.org.ce.ap.server;

import main.java.org.ce.ap.ParameterValue;

public interface TweetingService {



    public boolean addTweet(Tweet tweet);


    public boolean deleteTweet(int id);


    public void like(String clientUsername, Message tweet, String mine) ;

    public ParameterValue findMessage(int id);
    public void LikeRetweet(String clientUsername, Message tweet, String mine);



    public boolean addRetweet(Message tweet ,String userName,String quoteTweet);

}