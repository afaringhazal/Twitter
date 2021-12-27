package main.java.org.ce.ap.server;

public class TweetingService {


    Database database;
    ObserverService observerService;


    public TweetingService(Database database, ObserverService observerService){
        this.database = database;
        this.observerService=observerService;
    }


    public void addTweet(Tweet tweet) {

        for (Tweet t : database.getClientPage(tweet.getClient()).getTweets()) {
            if (t.equals(tweet)) {
                //observerService.DeletingForOtherPeople(tweet.getClient(), tweet);

                t.changeDate();
                //update date
                //better idea

                //database.getClientPage(client).getTweets().add(tweet);
              //  observerService.notification(tweet.getClient(), tweet);
                return;
            }
        }

        database.getClientPage(tweet.getClient()).getTweets().add(tweet);
        observerService.notification(tweet.getClient(), tweet);
    }

    public void deleteTweet(Tweet tweet) {
        database.getClientPage(tweet.getClient()).getTweets().remove(tweet);
        observerService.DeletingForOtherPeople(tweet.getClient(), tweet);
    }


    public void like(Client client, Tweet tweet, Client mine) {
        for (Tweet t : database.getClientPage(client).getTweets()) {
            if (t.equals(tweet)) {
                for (Client client1 : t.getLikes()) {
                    if (client1.equals(mine)) {
                        // before exit , now dislike
                        disLike(client, t, mine);
                        database.getClientPage(mine).addOrDislikeTweet(t);
                    }
                }

                t.Like(mine);
                database.getClientPage(mine).addOrDislikeTweet(t);

                //Like++
                return;
            }
        }

    }


    private void disLike(Client client, Tweet tweet, Client mine) {
        for (Tweet t : database.getClientPage(client).getTweets()) {
            if (t.equals(tweet)) {
                t.dislike(mine);
                return;
            }
        }


    }
}