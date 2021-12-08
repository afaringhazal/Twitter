import java.util.ArrayList;
import java.util.Iterator;

public class ObserverService {



    public void deletefollowers(Page followers, Client client)
    {
        Server.getClientPage(client).deleteFollowers(followers);

    }

    public void addFollowing(Page following, Client client)
    {
        Server.getClientPage(client).addFollowing(following);
    }

    public void deleteFollowing(Page following, Client client)
    {
        Server.getClientPage(client).deleteFollowing(following);

    }


    //function

    public ArrayList<Tweet> returnAllTweets(Client client)
    {
//        ArrayList<Tweet>tweets= new ArrayList<>();
//
//        for(Page page : )

        return Server.getClientPage(client).getOtherTweet();

    }



    public static void notification(Client client, Tweet tweet)
    {
        for(Page page : Server.getClientPage(client).getFollowers())
        {
            page.setOtherTweet(tweet);
        }
    }

    public static void DeletingForOtherPeople(Client client , Tweet tweet)
    {
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
