package main.java.org.ce.ap.server;

//import org.json.JSONArray;

import java.util.ArrayList;

/**
 * The interface Observer service.
 */
public interface ObserverService {


    /**
     * Delete follower boolean.
     *
     * @param followerUserName the follower user name
     * @param userName         the user name
     * @return the boolean
     */
     boolean deleteFollower(String followerUserName, String userName);

    /**
     * Follow boolean.
     *
     * @param UserNameFollowing the user name following
     * @param userName          the user name
     * @return the boolean
     */
     boolean follow(String UserNameFollowing, String userName);


    /**
     * Unfollow boolean.
     *
     * @param followingUsername the following username
     * @param userName          the user name
     * @return the boolean
     */
     boolean unfollow(String followingUsername, String userName);

    /**
     * Gets followers.
     *
     * @param userName the user name
     * @return the followers
     */
     ArrayList<String> getFollowers(String userName);

    /**
     * Gets followings.
     *
     * @param userName the user name
     * @return the followings
     */
     ArrayList<String> getFollowings(String userName);

    /**
     * Send my tweet array list.
     *
     * @param userName the user name
     * @return the array list
     */
     ArrayList<Message> sendMyTweet (String userName);

}
