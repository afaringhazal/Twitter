package main.java.org.ce.ap.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import main.java.org.ce.ap.Response;
import main.java.org.ce.ap.server.*;

import java.util.ArrayList;
import java.util.TreeMap;


public interface ConsoleViewService {

    //  String jsonText = JSONValue.toJSONString(obj);


    public void printAllTweets(Response response) ;




    public String toGsonString(Response response);



    public void printReply(ArrayList<Object> repliers, int degree);

    public void initLine(int degree);

    public void printList(Response response) ;

    public void printFollowersAndFollowings(Response response) ;


    public void printMyTweets(ArrayList<Object> repliers, int degree);


}
