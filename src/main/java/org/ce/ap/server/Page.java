package main.java.org.ce.ap.server;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Iterator;

public class Page {

    private Client client;
    private String id;
    private String biography;
    private LocalDate joinDate;
    private ArrayList<Tweet> tweets;
    private ArrayList<String> followersList;
    private ArrayList<String> followingsList;
    private ArrayList<Tweet> retweets;
    private ArrayList<Message> LikedTweetsList;


    public Page(Client client, String id, String biography, LocalDate joinDate) throws RuntimeException {

        this.client = client;
        this.id = id;
        this.biography = biography;
        this.joinDate = joinDate;
        tweets = new ArrayList<>();
        followersList = new ArrayList<>();
        followingsList = new ArrayList<>();
        LikedTweetsList = new ArrayList<>();
        retweets=new ArrayList<>();

        if (biography.length() > 256)
            throw new RuntimeException("More than 256!");
        else
            this.biography = biography;

    }


    public  boolean addFollowing(String username) {
        return followingsList.add(username);
    }
    public boolean addFollower(String userName)
    {
        return followersList.add(userName);
    }

    public boolean deleteFollowing(String username) {
        return followingsList.remove(username);
    }

    public boolean deleteFollower(String username) {
        return followersList.remove(username);
    }

    public String getBiography() {
        return biography;
    }


    public void addOrDislikeTweet(Tweet tweet) {

        Iterator<Message> it = LikedTweetsList.iterator();
        while (it.hasNext()) {
            if (it.equals(tweet)) {
               // System.out.println("This tweet has been liked before and is now disliked!");
                it.remove();
                return;

            }
        }

        LikedTweetsList.add(tweet);
    }

    public boolean addRetweet(Tweet tweet) {
        return retweets.add(tweet);
    }

    public boolean addTweet(Tweet tweet){
        return tweets.add(tweet);
    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }

    public ArrayList<String> getFollowers() {
        return followersList;
    }

    public ArrayList<String> getFollowingsList() {
        return followingsList;
    }

    public ArrayList<Tweet> getRetweets() {
        return retweets;
    }

    public ArrayList<Message> getLikedTweetsList() {
        return LikedTweetsList;
    }


    public Client getClient() {
        return client;
    }

    public String getId() {
        return id;
    }


    public LocalDate getJoinDate() {
        return joinDate;
    }
}