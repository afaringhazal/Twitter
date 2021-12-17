public class TweetingService {



    public void addTweet(Tweet tweet, Client client) {
        for (Tweet t : Server.getClientPage(client).getTweets()) {
            if (t.equals(tweet)) {
                ObserverService.DeletingTweetForFollowings(client, tweet);
                //update date
                //better idea
                ObserverService.notification(client, tweet);
                return;
            }
        }

        Server.getClientPage(client).getTweets().add(tweet);
        ObserverService.notification(client, tweet);
    }

    public void deleteTweet(Tweet tweet, Client client) {
        Server.getClientPage(client).getTweets().remove(tweet);
        ObserverService.DeletingTweetForFollowings(client, tweet);
    }


    public void like(Client client, Tweet tweet, Client mine) {
        for (Tweet t : Server.getClientPage(client).getTweets()) {
            if (t.equals(tweet)) {
                for (Client client1 : t.getSaveLiked()) {
                    if (client1.equals(mine)) {
                        // before exit
                        disLike(client, tweet, mine);
                    }
                }

                t.addSaveLiked(mine);
                //Like++
                return;
            }
        }

    }


    public void disLike(Client client, Tweet tweet, Client mine) {
        for (Tweet t : Server.getClientPage(client).getTweets()) {
            if (t.equals(tweet)) {
                t.deleteSaveLike(mine);
                //Like--
                return;
            }
        }


    }
}