package main.java.org.ce.ap.server;

public class TweetingService {


    Database database;
   // ObserverService observerService;
    private int counter;


    public TweetingService(Database database){
        this.database = database;
        //this.observerService=observerService;
        counter=1;
    }


    public boolean addTweet(Tweet tweet) {
        tweet.id=counter;
        counter++;
        return database.getClientPageFromUsername(tweet.clientUsername).addTweet(tweet);

    }

    public void deleteTweet(Tweet tweet) {
        database.getClientPageFromUsername(tweet.getClient()).getTweets().remove(tweet);
       // observerService.DeletingForOtherPeople(database.getClientPageFromUsername(tweet.clientUsername).getClient(), tweet);
    }


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