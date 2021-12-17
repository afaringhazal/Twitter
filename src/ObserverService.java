import java.util.ArrayList;
import java.util.Iterator;

public class ObserverService {



    public void deleteFollower(Page follower, Client client){

        Server.getClientPage(client).deleteFollower(follower);

    }


    public void addFollowing(Page following, Client client){

        Server.getClientPage(client).addFollowing(following);
    }


    public void deleteFollowing(Page following, Client client){

        Server.getClientPage(client).deleteFollowing(following);

    }


    public ArrayList<Tweet> returnFollowingsTweets(Client client){
        return Server.getClientPage(client).getFollowingsTweets();
    }


    public static void notification(Client client, Tweet tweet){

        for(Page page : Server.getClientPage(client).getFollowers())
        {
            page.addFollowingsTweet(tweet);
        }
    }


    public static void DeletingTweetForOtherPeople(Client client , Tweet tweet){

        for (Page page :Server.getClientPage(client).getFollowers())
        {
            Iterator<Tweet> it = page.getTweets().iterator();
            while (it.hasNext())
            {
                if(it.equals(tweet))
                {
                    it.remove();
                    break;
                }
                it.next();
            }
        }


    }















}
