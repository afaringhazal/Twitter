package main.java.org.ce.ap.impl.server;

import main.java.org.ce.ap.server.Database;
import main.java.org.ce.ap.server.ObserverService;
import main.java.org.ce.ap.server.Tweet;

import java.util.ArrayList;
import java.util.logging.Logger;

public class ObserverServiceImpl implements ObserverService {


    Database database;
    Logger logger;
    public ObserverServiceImpl(Database database) {
        this.database = database;
        logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }

    @Override
    public synchronized boolean deleteFollower(String followerUserName, String userName) {
        logger.info("follower: "+followerUserName+"was requested to be deleted by "+ userName);
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

    @Override
    public  synchronized boolean follow(String UserNameFollowing, String userName) {
        logger.info("user: "+userName+" requested to follow "+ UserNameFollowing);
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

    @Override
    public synchronized boolean unfollow(String followingUsername, String userName) {
        logger.info("user: "+userName+" requested to unfollow "+ followingUsername);
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

    @Override
    public  ArrayList<String> getFollowers(String userName) {
        logger.info("all followers of username: "+userName+" were gathered and returned. details:\n"+database.getClientPageFromUsername(userName).getFollowers());
        return database.getClientPageFromUsername(userName).getFollowers();
    }

    @Override
    public ArrayList<String> getFollowings(String userName) {
        logger.info("all followings of username: "+userName+" were gathered and returned. details:\n"+database.getClientPageFromUsername(userName).getFollowingsList());

        return database.getClientPageFromUsername(userName).getFollowingsList();
    }

    @Override
    public ArrayList<Tweet> sendMyTweet(String userName)
    {        logger.info("all tweets of username: "+userName+" were gathered and returned. details:\n"+database.getClientPageFromUsername(userName).getTweets());

        return database.getClientPageFromUsername(userName).getTweets();
    }

}
