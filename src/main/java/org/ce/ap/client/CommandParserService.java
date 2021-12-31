package main.java.org.ce.ap.client;

import com.google.gson.Gson;
//import main.java.org.ce.ap.ParameterValue;
import com.google.gson.GsonBuilder;
import main.java.org.ce.ap.Request;
import main.java.org.ce.ap.Response;
import main.java.org.ce.ap.server.Server;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public interface CommandParserService {

    public  void showMainMenu() throws IOException, ClassNotFoundException;
    public void refreshRequest();


    public void processSignIn() throws IOException, ClassNotFoundException ;


    public void processSignUp() throws IOException, ClassNotFoundException ;


    public void addTweet() ;

    public void requestTimeline();

    public void showPageMenu() throws IOException, ClassNotFoundException ;

    public void follow() throws IOException, ClassNotFoundException ;

    public void unfollow();

    public void deleteFollower();

    public void fixGson();

    public void showFollowersAndFollowing() throws IOException;

    public void  followersAndFollowingsMenu() throws IOException, ClassNotFoundException ;

    public void myTweetAndReplies();

    public void myFavoriteTweets();

    public void  DisplayPageInformation();

    public void editProfile();



}





