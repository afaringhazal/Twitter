import java.util.ArrayList;
import java.util.HashMap;

public class TweetingService extends Server{

//
//    public TweetingService(ArrayList<Client> clients, HashMap<Client, Page> ll) {
//        super(clients, ll);
//    }

    public void addTweet(Tweet tweet , Client client)
    {
        super.getBasic(client).getTweets().add(tweet);
    }

    public void deleteTweet(Tweet tweet,Client client)
    {
        super.getBasic(client).getTweets().remove(tweet);
    }


    public void retweet(Client client , Tweet tweet)
    {

        for(Tweet t:super.getBasic(client).getTweets())
        {
            if(t.equals(tweet))
            {
                //update date
                return;
            }
        }

        super.getBasic(client).getTweets().add(tweet);
    }


    public void like(Client client , Tweet tweet)
    {
        for(Tweet t:super.getBasic(client).getTweets())
        {
            if(t.equals(tweet))
            {
                //Like++
                return;
            }
        }

        // if don't have => exception

    }

    public void disLike(Client client , Tweet tweet)
    {
        for(Tweet t:super.getBasic(client).getTweets())
        {
            if(t.equals(tweet))
            {
                //Like--
                return;
            }
        }

        // if don't have => exception

    }














}
