package main.java.org.ce.ap.client;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The interface Command parser service.
 */
public interface CommandParserService {

    /**
     * Show main menu.
     *
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    void showMainMenu() throws IOException, ClassNotFoundException;

    /**
     * Refresh request.
     */
    void refreshRequest();


    /**
     * Process sign in.
     *
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    void processSignIn() throws IOException, ClassNotFoundException ;


    /**
     * Process sign up.
     *
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    void processSignUp() throws IOException, ClassNotFoundException ;


    /**
     * Add tweet.
     */
    void addTweet() ;

    /**
     * Request timeline.
     */
    void requestTimeline();

    /**
     * Show page menu.
     *
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    void showPageMenu() throws IOException, ClassNotFoundException ;

    /**
     * Follow.
     *
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    void follow() throws IOException, ClassNotFoundException ;

    /**
     * Unfollow.
     */
    public void unfollow();

    /**
     * Delete follower.
     */
    void deleteFollower();

    /**
     * Fix gson.
     */
    void fixGson();

    /**
     * Show followers and following.
     *
     * @throws IOException the io exception
     */
    void showFollowersAndFollowing() throws IOException;

    /**
     * Followers and followings menu.
     *
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    void  followersAndFollowingsMenu() throws IOException, ClassNotFoundException ;

    /**
     * My tweet.
     */
    void myTweet();

    /**
     * My favorite tweets.
     */
    void myFavoriteTweets();

    /**
     * Display page information.
     */
    void  DisplayPageInformation();

    /**
     * Edit profile.
     */
    void editProfile();

    /**
     * Request to retweet.
     */
    void requestToRetweet();


    /**
     * Request to like.
     */
    void requestToLike();

    /**
     * Send request and listen for response.
     *
     * @param title           the title
     * @param parameterValues the parameter values
     */
    void sendRequestAndListenForResponse(String title, ArrayList<Object> parameterValues);

}





