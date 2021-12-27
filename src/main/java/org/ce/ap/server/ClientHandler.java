package main.java.org.ce.ap.server;

import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

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
    JSONObject jo = new JSONObject();


    public ClientHandler(Socket socket, AuthenticationService authenticationService, TweetingService tweetingService) {
        this.socket = socket;
        this.clientIsConnected = true;


        this.authenticationService = authenticationService;
        this.tweetingService = tweetingService;

    }

    @Override
    public void run() {

        ObjectInputStream objectInputStream ;
        ObjectOutputStream objectOutputStream;
        JSONObject clientToHandler = null;
        Gson gson = new Gson();
        
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            while (clientIsConnected) {

               // System.out.println("***");
               // System.out.println(objectInputStream.available());
               //Object g = objectInputStream.readObject();
              // clientToHandler =(JSONObject)objectInputStream.readObject();

               //clientToHandler = (JSONObject) objectInputStream.readObject();
               String message = (String) objectInputStream.readObject();
               
                PersonalInformation personalInformation = gson.fromJson(message, PersonalInformation.class);
               // System.out.println("personalInformation.getPassword() = " + personalInformation.getPassword());
                //System.out.println("personalInformation = " + personalInformation.getUserName());
                
                //  System.out.println(message);

                System.out.println("KKKKKKK");

                if((clientToHandler.get("Title")).equals("Sign Up")) {
                    // String firstName, String lastName, LocalDate birthday, String userName, String password)
                    Page page = authenticationService.signUpRequest((Client)clientToHandler.get("Client"),clientToHandler.getString("Bio"),clientToHandler.getString("Id"));

                    jo.put("Page",page);
                    objectOutputStream.writeObject(jo);

                }
               else if((clientToHandler.get("Title")).equals("Sign In")) {

                    Page page =authenticationService.signInRequest(clientToHandler.getJSONObject("ParameterValues").getString("UserName"),
                             clientToHandler.getJSONObject("ParameterValues").getString("Password"));

                    jo.put("Page",page);
                    objectOutputStream.writeObject(jo);


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






}


