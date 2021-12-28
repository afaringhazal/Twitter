package main.java.org.ce.ap.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.java.org.ce.ap.Request;
import main.java.org.ce.ap.Response;
import main.java.org.ce.ap.server.Message;
import main.java.org.ce.ap.server.Page;
import main.java.org.ce.ap.server.Reply;
import main.java.org.ce.ap.server.Tweet;

import java.util.ArrayList;




public class ConsoleViewService {

    //  String jsonText = JSONValue.toJSONString(obj);

    public  void ShowPage(Page page){
        //information ==> name , ... , bio,id , ..., id followers and following , ...


    }



    public void printAllTweets(Response response) {
        for (Object obj : response.getResults()) {
            Tweet tweet= (Tweet) obj;
            System.out.println("|" + tweet.getClient() + "                         " + tweet.getDate());
            System.out.println("|" + tweet.getText());
            System.out.println("|" + tweet.getRetweets().size() + " Retweets, " + tweet.getLikes().size() + " Likes");
            printReply(tweet, 0);

        }
    }
    public  JSONObject ShowAllTweetForOnePage(Page page) throws JSONException {
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

    public  JSONObject  ShowAllTweet(ArrayList<Tweet> tweets) throws JSONException {
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



    private String toGsonString(Response response)
    {

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.toJson(response);





    }

    public void printReply(Message message,int degree){
    degree+=1;

        for (Message msg:message.getReplies()){
           initLine(degree);
            System.out.println(((Reply)msg).getReplier());
            initLine(degree);
            System.out.println(msg.getText());
            initLine(degree);
            System.out.println(msg.getRetweets().size()+" Retweets, "+msg.getLikes().size()+ " Likes");
            printReply(msg,degree);
        }

    }

    public void initLine(int degree){

        System.out.print("|");
        for(int i =0;i<4*degree;i++)
        {
            System.out.print("-");
        }
    }





}
