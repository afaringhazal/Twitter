package main.java.org.ce.ap.server;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {

    private ArrayList<Client> clients=new ArrayList<>();
    private ArrayList<Tweet> allTweets=new ArrayList<>();
    private HashMap<Client, Page> ClientPage = new HashMap<>();
    private HashMap<String, Client> ClientId = new HashMap<>();

    public ArrayList<Client> getClients() {
        return clients;
    }

    public HashMap<Client, Page> getAllClientPages() {
        return ClientPage;
    }

    public Page getClientPage(Client client) {
        return ClientPage.get(client);
    }


    public Page getClientPageFromUsername(String s) {
        return getClientPage(ClientId.get(s));
    }

    public HashMap<String,Client> getClientIds(){

        return ClientId;
    }

    public Client getClientFromUsername(String s){
        Client client=null;
        for (Client c:clients){
            if (client.getUserName().equals(s)){
                client=c;
            }
        }
        return client;
    }
    public void addTweetToAllTweets(Tweet tweet){
        allTweets.add(tweet);
    }
   public Tweet getTweet(int id){

        for (Tweet tweet:allTweets){
            if (tweet.id==id){return tweet;}
        }
        return null ;
   }
}