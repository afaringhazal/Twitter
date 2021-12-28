package main.java.org.ce.ap.server;

import com.google.gson.Gson;
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
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable {

    private Socket socket;
    private boolean clientIsConnected;

    private AuthenticationService authenticationService;
    private TweetingService tweetingService;
    private  ObserverService observerService;

    private TimelineService timelineService;

    Response response = new Response(false,0,0,null);
    Request request ;
    ObjectInputStream objectInputStream ;
    ObjectOutputStream objectOutputStream;
    String message="";
    Page page = null;

    Gson gson = new Gson();

    public ClientHandler(Socket socket, AuthenticationService authenticationService, TweetingService tweetingService,ObserverService observerService,TimelineService timelineService) {
        this.socket = socket;
        this.clientIsConnected = true;


        this.authenticationService = authenticationService;
        this.tweetingService = tweetingService;
        this.observerService =observerService;
        this.timelineService =timelineService;

    }

    @Override
    public void run() {

        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            while (clientIsConnected) {
                response = new Response(false,0,0,null);

                message = (String) objectInputStream.readObject();
               request = gson.fromJson(message,Request.class);

                 if(request.getTitle().equals("Sign up")) {
                     processSignUp();
                 }
               else if(request.getTitle().equals("Sign In")) {
                   processSignIn();

               }
               else if(request.getTitle().equals("Get Timeline")) {
                   processTimeLine();

               }
               else if(request.getTitle().equals("Show Followers"))
                 {

                     ShowAllFollowers();



                 }









                //finish tweeting service







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
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }





    private void processSignUp() throws IOException, NoSuchAlgorithmException, ClassNotFoundException {
        PersonalInformation personalInformation = authenticationService.signUpRequest((String) ((ParameterValue) request.getParameterValue().get(0)).getValue()
                , (String)((ParameterValue) request.getParameterValue().get(1)).getValue());

        if (personalInformation == null) {
            response.setHasError(true);
            response.setErrorCode(20);
        }


        response.setCount(1);

        ArrayList<Object> result = new ArrayList<>();
        ParameterValue p = new ParameterValue("Result", "Connected");
        result.add(p);

        response.setResults(result);

        objectOutputStream.writeObject(gson.toJson(response));



        message = (String) objectInputStream.readObject();
        request = gson.fromJson(message,Request.class);
        int year=Integer.parseInt((String)((ParameterValue)request.getParameterValue().get(2)).getValue());
        int month=Integer.parseInt((String)((ParameterValue)request.getParameterValue().get(3)).getValue());
        int day=Integer.parseInt((String)((ParameterValue)request.getParameterValue().get(4)).getValue());
        LocalDate localDate =LocalDate.of(year,month,day);

        Client client = new Client((String)((ParameterValue) request.getParameterValue().get(0)).getValue(),(String)((ParameterValue) request.getParameterValue().get(1)).getValue(),
         localDate, personalInformation);

      page =  authenticationService.signUp(client,(String)((ParameterValue)request.getParameterValue().get(5)).getValue(),(String)((ParameterValue)request.getParameterValue().get(6)).getValue())
        response.setCount(1);

         result = new ArrayList<>();
         p = new ParameterValue("Result", "Connected");
        result.add(p);

        response.setResults(result);
        objectOutputStream.writeObject(gson.toJson(response));



    }





    private void processSignIn() throws NoSuchAlgorithmException, IOException {


        page = authenticationService.signInRequest((String) ((ParameterValue) request.getParameterValue().get(0)).getValue(),
                (String) ((ParameterValue) request.getParameterValue().get(1)).getValue());
        if (page == null) {
            response.setHasError(true);
            response.setErrorCode(15);

        }


        response.setCount(1);

        ArrayList<Object> result = new ArrayList<>();
        ParameterValue p = new ParameterValue("Result", "Connect!");
        result.add(p);

        response.setResults(result);
        objectOutputStream.writeObject(gson.toJson(response));
    }


    public ArrayList<Object> processTweet(Tweet tweet){

        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(tweet);
        return parameters;



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

        ArrayList<Object> result = new ArrayList<>();
        result.addAll(timelineService.gatherTimeline(page.getClient().getUserName()));

        response.setResults(result);

        objectOutputStream.writeObject(gson.toJson(response));


    }



    public void ShowAllFollowers() throws IOException {  ArrayList<Object> UserNameFollower = null;
        try{
            UserNameFollower.addAll(observerService.getFollowers(page.getClient().getUserName()));

        }catch (Exception e)
        {
            response.setHasError(true);
            response.setErrorCode(12);
        }

        response.setResults(UserNameFollower);
        objectOutputStream.writeObject(gson.toJson(response));
        
    }


}


