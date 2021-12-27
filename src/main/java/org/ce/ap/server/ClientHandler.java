package main.java.org.ce.ap.server;

import com.google.gson.Gson;
import main.java.org.ce.ap.ParameterValue;
import main.java.org.ce.ap.Request;
import main.java.org.ce.ap.Response;
import main.java.org.ce.ap.client.Client;
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
    private Database database;
    private TimelineService timelineService;

    Response response = new Response(false,0,0,null);
    Request request ;
    ObjectInputStream objectInputStream ;
    ObjectOutputStream objectOutputStream;
    String message="";
    Page page = null;

    Gson gson = new Gson();

    public ClientHandler(Socket socket, AuthenticationService authenticationService, TweetingService tweetingService) {
        this.socket = socket;
        this.clientIsConnected = true;


        this.authenticationService = authenticationService;
        this.tweetingService = tweetingService;

    }

    @Override
    public void run() {

        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            while (clientIsConnected) {

                message = (String) objectInputStream.readObject();
               request = gson.fromJson(message,Request.class);

                 if(request.getTitle().equals("Sign up")) {
                     processSignUp();
                 }
               else if(request.getTitle().equals("Sign In")) {
                   processSignIn();

               }
                else if(clientToHandler.getString("Title").equals("get Followers"))
                {
                    //need page
                    Page page = (Page) clientToHandler.get("Page");
                    jo.put("Followers",page.getFollowers());
                    objectOutputStream.writeObject(jo);

                }
               else if(clientToHandler.getString("Title").equals("Delete Follower"))
                {
                    //client
                    //page follower
                }
               else if(clientToHandler.getString("Title").equals("Add Tweet"))
                {
                    //creat tweet and add
                    Tweet tweet = null;
                    try {
                         tweet = new Tweet((Client) clientToHandler.getJSONObject("parameterValues").get("Client"),clientToHandler.getJSONObject("parameterValues").getString("content").toCharArray());

                    }
                    catch (RuntimeException e)
                    {
                        jo.put("HasError","True");
                        jo.put("ErrorCode","Invalid char number for tweets");
                        jo.put("Count",0);
                        jo.put("Results","");
                        objectOutputStream.writeObject(jo);
                        continue;
                    }

                    tweetingService.addTweet(tweet);


                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("Content",tweet.getText());
                    jsonObject.put("UserName",tweet.getClient().getUserName());
                    jsonObject.put("CreationDate",tweet.getDate());
                    jsonObject.put("Like",tweet.getLikes());
                    jsonObject.put("Retweets",tweet.getRetweet());
                    jsonObject.put("Replies",tweet.getReplies());

                    jo.put("HasError","False");
                    jo.put("ErrorCode","");
                    jo.put("Count",5);
                    jo.put("Results",jsonObject);



                    //output
                    objectOutputStream.writeObject(jo);

                }
               else if(clientToHandler.getString("Title").equals("Delete Tweet"))
                {
                    tweetingService.deleteTweet((Tweet) clientToHandler.get("ParameterValues"));

                }
                else if(clientToHandler.getString("Title").equals("All Tweet")) //for choose like
                {
                    List<Message> AllTweet =new ArrayList<>();
                    int numberAllTweet=0;
                    for(Client client : database.getClients())
                    {
                        for (Tweet tweet : database.getClientPage(client).getTweets())
                        {
                            AllTweet.add(tweet);
                            numberAllTweet++;
                        }
                    }

                    //sort
                   AllTweet = timelineService.sortMessages(AllTweet);

                    jo.put("HasError","False");
                    jo.put("ErrorCode","");
                    jo.put("Count",numberAllTweet);
                    jo.put("Results",AllTweet);

                    //send all tweet
                    objectOutputStream.writeObject(jo);


                }
                else if(clientToHandler.getString("Title").equals("Like"))
                {
                    tweetingService.like(((Tweet)clientToHandler.getJSONObject("ParameterValues").get("Tweet")).getClient(),(Tweet) clientToHandler.getJSONObject("ParameterValues").get("Tweet"),
                         (Client) clientToHandler.getJSONObject("ParameterValues").get("MyClientToLike"));


                    jo.put("HasError","false");
                    jo.put("ErrorCode","");
                    jo.put("Count",1);
                    jo.put("Results",clientToHandler.getJSONObject("ParameterValues").get("Tweet"));

                    objectOutputStream.writeObject(jo);

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
        PersonalInformation personalInformation = authenticationService.signUpRequest(((ParameterValue) request.getParameterValue().get(0)).getValue()
                , ((ParameterValue) request.getParameterValue().get(1)).getValue());

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
        int year=Integer.parseInt(((ParameterValue)request.getParameterValue().get(2)).getValue());
        int month=Integer.parseInt(((ParameterValue)request.getParameterValue().get(3)).getValue());
        int day=Integer.parseInt(((ParameterValue)request.getParameterValue().get(4)).getValue());
        LocalDate localDate =LocalDate.of(year,month,day);

        Client client = new Client(((ParameterValue) request.getParameterValue().get(0)).getValue(),((ParameterValue) request.getParameterValue().get(1)).getValue(),
         localDate, personalInformation);

      page =  authenticationService.signUp(client,((ParameterValue)request.getParameterValue().get(5)).getValue(),((ParameterValue)request.getParameterValue().get(6)).getValue())
        response.setCount(1);

         result = new ArrayList<>();
         p = new ParameterValue("Result", "Connected");
        result.add(p);

        response.setResults(result);
        objectOutputStream.writeObject(response);



    }








    private void processSignIn() throws NoSuchAlgorithmException, IOException {


        page = authenticationService.signInRequest(((ParameterValue) request.getParameterValue().get(0)).getValue(),
                (((ParameterValue) request.getParameterValue().get(1)).getValue()));
        if (page == null) {
            response.setHasError(true);
            response.setErrorCode(15);

        }


        response.setCount(1);

        ArrayList<Object> result = new ArrayList<>();
        ParameterValue p = new ParameterValue("Result", "Connect!");
        result.add(p);

        response.setResults(result);
        objectOutputStream.writeObject(response);
    }

}


