package main.java.org.ce.ap.server;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * The interface Client handler.
 */
public interface ClientHandler extends Runnable {

    @Override
    void run();

    /**
     * Send my tweet and replies.
     *
     * @throws IOException the io exception
     */
    void sendMyTweetAndReplies() throws IOException;

    /**
     * Process sign up.
     *
     * @throws IOException              the io exception
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws ClassNotFoundException   the class not found exception
     */
    void processSignUp() throws IOException, NoSuchAlgorithmException, ClassNotFoundException;


    /**
     * Process sign in.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws IOException              the io exception
     */
    void processSignIn() throws NoSuchAlgorithmException, IOException;


    /**
     * Process tweet.
     *
     * @throws IOException the io exception
     */
    void processTweet() throws IOException;

    /**
     * Process time line.
     *
     * @throws IOException the io exception
     */
    void processTimeLine() throws IOException;

    /**
     * Show all followers.
     *
     * @throws IOException the io exception
     */
    void ShowAllFollowers() throws IOException;

    /**
     * Request delete follower.
     *
     * @throws IOException the io exception
     */
    void requestDeleteFollower() throws IOException;

    /**
     * Request unfollow.
     *
     * @throws IOException the io exception
     */
    void requestUnfollow() throws IOException;

    /**
     * Request follow.
     *
     * @throws IOException the io exception
     */
    void requestFollow() throws IOException;

    /**
     * Refresh response.
     */
    void refreshResponse();

    /**
     * Show all usernames.
     *
     * @throws IOException the io exception
     */
    void ShowAllUsernames() throws IOException;

    /**
     * Show followers and followings.
     *
     * @throws IOException the io exception
     */
    void showFollowersAndFollowings() throws IOException;

    /**
     * My favorite tweets.
     *
     * @throws IOException the io exception
     */
    void myFavoriteTweets() throws IOException;

    /**
     * Display page information.
     *
     * @throws IOException the io exception
     */
    void DisplayPageInformation() throws IOException;

    /**
     * Edit profile.
     */
    void editProfile();

    /**
     * Liked message.
     *
     * @throws IOException the io exception
     */
    void LikedMessage() throws IOException;

    /**
     * Retweet message.
     *
     * @throws IOException the io exception
     */
    void retweetMessage() throws IOException;

    /**
     * Delete tweet.
     *
     * @throws IOException the io exception
     */
    void deleteTweet() throws IOException;


}




