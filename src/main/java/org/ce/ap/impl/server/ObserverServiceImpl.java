package org.ce.ap.impl.server;
import org.ce.ap.server.Database;
import org.ce.ap.server.Message;
import org.ce.ap.server.ObserverService;
import org.ce.ap.server.Tweet;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * class : The type Observer service.
 * @author MohammadHdi sheikhEslami
 * @author Rezvan Afari
 * @version 1.0.0
 */
public class ObserverServiceImpl implements ObserverService {

    /**
     * The Database.
     */
    Database database;

    /**
     * The Logger.
     */
    Logger logger;

    /**
     * Instantiates a new Observer service.
     *
     * @param database the database
     */
    public ObserverServiceImpl(Database database) {
        this.database = database;
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }

    /**
     * Delete follower boolean.
     *
     * @param followerUserName the follower user name
     * @param userName         the user name
     * @return the boolean
     */
    @Override
    public synchronized boolean deleteFollower(String followerUserName, String userName) {
        logger.info("follower: " + followerUserName + "was requested to be deleted by " + userName);
        for (String availableUserName : database.getClientPageFromUsername(userName).getFollowers()) {
            if (availableUserName.equals(followerUserName)) {
                logger.info("follower was deleted.");
                return database.getClientPageFromUsername(userName).deleteFollower(followerUserName)
                        && database.getClientPageFromUsername(followerUserName).deleteFollowing(userName);

            }
        }
        logger.info("follower was not deleted because they were not a follower.");
        return false;

    }

    /**
     * Follow boolean.
     *
     * @param UserNameFollowing the user name following
     * @param userName          the user name
     * @return the boolean
     */
    @Override
    public synchronized boolean follow(String UserNameFollowing, String userName) {
        logger.info("user: " + userName + " requested to follow " + UserNameFollowing);
        if (UserNameFollowing.equals(userName)) {
            logger.info("request was denied because the user cannot follow themself.");
            return false;
        }

        for (String availableUserName : database.getUserNames()) {
            if (availableUserName.equals(UserNameFollowing)) {

                for (String reFollow : database.getClientPageFromUsername(userName).getFollowingsList()) {
                    if (reFollow.equals(UserNameFollowing)) {
                        logger.info("request was denied because the user cannot follow someone twice.");
                        return false;
                    }
                }

                logger.info("request was accepted and user was followed");
                return database.getClientPageFromUsername(userName).addFollowing(UserNameFollowing)
                        && database.getClientPageFromUsername(UserNameFollowing).addFollower(userName);

            }
        }
        return false;

    }

    /**
     * Unfollow boolean.
     *
     * @param followingUsername the following username
     * @param userName          the user name
     * @return the boolean
     */
    @Override
    public synchronized boolean unfollow(String followingUsername, String userName) {
        logger.info("user: " + userName + " requested to unfollow " + followingUsername);
        for (String availableUserName : database.getClientPageFromUsername(userName).getFollowingsList()) {
            if (availableUserName.equals(followingUsername)) {
                logger.info("request was accepted and user was unfollowed.");
                return database.getClientPageFromUsername(userName).deleteFollowing(followingUsername)
                        && database.getClientPageFromUsername(followingUsername).deleteFollower(userName);

            }
        }
        logger.info("request was denied because the user hadn't followed the requested username.");
        return false;

    }

    /**
     * Gets followers.
     *
     * @param userName the user name
     * @return the followers
     */
    @Override
    public ArrayList<String> getFollowers(String userName) {
        logger.info("all followers of username: " + userName + " were gathered and returned. details:\n" + database.getClientPageFromUsername(userName).getFollowers());
        return database.getClientPageFromUsername(userName).getFollowers();
    }

    /**
     * Gets followings.
     *
     * @param userName the user name
     * @return the followings
     */
    @Override
    public ArrayList<String> getFollowings(String userName) {
        logger.info("all followings of username: " + userName + " were gathered and returned. details:\n" + database.getClientPageFromUsername(userName).getFollowingsList());

        return database.getClientPageFromUsername(userName).getFollowingsList();
    }

    /**
     * Send my tweet array list.
     *
     * @param userName the user name
     * @return the array list
     */
    @Override
    public ArrayList<Message> sendMyTweet(String userName) {
        logger.info("all tweets of username: " + userName + " were gathered and returned. details:\n" + database.getClientPageFromUsername(userName).getTweets());
        ArrayList<Message> myTweet = new ArrayList<>();
        myTweet.addAll(database.getClientPageFromUsername(userName).getTweets());
        myTweet.addAll(database.getClientPageFromUsername(userName).getRetweets());
        return myTweet;
    }

}
