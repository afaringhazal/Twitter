import java.util.ArrayList;
import java.util.Iterator;

public class ObserverService {



    public void deletefollowers(Page followers, Client client)
    {
        Server.getBasic(client).deleteFollowers(followers);

    }

    public void addFollowing(Page following, Client client)
    {
        Server.getBasic(client).addFollowing(following);
    }

    public void deleteFollowing(Page following, Client client)
    {
        Server.getBasic(client).deleteFollowing(following);

    }


    //function

    public ArrayList<Tweet> returnAllTweets(Client client)
    {
//        ArrayList<Tweet>tweets= new ArrayList<>();
//
//        for(Page page : )

        return Server.getBasic(client).getOtherTweet();

    }



    public static void notification(Client client, Tweet tweet)
    {
        for(Page page : Server.getBasic(client).getFollowers())
        {
            page.setOtherTweet(tweet);
        }
    }

    public static void DeletingForOtherPeople(Client client , Tweet tweet)
    {
        for (Page page :Server.getBasic(client).getFollowers())
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
