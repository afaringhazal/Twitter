import java.util.ArrayList;
import java.util.Iterator;

public class ObserverService {


    /**
     * @param follower
     * @param client
     */
    public void deleteFollower(Page follower, Client client){
        Server.getClientPage(client).deleteFollower(follower);
    }


    /** follows a client
     * @param following the  page of the person to be followed
     * @param client client who wants to follow someone
     */
    public void follow(Page following, Client client){

        Server.getClientPage(client).follow(following);
    }


    /** unfollows a client
     * @param following the  page of the person to be unfollowed
     * @param client client who wants to unfollow someone
     */
    public void unfollow(Page following, Client client){

        Server.getClientPage(client).unfollow(following);

    }

    /** returns the tweets of all of the people the client has followed
     * @param client the client who wants the tweets
     * @return tweets of people the client has followed
     */
    public ArrayList<Tweet> returnFollowingsTweets(Client client){
        return Server.getClientPage(client).getFollowingsTweets();
    }


    /** adds a new tweet to pages of people who have followed the tweeter
     * @param tweet the new tweet
     */
    public static void notification( Tweet tweet){

        for(Page page : Server.getClientPage(tweet.client).getFollowers())
        {
            page.addFollowingsTweet(tweet);
        }
    }


    /** deletes a tweet from the pages of people who have followed the tweeter
     * @param tweet the tweet to be deleted
     */
    public static void DeleteTweetForFollowings( Tweet tweet) {
        Iterator<Tweet> it;
        for (Page page : Server.getClientPage(tweet.client).getFollowers()) {
            it = page.getTweets().iterator();
            while (it.hasNext()) {
                if (it.equals(tweet)) {
                    it.remove();
                    break;
                }
                it.next();
            }
        }


    }















}
