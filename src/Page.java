import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Iterator;

public class Page {

    private Client client;
    private String id;
    private String biography;
    private LocalDate joinDate;

    private ArrayList<Tweet> tweets;
    private ArrayList<Retweet> retweets;
    private ArrayList<Reply> replies;
    private ArrayList<Message> likedTweetsList;

    private ArrayList<Page> followersList;
    private ArrayList<Page> followingsList;
    private ArrayList<Tweet> followingsTweets;



    /** constructor of page
     * @param client owner of the page
     * @param id client id
     * @param biography a short text about client
     * @param joinDate date of creation of this page
     * @throws RuntimeException
     */
    public Page(Client client, String id, String biography, LocalDate joinDate) throws RuntimeException {

        this.client = client;
        this.id = id;
        this.biography = biography;
        this.joinDate = joinDate;
        tweets = new ArrayList<>();
        followersList = new ArrayList<>();
        followingsList = new ArrayList<>();
        likedTweetsList = new ArrayList<>();

        if (biography.length() > 256)
            throw new RuntimeException("More than 256!");
        else
            this.biography = biography;

    }


    /** follows a page
     * @param page the page to be followed
     */
    public void follow(Page page) {
        followingsList.add(page);
    }

    /** unfollows a page
     * @param page the page to be unfollowed
     */
    public void unfollow(Page page) {
        followingsList.remove(page);
    }

    /** deletes a follower from followers' list
     * @param page deleted follower
     */
    public void deleteFollower(Page page) {
        followersList.remove(page);
    }


    /** adds a tweet to the list of followings' tweets
     * @param tweet the tweet to be added
     */
    public void addFollowingsTweet(Tweet tweet) {
        followingsTweets.add(tweet);
    }

    //setters and getters

    public String getBiography() {
        return biography;
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
        return likedTweetsList;
    }

    public ArrayList<Retweet> getRetweets() {
        return retweets;
    }

    public ArrayList<Reply> getReplies() {
        return replies;
    }
}