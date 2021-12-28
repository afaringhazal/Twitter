package main.java.org.ce.ap.server;

//import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Iterator;

public class ObserverService {


    Database database;
    public ObserverService(Database database){
        this.database = database;
    }

    public void deleteFollower(String UserNameFollower, String userName){

        database.getClientPageFromUsername(userName).deleteFollower(database.getClientPageFromUsername(UserNameFollower));

    }


    public void addFollowing(String UserNameFollowing, String userName){

        database.getClientPageFromUsername(userName).addFollowing(UserNameFollowing);
    }


    public void deleteFollowing(String UserNameFollowing, String userName){

        database.getClientPageFromUsername(userName).deleteFollowing(database.getClientPageFromUsername(UserNameFollowing));

    }
    public ArrayList<String> getFollowers(String userName)
    {
        return database.getClientPageFromUsername(userName).getFollowers();
    }





//    public void notification(Client client, Tweet tweet){
//
//        for(Page page : database.getClientPage(client).getFollowers())
//        {
//            page.addFollowingsTweet(tweet);
//        }
//
//    }
//
//
//    public void DeletingForOtherPeople(Client client , Tweet tweet){
//
//        for (Page page : database.getClientPage(client).getFollowers())
//        {
//            Iterator<Tweet> it = page.getTweets().iterator();
//            while (it.hasNext())
//            {
//                if(it.equals(tweet))
//                {
//                    it.remove();
//                    break;
//                }
//                it.next();
//            }
//        }
//
//
//    }















}
