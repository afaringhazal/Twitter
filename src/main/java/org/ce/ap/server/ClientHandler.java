package main.java.org.ce.ap.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.java.org.ce.ap.ParameterValue;
import main.java.org.ce.ap.Request;
import main.java.org.ce.ap.Response;
//import org.json.JSONException;
//import org.json.JSONObject;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {

    private Socket socket;
    private boolean clientIsConnected;

    private AuthenticationService authenticationService;
    private TweetingService tweetingService;
    private ObserverService observerService;

    private TimelineService timelineService;

    Response response = new Response(false, 0, 0, null);
    Request request;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    String message = "";
    Page page = null;
    public Logger logger;

    Gson gson;

    public ClientHandler(Socket socket, AuthenticationService authenticationService, TweetingService tweetingService, ObserverService observerService, TimelineService timelineService) {
        fixGson();
        this.socket = socket;
        this.clientIsConnected = true;
        this.authenticationService = authenticationService;
        this.tweetingService = tweetingService;
        this.observerService = observerService;
        this.timelineService = timelineService;

    }

    @Override
    public void run() {

        System.out.println("********");
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            while (clientIsConnected) {
                refreshResponse();
                message = (String) objectInputStream.readObject();
                System.out.println("request received");

                request = gson.fromJson(message, Request.class);
                System.out.println(request.getTitle() + "  " + request.getParameterValue());

                if (request.getTitle().equals("Sign Up")) {
                    System.out.println("requested sign up");
                    processSignUp();

                } else if (request.getTitle().equals("Sign In")) {
                    System.out.println("Sign in requested");
                    processSignIn();

                } else if (request.getTitle().equals("Get Timeline")) {
                    processTimeLine();

                } else if (request.getTitle().equals("Show Followers")) {
                    ShowAllFollowers();

                } else if (request.getTitle().equals("Delete Follower")) {
                    requestDeleteFollower();
                }else if(request.getTitle().equals("unfollow"))
                {
                    requestUnfollow();
                } else if (request.getTitle().equals("Add Tweet")) {
                    processTweet();
                }
                else if(request.getTitle().equals("Show AllUsernames"))
                {
                    ShowAllUsernames();
                }
                else if(request.getTitle().equals("follow"))
                {
                    requestFollow();
                }
                else if(request.getTitle().equals("showFollowersAndFollowings"))
                {
                    showFollowersAndFollowings();
                }
                else if(request.getTitle().equals("myTweetAndReplies")){
                    sendMyTweetAndReplies();
                }
                else if(request.getTitle().equals("myFavoriteTweets"))
                {
                    myFavoriteTweets();
                }
                else if(request.getTitle().equals("Display Page Information")){
                    DisplayPageInformation();
                }




            }
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
        } catch (EOFException e) {
            clientIsConnected = false;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }

    private void sendMyTweetAndReplies() {



    }


    private void processSignUp() throws IOException, NoSuchAlgorithmException, ClassNotFoundException {

        String username= (String) request.getParameterValue().get(0);
        String password= (String) request.getParameterValue().get(1);
        System.out.println(username+"  "+ password);
        PersonalInformation personalInformation = authenticationService.signUpRequest(username,password);

        if (personalInformation == null) {
            response.setHasError(true);
            response.setErrorCode(20);
            System.out.println("signup user is already used.");
        }
        System.out.println("sign up user/pass is allowed.");

        response.setCount(1);

        ArrayList<Object> result = new ArrayList<>();
        ParameterValue p = new ParameterValue("Result", "Connected");
        result.add(p);

        response.setResults(result);

        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();


        message = (String) objectInputStream.readObject();
        System.out.println("received further request.");
        request = gson.fromJson(message, Request.class);
        System.out.println(request.getParameterValue().get(2));
        System.out.println(request.getParameterValue().get(3));
        System.out.println(request.getParameterValue().get(4));
        int year = Integer.parseInt((String)request.getParameterValue().get(2));
        System.out.println("year " +year);

        int month = Integer.parseInt((String)request.getParameterValue().get(3));
        int day = Integer.parseInt((String)request.getParameterValue().get(4));
        LocalDate localDate = LocalDate.of(year, month, day);
        System.out.println("2222222");
        System.out.println(localDate);

        Client client = new Client((String) request.getParameterValue().get(0), (String)request.getParameterValue().get(1),
                localDate, personalInformation);

        page = authenticationService.signUp(client, (String)request.getParameterValue().get(5),(String) request.getParameterValue().get(6));
        response.setCount(1);
        System.out.println("Authentication completed.");
        result = new ArrayList<>();
        p = new ParameterValue("Result", "Connected");
        result.add(p);

        response.setResults(result);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();
        System.out.println("signUp successful.");
    }


    private void processSignIn() throws NoSuchAlgorithmException, IOException {


        page = authenticationService.signInRequest((String) (request.getParameterValue().get(0)),
                (String) ( request.getParameterValue().get(1)));
        if (page == null) {
            System.out.println("+");
            response.setHasError(true);
            response.setErrorCode(15);

        }
        System.out.println("no problem");

        response.setCount(1);

        ArrayList<Object> result = new ArrayList<>();
        ParameterValue p = new ParameterValue("Result", "Connect!");
        result.add(p);

        response.setResults(result);
        objectOutputStream.writeObject(gson.toJson(response));
        System.out.println("sent response");
        refreshResponse();

    }


    public void processTweet() throws IOException {

        String text= (String) (request.getParameterValue().get(0));
        if (!tweetingService.addTweet(new Tweet(page.getClient().getUserName(),text))) {
            response.setHasError(true);
            response.setErrorCode(23);
        }
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();
        //
//        parameters.add(new ParameterValue("Text", tweet.text));
//        parameters.add(new ParameterValue("UserName", page.getClient().getUserName()));
//        ArrayList<ParameterValue> retweets=new ArrayList<>();
//        for (String clientUsername : tweet.getRetweets()) {
//            retweets.add(new ParameterValue("retweeter", clientUsername));
//
//        }
//        parameters.add(new ParameterValue("Retweets",retweets));
//
//        ArrayList<String> tweetLikes =new ArrayList<>() ;
//        for (String string: tweet.getLikes()) {
//            retweets.add(new ParameterValue("liker", string));
//
//        }
//        parameters.add(new ParameterValue("likes",tweetLikes));
//
//        ArrayList<String> tweetReplies =new ArrayList<>() ;
//        for (Message msg:tweet.getReplies() ) {
//
//            retweets.add(new ParameterValue(((Reply) msg).getReplier(),((Reply) msg).getText()));
//
//        }
//        parameters.add(new ParameterValue("Replies",tweetReplies));
    }

    public void processTimeLine() throws IOException {

        System.out.println((page.getClient().getUserName()));
        ArrayList<Object> result = new ArrayList<>();
        result.addAll(timelineService.gatherTimeline(page.getClient().getUserName()));
        System.out.println(result);
        response.setResults(result);
        System.out.println(gson.toJson(response));
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();


    }

    public void ShowAllFollowers() throws IOException {
        ///////
        ArrayList<Object> UserNameFollower = null;
        try {
            UserNameFollower.addAll(observerService.getFollowers(page.getClient().getUserName()));

        } catch (Exception e) {
            response.setHasError(true);
            response.setErrorCode(12);
        }

        response.setResults(UserNameFollower);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();

    }

    public void requestDeleteFollower() throws IOException {

        String userName = (String) request.getParameterValue().get(0);
        if (!observerService.deleteFollower(userName, page.getClient().getUserName())) {

            response.setHasError(true);
            response.setErrorCode(22);

        }
        response.setCount(1);
        response.setResults(null);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();
    }

    public void requestUnfollow() throws IOException {

        String userName = (String) request.getParameterValue().get(0);
        if (!observerService.unfollow(userName, page.getClient().getUserName())) {

            response.setHasError(true);
            response.setErrorCode(23);

        }
        response.setCount(1);
        response.setResults(null);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();
    }

    public void requestFollow() throws IOException {
        ArrayList<Object> follow = request.getParameterValue();
        for(Object obj : follow)
        {
            if(!observerService.follow((String) obj,page.getClient().getUserName())) {
                response.setHasError(true);
                response.setErrorCode(22);
            }
        }

        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();

    }

    public void refreshResponse(){response = new Response(false, 0, 0, null);}

    public void fixGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new Server.LocalDateSerializer());

        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new Server.LocalDateTimeSerializer());

        gsonBuilder.registerTypeAdapter(LocalDate.class, new Server.LocalDateDeserializer());

        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new Server.LocalDateTimeDeserializer());

        gson = gsonBuilder.setPrettyPrinting().create();

    }

    public void ShowAllUsernames() throws IOException {

        ArrayList<Object> x = new ArrayList<>();
        x.addAll(authenticationService.AllUserNames());
        response.setResults(x);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();
    }

    public void showFollowersAndFollowings() throws IOException {

        ArrayList<Object> UserNameFollowers = new ArrayList<>();
        ArrayList<Object> UserNameFollowings= new ArrayList<>();
        System.out.println("Show in observerService (section follower)  : ");

        System.out.println(observerService.getFollowers(page.getClient().getUserName()));
        try {
            System.out.println("In try");

            UserNameFollowers.addAll(observerService.getFollowers(page.getClient().getUserName()));
            System.out.println("can add");
            UserNameFollowings.addAll(observerService.getFollowings(page.getClient().getUserName()));

        } catch (Exception e) {
            response.setHasError(true);
            response.setErrorCode(12);
        }

        ArrayList<Object> result = new ArrayList<>();
        result.add(UserNameFollowers);
        result.add(UserNameFollowings);
        System.out.println("UserName followers:");
        System.out.println(UserNameFollowers);
        System.out.println("UserNameFollowings :");
        System.out.println(UserNameFollowings);

        response.setResults(result);


        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();

    }

    public void sentMyTweetAndReplies() throws IOException {

        ArrayList<Object> result = new ArrayList<>();
        result.addAll(page.getTweets());
        response.setResults(result);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();


    }

    public void myFavoriteTweets() throws IOException {
        ArrayList<Object> result = new ArrayList<>();
        result.addAll(page.getLikedTweetsList());
        response.setResults(result);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();

    }

    public void DisplayPageInformation() throws IOException {

        ArrayList<Object> result = new ArrayList<>();
        result.add("UserName : "+page.getClient().getUserName()); //usrName
        result.add("FirstName : "+page.getClient().getFirstName());//firstName
        result.add("LastName : "+page.getClient().getLastName());//lastName
        result.add("Birthday : ");
        result.add(page.getClient().getBirthday());//birthday
        result.add("Bio : "+page.getBiography()); //bio
        result.add("Id : "+page.getId());//id
        result.add("JoinDate : ");
        result.add(page.getJoinDate()); //joinDate


        response.setResults(result);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();




    }

}



