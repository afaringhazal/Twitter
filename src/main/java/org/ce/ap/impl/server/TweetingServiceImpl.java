package main.java.org.ce.ap.impl.server;

import main.java.org.ce.ap.ParameterValue;
import main.java.org.ce.ap.server.*;

public class TweetingServiceImpl implements TweetingService {

    Database database;
    protected int counter;


    public TweetingServiceImpl(Database database) {
        this.database = database;
        counter = 1;
    }

    @Override
    public boolean addTweet(Tweet tweet) {
        tweet.id = counter;
        counter++;
        return database.getClientPageFromUsername(tweet.clientUsername).addTweet(tweet);

    }

    @Override

    public void deleteTweet(Tweet tweet) {
        database.getClientPageFromUsername(tweet.getClient()).getTweets().remove(tweet);
        // observerService.DeletingForOtherPeople(database.getClientPageFromUsername(tweet.clientUsername).getClient(), tweet);
    }

    @Override
    public void like(String clientUsername, Message tweet, String mine) {

        System.out.println("The clientUsername : "+clientUsername);
        System.out.println("The mine : "+ mine);


        for (Tweet t : database.getClientPageFromUsername(clientUsername).getTweets()) {
            if (t.equals(tweet)) {
                System.out.println("find tweet in page client");
                for (String username : t.getLikes()) {
                    if (username.equals(mine)){
                        System.out.println(mine + " exit in likedList => disLike");
                        // before exit , now dislike
                        disLike(clientUsername, t, mine);
                        database.getClientPage(database.getClientFromUsername(mine)).addOrDislikeTweet(t);
                        return;
                    }
                }

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
                return new ParameterValue("Tweet",tweet);
            }

        }


        for (Retweet retweet : database.getAllRetweets()) {
            if (retweet.id==id) {
                return new ParameterValue("Retweet",retweet);
            }

        }


        for (Reply reply: database.getAllReplies()) {
            if (reply.id==id) {
                return new ParameterValue("Reply",reply);
            }

        }
        return null;
    }



    public void LikeRetweet(String clientUsername, Message tweet, String mine)
    {
        for (Retweet t : database.getClientPageFromUsername(clientUsername).getRetweets()) {
            if (t.equals(tweet)) {
                for (String username : t.getLikes()) {
                    if (username.equals(mine)){
                        // before exit , now dislike
                        disLikeRetweet(clientUsername, t, mine);
                        database.getClientPage(database.getClientFromUsername(mine)).addOrDislikeTweet(t);
                    }
                }

                t.Like(mine);
                database.getClientPage(database.getClientFromUsername(mine)).addOrDislikeTweet(t);

                //Like++
                return;
            }
        }




    }

    public void LikeReply(String clientUsername, Message tweet, String mine){
        for (Reply t : database.getClientPageFromUsername(clientUsername).getReplies()) {
            if (t.equals(tweet)) {
                for (String username : t.getLikes()) {
                    if (username.equals(mine)){
                        // before exit , now dislike
                        disLikeReply(clientUsername, t, mine);
                        database.getClientPage(database.getClientFromUsername(mine)).addOrDislikeTweet(t);
                    }
                }

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
    private void disLikeReply(String clientUsername, Message tweet, String mine) {
        for (Reply t : database.getClientPage(database.getClientFromUsername(clientUsername)).getReplies()) {
            if (t.equals(tweet)) {
                t.dislike(mine);
                return;
            }
        }


    }


}