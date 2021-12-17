public class TweetingService {


    /** adds a tweet to the server
     * @param tweet the tweet to be added to server
     */
    public void addTweet(Tweet tweet) {
        for (Tweet t : Server.getClientPage(tweet.client).getTweets()) {
            if (t.equals(tweet)) {
                ObserverService.DeleteTweetForFollowings( tweet);
                //update date
                //better idea
                ObserverService.notification(tweet);
                return;
            }
        }

        Server.getClientPage(tweet.client).getTweets().add(tweet);
        ObserverService.notification(tweet);
    }

    /** deletes a tweet from the server
     * @param tweet the tweet to be deleted from server
     */
    public void deleteTweet(Tweet tweet) {
        Server.getClientPage(tweet.client).getTweets().remove(tweet);
        ObserverService.DeleteTweetForFollowings( tweet);
    }


    /** likes a tweet for a client
     * @param tweet the tweet to be liked
     * @param liker the client who liked the tweet
     */
    public void like(Tweet tweet, Client liker) {
        for (Tweet t : Server.getClientPage(tweet.client).getTweets()) {
            if (t.equals(tweet)) {
                for (Client client1 : t.getPeopleWhoLikedThisMessage()) {
                    if (client1.equals(liker)) {
                        disLike(tweet, liker);
                    }
                }
                t.addLike(liker);
                return;
            }
        }

    }


    /** dislikes a tweet for a client
     * @param tweet the tweet to be disliked
     * @param disliker the client who disliked the tweet
     */
    public void disLike( Tweet tweet, Client disliker) {
        for (Tweet t : Server.getClientPage(tweet.client).getTweets()) {
            if (t.equals(tweet)) {
                t.deleteLike(disliker);
                return;
            }
        }


    }
}