package main.java.org.ce.ap.server;

//import org.json.JSONArray;

import java.util.ArrayList;

public class ObserverService {


    Database database;
    public ObserverService(Database database){
        this.database = database;
    }

    public boolean deleteFollower(String followerUserName, String userName){

       return database.getClientPageFromUsername(userName).deleteFollower(followerUserName);

    }


    public boolean follow(String UserNameFollowing, String userName){

       return database.getClientPageFromUsername(userName).addFollowing(UserNameFollowing);
    }


    public boolean unfollow(String followingUsername, String userName){

       return database.getClientPageFromUsername(userName).deleteFollowing(followingUsername);

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
