package main.java.org.ce.ap.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import main.java.org.ce.ap.Response;
import main.java.org.ce.ap.server.*;

import java.util.ArrayList;
import java.util.TreeMap;


public class ConsoleViewService {

    //  String jsonText = JSONValue.toJSONString(obj);

    public  void ShowPage(Page page){

    }



    public void printAllTweets(Response response) {
        for (Object obj : response.getResults()) {
            LinkedTreeMap<String,Object> treeMap = (LinkedTreeMap<String, Object>) obj;
            System.out.println("|" + treeMap.get("clientUsername") + "                         " + treeMap.get("date") );
            System.out.println("|" + treeMap.get("text") );
            System.out.println("|" + ((ArrayList<Object>) treeMap.get("retweets")).size() + " Retweets, " + ((ArrayList<String>) treeMap.get("likes")).size() + " Likes\n");
            printReply((ArrayList<Object>)treeMap.get("replies"), 0);
        }

        }




    private String toGsonString(Response response)
    {

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.toJson(response);





    }



    public void printReply(ArrayList<Object> repliers, int degree){
    degree+=1;

        for (Object msg : repliers){
            TreeMap<String,Object> treeMap1 = (TreeMap<String, Object>)msg;
           initLine(degree);
            System.out.println(treeMap1.get("clientUsername"));
            initLine(degree);
            System.out.println(treeMap1.get("text"));
            initLine(degree);
            System.out.println(((ArrayList<Object>) treeMap1.get("retweets")).size()+" Retweets, "+((ArrayList<String>) treeMap1.get("likes")).size()+ " Likes\n");
            printReply((ArrayList<Object>) treeMap1.get("replies"),degree);
        }

    }

    public void initLine(int degree){

        System.out.print("|");
        for(int i =0;i<4*degree;i++)
        {
            System.out.print("-");
        }
    }

    public void printList(Response response) {

        for (Object obj : response.getResults()) {
            System.out.println((String) obj);
        }
    }

    public void printFollowersAndFollowings(Response response) {

        ArrayList<Object> result = response.getResults();
        ArrayList<Object> followers = (ArrayList<Object>) result.get(0);
        ArrayList<Object> followings = (ArrayList<Object>) result.get(1);

        System.out.println("Followers : ");
        for(Object userNameFollower : followers )
        {
            System.out.println((String) userNameFollower);

        }
        System.out.println();
        System.out.println("Followings : ");
        for(Object userNameFollowing : followings)
        {
            System.out.println((String) userNameFollowing);
        }

    }


    public void printMyTweets(ArrayList<Object> repliers, int degree){
        degree += 1;

        for (Object msg : repliers){
            TreeMap<String,Object> treeMap1 = (TreeMap<String, Object>)msg;
            initLine(degree);
            System.out.println(treeMap1.get("clientUsername"));
            initLine(degree);
            System.out.println(treeMap1.get("text"));
            initLine(degree);
            System.out.println("Retweets  " + ((ArrayList<Object>) treeMap1.get("retweets")).size());
            for(int i=0 ;i < ((ArrayList<Object>) treeMap1.get("retweets")).size() ;i++)
            {
                initLine(degree);
                System.out.print("----");
                System.out.println(((ArrayList<Object>)treeMap1.get("retweets")).get(i));
            }
            System.out.println();

            System.out.println("Likes "+ ((ArrayList<String>) treeMap1.get("likes")).size());
            for(int i=0 ;i < ((ArrayList<Object>) treeMap1.get("likes")).size() ;i++)
            {
                initLine(degree);
                System.out.print("----");
                System.out.println(((ArrayList<Object>)treeMap1.get("likes")).get(i));
            }
            System.out.println();

            printReply((ArrayList<Object>) treeMap1.get("replies"),degree);
        }

    }


}
