package main.java.org.ce.ap.impl.server;

import main.java.org.ce.ap.server.Client;
import main.java.org.ce.ap.server.Database;
import main.java.org.ce.ap.server.Tweet;
import main.java.org.ce.ap.server.TweetingService;

public class TweetingServiceImpl implements TweetingService {

    Database database;
    private int counter;


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
    public void like(Client client, Tweet tweet, Client mine) {
        for (Tweet t : database.getClientPage(client).getTweets()) {
            if (t.equals(tweet)) {
                for (String username : t.getLikes()) {
                    if (username.equals(mine.getUserName())) {
                        // before exit , now dislike
                        disLike(client, t, mine);
                        database.getClientPage(mine).addOrDislikeTweet(t);
                    }
                }

                t.Like(mine.getUserName());
                database.getClientPage(mine).addOrDislikeTweet(t);

                //Like++
                return;
            }
        }

    }


    private void disLike(Client client, Tweet tweet, Client mine) {
        for (Tweet t : database.getClientPage(client).getTweets()) {
            if (t.equals(tweet)) {
                t.dislike(mine.getUserName());
                return;
            }
        }


    }
}