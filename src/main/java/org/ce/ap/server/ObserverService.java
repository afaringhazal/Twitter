package main.java.org.ce.ap.server;

//import org.json.JSONArray;

import java.util.ArrayList;

public class ObserverService {


    Database database;
    public ObserverService(Database database){
        this.database = database;
    }

    public boolean deleteFollower(String followerUserName, String userName){

        for(String availableUserName : database.getClientPageFromUsername(userName).getFollowers()){
            if(availableUserName.equals(followerUserName))
            {
                //There is a username to remove
                return database.getClientPageFromUsername(userName).deleteFollower(followerUserName)
                        && database.getClientPageFromUsername(followerUserName).deleteFollowing(userName);

            }
        }
        return false;

    }


    public boolean follow(String UserNameFollowing, String userName){
        if(UserNameFollowing.equals(userName))
        {
            //the object can not follow itself
            return false;
        }



        for(String availableUserName : database.getUserNames())
        {
            if(availableUserName.equals(UserNameFollowing))
            {

                for(String reFollow : database.getClientPageFromUsername(userName).getFollowingsList())
                {
                    if(reFollow.equals(UserNameFollowing))
                    {
                        //exit before
                        return false;
                    }
                }


                return  database.getClientPageFromUsername(userName).addFollowing(UserNameFollowing)
                        && database.getClientPageFromUsername(UserNameFollowing).addFollower(userName);

            }
        }
        return false;

    }


    public boolean unfollow(String followingUsername, String userName){
        
        for (String availableUserName : database.getClientPageFromUsername(userName).getFollowingsList())
        {
            if(availableUserName.equals(followingUsername))
            {
                return database.getClientPageFromUsername(userName).deleteFollowing(followingUsername)
                        && database.getClientPageFromUsername(followingUsername).deleteFollower(userName);

            }
        }
        return false;

    }
    public ArrayList<String> getFollowers(String userName) {

        return database.getClientPageFromUsername(userName).getFollowers();
    }
    public ArrayList<String> getFollowings(String userName)
    {
        return database.getClientPageFromUsername(userName).getFollowingsList();
    }

}
