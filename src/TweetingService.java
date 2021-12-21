public class TweetingService {


    Database database;
    ObserverService observerService;


    public TweetingService(Database database, ObserverService observerService){
        this.database = database;
        this.observerService=observerService;
    }
    public void addTweet(Tweet tweet, Client client) {

        for (Tweet t : database.getClientPage(client).getTweets()) {
            if (t.equals(tweet)) {
                observerService.DeletingForOtherPeople(client, tweet);
                //update date
                //better idea
                observerService.notification(client, tweet);
                return;
            }
        }

        database.getClientPage(client).getTweets().add(tweet);
        observerService.notification(client, tweet);
    }

    public void deleteTweet(Tweet tweet, Client client) {
        database.getClientPage(client).getTweets().remove(tweet);
        observerService.DeletingForOtherPeople(client, tweet);
    }


    public void like(Client client, Tweet tweet, Client mine) {
        for (Tweet t : database.getClientPage(client).getTweets()) {
            if (t.equals(tweet)) {
                for (Client client1 : t.getLikes()) {
                    if (client1.equals(mine)) {
                        // before exit
                        disLike(client, tweet, mine);
                    }
                }

                t.Like(mine);
                //Like++
                return;
            }
        }

    }


    public void disLike(Client client, Tweet tweet, Client mine) {
        for (Tweet t : database.getClientPage(client).getTweets()) {
            if (t.equals(tweet)) {
                t.dislike(mine);
                //Like--
                return;
            }
        }


    }
}