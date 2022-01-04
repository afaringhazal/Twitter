package main.java.org.ce.ap.server;

import main.java.org.ce.ap.ParameterValue;

/**
 * The interface Tweeting service.
 */
public interface TweetingService {

    /**
     * Add tweet boolean.
     *
     * @param tweet the tweet
     * @return the boolean
     */
    boolean addTweet(Tweet tweet);


    /**
     * Delete tweet boolean.
     *
     * @param id                    the id
     * @param userNameWantsToDelete the username wants to delete
     * @return the boolean
     */
    boolean deleteTweet(int id,  String usernameWantsToDelete);


    /**
     * Like.
     *
     * @param clientUsername the client username
     * @param tweet          the tweet
     * @param mine           the mine
     */
    void like(String clientUsername, Message tweet, String mine) ;

    /**
     * Find message parameter value.
     *
     * @param id the id
     * @return the parameter value
     */
    ParameterValue findMessage(int id);

    /**
     * Like retweet.
     *
     * @param clientUsername the client username
     * @param tweet          the tweet
     * @param mine           the mine
     */
    void LikeRetweet(String clientUsername, Message tweet, String mine);

    /**
     * Add retweet boolean.
     *
     * @param tweet      the tweet
     * @param userName   the user name
     * @param quoteTweet the quote tweet
     * @return the boolean
     */
    boolean addRetweet(Message tweet ,String userName,String quoteTweet);

}