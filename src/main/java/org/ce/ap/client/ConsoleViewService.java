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
        //information ==> name , ... , bio,id , ..., id followers and following , ...


    }



    public void printAllTweets(Response response) {
        for (Object obj : response.getResults()) {
            LinkedTreeMap<String,Object> treeMap = (LinkedTreeMap<String, Object>) obj;
            System.out.println("|" + treeMap.get("clientUsername") + "                         " + treeMap.get("date") );
            System.out.println("|" + treeMap.get("text") );
            System.out.println("|" + ((ArrayList<Object>) treeMap.get("retweets")).size() + " Retweets, " + ((ArrayList<String>) treeMap.get("likes")).size() + " Likes");
            printReply((ArrayList<Object>)treeMap.get("replies"), 0);
        }
//            }
//            else if(obj instanceof Retweet){
//                Retweet retweet =(Retweet) obj;
//                System.out.println("|" + retweet.getRetweetClient() +" (Retweeted)"+ "                         " + retweet.getDate());
//                if(retweet.getText()!=null)
//                {
//                    System.out.println("|"+retweet.getText());
//                }
//                System.out.println("--|"+retweet.getTweet().getClient()+"                         "+retweet.getTweet().getDate());
//                System.out.println("--|" + retweet.getTweet().getText());
//                System.out.print("--|"+retweet.getTweet().getRetweets().size()+" Retweets, "+retweet.getTweet().getLikes().size()+ " Likes");
//                System.out.println("|" + retweet.getRetweets().size() + " Retweets, " + retweet.getLikes().size() + " Likes");
//                printReply(retweet, 0);
//
//
//
//            }
//            else {
//                System.out.println("problem opening response results."); }
//
//        }
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
            System.out.println(((ArrayList<Object>) treeMap1.get("retweets")).size()+" Retweets, "+((ArrayList<String>) treeMap1.get("likes")).size()+ " Likes");
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





}
