package main.java.org.ce.ap.client;

import main.java.org.ce.ap.server.Page;
import main.java.org.ce.ap.server.Tweet;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ConsoleViewService {
    //  String jsonText = JSONValue.toJSONString(obj);

    public static void ShowPage(Page page){
        //information ==> name , ... , bio,id , ..., id followers and following , ...


    }

    public static void ShowTweet(Tweet tweet)
    {

        //"content": "Hello, World!",
        //"username": "RichardHendricks",
        //"creationDate": "11/24/2021 2:39 PM",
        //"likes": [],
        //"retweets": [],
        //"replies": []

    }
    public static JSONObject ShowAllTweetForOnePage(Page page) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        int num =1;
        for (Tweet tweet :page.getTweets())
        {
            System.out.println(num+"- ");
            ShowTweet(tweet);
            jsonObject.put(String.valueOf(num),tweet);
            num++;
        }

        return jsonObject;
    }

    public static JSONObject  ShowAllTweet(ArrayList<Tweet> tweets) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        int number=1;
        for(Tweet tweet:tweets)
        {
            System.out.print(number+"- ");
            ShowTweet(tweet);
            jsonObject.put(String.valueOf(number),tweet);
            number++;
        }
        jsonObject.put("Count",number--);

        return jsonObject;
    }







}
