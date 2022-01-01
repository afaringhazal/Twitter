package main.java.org.ce.ap.impl.server;

import main.java.org.ce.ap.ParameterValue;
import main.java.org.ce.ap.server.*;

import java.util.logging.Logger;

public class TweetingServiceImpl implements TweetingService {

    Database database;
    protected int counter;
Logger logger;

    public TweetingServiceImpl(Database database) {
        this.database = database;
        logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        counter = 1;
    }

    @Override
    public synchronized boolean addTweet(Tweet tweet) {
        tweet.id = counter;
        counter++;
        logger.info("tweet "+tweet.id +"was added.");
        return database.getClientPageFromUsername(tweet.clientUsername).addTweet(tweet);

    }

    @Override
 // we have problem this method
    public synchronized boolean deleteTweet(int id) {

        ParameterValue pm = findMessage(id);
        if (pm == null) {
            logger.info("delete tweet was requested but tweet did not exist.");
            return false;
        }
        if (pm.getName().equals("Tweet")) {

            Tweet tweet = ((Tweet) pm.getValue());
            logger.info("delete tweet was requested for tweet: "+tweet.id+ "and was removed.");
           return database.getClientPageFromUsername(tweet.clientUsername).removeTweet(tweet);
        } else {

            Retweet tweet = ((Retweet) pm.getValue());
            logger.info("delete tweet was requested for retweet: "+tweet.id+ "and was removed.");
            return database.getClientPageFromUsername(tweet.clientUsername).removeRetweet(tweet);
        }
    }
    @Override


    public synchronized void like(String clientUsername, Message tweet, String mine) {

        System.out.println("The clientUsername : "+clientUsername);
        System.out.println("The mine : "+ mine);


        for (Tweet t : database.getClientPageFromUsername(clientUsername).getTweets()) {
            if (t.equals(tweet)) {
                System.out.println("find tweet in page client");
                for (String username : t.getLikes()) {
                    if (username.equals(mine)){
                        System.out.println(mine + " exit in likedList => disLike");
                        logger.info("tweet :"+tweet.id+" was already liked by"+ clientUsername+" so it was disliked.");
                        disLike(clientUsername, t, mine);
                        database.getClientPage(database.getClientFromUsername(mine)).addOrDislikeTweet(t);
                        return;
                    }
                }
                logger.info("tweet :"+tweet.id+" was by"+ clientUsername+" .");
                t.Like(mine);
                System.out.println(mine+" add in Like List in tweet t :" +t.getText());
                database.getClientPageFromUsername(mine).addOrDislikeTweet(t);
                //Like++
                return;
            }
        }

    }


    private void disLike(String clientUsername, Message tweet, String mine) {
        for (Tweet t : database.getClientPage(database.getClientFromUsername(clientUsername)).getTweets()) {
            if (t.equals(tweet)) {
                t.dislike(mine);
                return;
            }
        }


    }

    public ParameterValue findMessage(int id) {


        for (Tweet tweet : database.getAllTweets()) {
            if (tweet.id==id) {
                logger.info("tweet with id: "+id+"was requested to find and was found.");
                return new ParameterValue("Tweet",tweet);
            }

        }


        for (Retweet retweet : database.getAllRetweets()) {
            if (retweet.id==id) {
                logger.info("retweet with id: "+id+"was requested to find and was found.");
                return new ParameterValue("Retweet",retweet);
            }

        }

        logger.info("message with id: "+id+"was requested to find but was not found.");
        return null;
    }



    public synchronized void LikeRetweet(String clientUsername, Message tweet, String mine)
    {
        for (Retweet t : database.getClientPageFromUsername(clientUsername).getRetweets()) {
            if (t.equals(tweet)) {
                for (String username : t.getLikes()) {
                    if (username.equals(mine)){
                        logger.info("retweet with id: "+ t.clientUsername+"was requested to be liked by"+ clientUsername+"but was already liked so got disliked.");
                        disLikeRetweet(clientUsername, t, mine);
                        database.getClientPage(database.getClientFromUsername(mine)).addOrDislikeTweet(t);
                    }
                }
                logger.info("retweet with id: "+ t.clientUsername+"was requested to be liked by"+ clientUsername+"and was liked.");

                t.Like(mine);
                database.getClientPage(database.getClientFromUsername(mine)).addOrDislikeTweet(t);

                //Like++
                return;
            }
        }




    }


    private void disLikeRetweet(String clientUsername, Message tweet, String mine) {
        for (Retweet t : database.getClientPage(database.getClientFromUsername(clientUsername)).getRetweets()) {
            if (t.equals(tweet)) {
                t.dislike(mine);
                return;
            }
        }


    }



    public synchronized boolean addRetweet(Message tweet ,String userName,String quoteTweet) {
        Retweet retweet = new Retweet(tweet,userName,quoteTweet);
        retweet.id = counter;
        counter++;
        logger.info("retweet "+tweet.id +"was added. retweet source:"+ tweet.id+" .");
        return  tweet.addUserNameToRetweet(userName) &&  database.getClientPageFromUsername(userName).addRetweet(retweet);

    }
}