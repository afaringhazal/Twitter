package org.ce.ap.server;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * class : The type Page.
 * @author MohammadHdi sheikhEslami
 * @author Rezvan Afari
 * @version 1.0.0
 */
public class Page implements Serializable {

    private Client client;
    private String id;
    private String biography;
    private LocalDate joinDate;
    private ArrayList<Tweet> tweets;
    private ArrayList<String> followersList;
    private ArrayList<String> followingsList;
    private ArrayList<Retweet> retweets;
    private ArrayList<Message> LikedTweetsList;
    private ArrayList<Reply> replies ;


    /**
     * Instantiates a new Page.
     *
     * @param client    the client
     * @param id        the id
     * @param biography the biography
     * @param joinDate  the join date
     * @throws RuntimeException the runtime exception
     */
    public Page(Client client, String id, String biography, LocalDate joinDate) throws RuntimeException {

        this.client = client;
        this.id = id;
        this.biography = biography;
        this.joinDate = joinDate;
        tweets = new ArrayList<>();
        followersList = new ArrayList<>();
        followingsList = new ArrayList<>();
        LikedTweetsList = new ArrayList<>();
        replies = new ArrayList<>();
        retweets=new ArrayList<>();

        if (biography.length() > 256)
            throw new RuntimeException("More than 256!");
        else
            this.biography = biography;

    }

    /**
     * Gets replies.
     *
     * @return the replies
     */
    public ArrayList<Reply> getReplies() {
        return replies;
    }

    /**
     * Add following boolean.
     *
     * @param username the username
     * @return the boolean
     */
    public  boolean addFollowing(String username) {
        return followingsList.add(username);
    }

    /**
     * Add follower boolean.
     *
     * @param userName the user name
     * @return the boolean
     */
    public boolean addFollower(String userName)
    {
        return followersList.add(userName);
    }

    /**
     * Delete following boolean.
     *
     * @param username the username
     * @return the boolean
     */
    public boolean deleteFollowing(String username) {
        return followingsList.remove(username);
    }

    /**
     * Delete follower boolean.
     *
     * @param username the username
     * @return the boolean
     */
    public boolean deleteFollower(String username) {
        return followersList.remove(username);
    }

    /**
     * Gets biography.
     *
     * @return the biography
     */
    public String getBiography() {
        return biography;
    }


    /**
     * Add or dislike tweet.
     *
     * @param tweet the tweet
     */
    public void addOrDislikeTweet(Message tweet) {

        System.out.println("Now we in addOrDislikedTweet :");
        Iterator<Message> it = LikedTweetsList.iterator();
        while (it.hasNext()) {
            if (it.next().equals(tweet)) {
               // System.out.println("This tweet has been liked before and is now disliked!");
                it.remove();
                System.out.println("Exit Tweet So Dislike");
                return;

            }
        }

        System.out.println("don't Exit => so added");
        LikedTweetsList.add(tweet);
    }


    /**
     * Add retweet boolean.
     *
     * @param tweet the tweet
     * @return the boolean
     */
    public boolean addRetweet(Retweet tweet) {
        return retweets.add(tweet);
    }

    /**
     * Remove retweet boolean.
     *
     * @param tweet the tweet
     * @return the boolean
     */
    public boolean removeRetweet(Retweet tweet) {
        return retweets.remove(tweet);
    }

    /**
     * Remove tweet boolean.
     *
     * @param tweet the tweet
     * @return the boolean
     */
    public boolean removeTweet(Tweet tweet) {
        return tweets.remove(tweet);
    }

    /**
     * Add tweet boolean.
     *
     * @param tweet the tweet
     * @return the boolean
     */
    public boolean addTweet(Tweet tweet){
        return tweets.add(tweet);
    }

    /**
     * Gets tweets.
     *
     * @return the tweets
     */
    public ArrayList<Tweet> getTweets() {
        return tweets;
    }

    /**
     * Gets followers.
     *
     * @return the followers
     */
    public ArrayList<String> getFollowers() {
        return followersList;
    }

    /**
     * Gets followings list.
     *
     * @return the followings list
     */
    public ArrayList<String> getFollowingsList() {
        return followingsList;
    }

    /**
     * Gets retweets.
     *
     * @return the retweets
     */
    public ArrayList<Retweet> getRetweets() {
        return retweets;
    }

    /**
     * Gets liked tweets list.
     *
     * @return the liked tweets list
     */
    public ArrayList<Message> getLikedTweetsList() {
        return LikedTweetsList;
    }


    /**
     * Gets client.
     *
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }


    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets biography.
     *
     * @param biography the biography
     */
    public void setBiography(String biography) {
        this.biography = biography;
    }

    /**
     * Gets join date.
     *
     * @return the join date
     */
    public LocalDate getJoinDate() {
        return joinDate;
    }
}