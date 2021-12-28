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
    private ArrayList<Page> followersList;
    private ArrayList<Page> followingsList;
    private ArrayList<Tweet> followingsTweets;
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
        followingsTweets=new ArrayList<>();

        if (biography.length() > 256)
            throw new RuntimeException("More than 256!");
        else
            this.biography = biography;

    }


    public void addFollowing(Page page) {
        followingsList.add(page);
    }

    public void deleteFollowing(Page page) {
        followingsList.remove(page);
    }

    public void deleteFollower(Page page) {
        followersList.remove(page);
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

    public void addFollowingsTweet(Tweet tweet) {
        followingsTweets.add(tweet);
    }

    //setters and getters

    public void addTweet(Tweet tweet){
        tweets.add(tweet);
    }
    public ArrayList<Tweet> getTweets() {
        return tweets;
    }

    public ArrayList<Page> getFollowers() {
        return followersList;
    }

    public ArrayList<Page> getFollowingsList() {
        return followingsList;
    }

    public ArrayList<Tweet> getFollowingsTweets() {
        return followingsTweets;
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
}