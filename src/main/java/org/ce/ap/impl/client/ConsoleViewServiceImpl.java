package main.java.org.ce.ap.impl.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import main.java.org.ce.ap.Response;
import main.java.org.ce.ap.client.ConsoleViewService;
import main.java.org.ce.ap.server.Page;

import java.util.ArrayList;
import java.util.TreeMap;

public class ConsoleViewServiceImpl implements ConsoleViewService {


    @Override
    public void printAllTweets(Response response) {
        for (Object obj : response.getResults()) {
            LinkedTreeMap<String, Object> treeMap = (LinkedTreeMap<String, Object>) obj;
            System.out.println("|" + treeMap.get("clientUsername") + "                         " + treeMap.get("date"));
            System.out.println("|" + treeMap.get("text"));
            System.out.println("|" + ((ArrayList<Object>) treeMap.get("retweets")).size() + " Retweets, " + ((ArrayList<String>) treeMap.get("likes")).size() + " Likes\n");
            printReply((ArrayList<Object>) treeMap.get("replies"), 0);
        }

    }


    @Override
    public String toGsonString(Response response) {

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.toJson(response);


    }


    @Override
    public void printReply(ArrayList<Object> repliers, int degree) {
        degree += 1;

        for (Object msg : repliers) {
            TreeMap<String, Object> treeMap1 = (TreeMap<String, Object>) msg;
            initLine(degree);
            System.out.println(treeMap1.get("clientUsername"));
            initLine(degree);
            System.out.println(treeMap1.get("text"));
            initLine(degree);
            System.out.println(((ArrayList<Object>) treeMap1.get("retweets")).size() + " Retweets, " + ((ArrayList<String>) treeMap1.get("likes")).size() + " Likes\n");
            printReply((ArrayList<Object>) treeMap1.get("replies"), degree);
        }

    }

    @Override
    public void initLine(int degree) {
        for (int i = 0; i < 4 * degree; i++) {
            System.out.print("-");
        }
        System.out.print("|");
    }

    @Override
    public void printList(Response response) {

        for (Object obj : response.getResults()) {
            System.out.println((String) obj);
        }
    }

    @Override
    public void printFollowersAndFollowings(Response response) {

        ArrayList<Object> result = response.getResults();
        ArrayList<Object> followers = (ArrayList<Object>) result.get(0);
        ArrayList<Object> followings = (ArrayList<Object>) result.get(1);

        System.out.println("Followers : ");
        for (Object userNameFollower : followers) {
            System.out.println((String) userNameFollower);

        }
        System.out.println();
        System.out.println("Followings : ");
        for (Object userNameFollowing : followings) {
            System.out.println((String) userNameFollowing);
        }

    }

    @Override
    public void printMyTweets(ArrayList<Object> repliers, int degree) {
        for (Object msg : repliers) {
            LinkedTreeMap<String, Object> treeMap1 = (LinkedTreeMap<String, Object>) msg;
            initLine(degree);
            System.out.println(treeMap1.get("clientUsername")+ "                         " + treeMap1.get("date"));
            initLine(degree);
            System.out.println(treeMap1.get("text"));
            initLine(degree);
            System.out.println("Retweets  " + ((ArrayList<Object>) treeMap1.get("retweets")).size());
            for (int i = 0; i < ((ArrayList<Object>) treeMap1.get("retweets")).size(); i++) {
                initLine(degree+1);
                System.out.println(((ArrayList<Object>) treeMap1.get("retweets")).get(i));
            }
            System.out.println();
            initLine(degree);
            System.out.println("Likes " + ((ArrayList<String>) treeMap1.get("likes")).size());
            for (int i = 0; i < ((ArrayList<Object>) treeMap1.get("likes")).size(); i++) {
                initLine(degree+1);
                System.out.println(((ArrayList<Object>) treeMap1.get("likes")).get(i));
            }
            System.out.println();
            initLine(degree);
            System.out.println("Replies " + ((ArrayList<String>) treeMap1.get("replies")).size());
            printReply((ArrayList<Object>) treeMap1.get("replies"), degree);
        }

    }


}
