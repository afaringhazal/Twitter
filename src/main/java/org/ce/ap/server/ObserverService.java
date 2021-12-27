package main.java.org.ce.ap.server;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Iterator;

public class ObserverService {


    Database database;
    public ObserverService(Database database){
        this.database = database;
    }

    public void deleteFollower(Page follower, Client client){

        database.getClientPage(client).deleteFollower(follower);

    }


    public void addFollowing(Page following, Client client){

        database.getClientPage(client).addFollowing(following);
    }


    public void deleteFollowing(Page following, Client client){

        database.getClientPage(client).deleteFollowing(following);

    }


    public ArrayList<Tweet> returnFollowingsTweets(Client client){
        return database.getClientPage(client).getFollowingsTweets();
    }


    public void notification(Client client, Tweet tweet){

        for(Page page : database.getClientPage(client).getFollowers())
        {
            page.addFollowingsTweet(tweet);
        }
//        JSONArray jsonArray=new JSONArray();

    }


    public void DeletingForOtherPeople(Client client , Tweet tweet){

        for (Page page : database.getClientPage(client).getFollowers())
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
