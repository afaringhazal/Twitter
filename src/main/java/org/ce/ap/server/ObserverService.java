package main.java.org.ce.ap.server;

//import org.json.JSONArray;

import java.util.ArrayList;

public interface ObserverService {


    public boolean deleteFollower(String followerUserName, String userName);

    public boolean follow(String UserNameFollowing, String userName);


    public boolean unfollow(String followingUsername, String userName);
    public ArrayList<String> getFollowers(String userName);
    public ArrayList<String> getFollowings(String userName);
    public ArrayList<Tweet> sendMyTweet (String userName);

}
