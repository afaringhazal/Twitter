package main.java.org.ce.ap.server;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

public class Database implements Serializable {

    private ArrayList<Client> clients;
    private ArrayList<Tweet> allTweets;
    private HashMap<Client, Page> ClientPage ;
    private HashMap<String, Client> ClientId ;




    public Database() throws NoSuchAlgorithmException {
        clients=new ArrayList<>();
        allTweets=new ArrayList<>();
        ClientPage = new HashMap<>();
        ClientId= new HashMap<>();

    }


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
            if (c.getUserName().equals(s)){
                client=c;
            }
        }
        return client;
    }


    public ArrayList <String> getUserNames(){
        ArrayList<String> usernames=new ArrayList<>();
        for (Client client:clients){

            usernames.add(client.getUserName());

        }

        return usernames;
    }




    public ArrayList<Tweet> getAllTweets(){

        ArrayList<Tweet> arrayList=new ArrayList<>();
        for (Client client : clients){
            arrayList.addAll(getClientPage(client).getTweets());
        }
        return arrayList;

    }
    public ArrayList<Reply> getAllReplies(){

        ArrayList<Reply> arrayList=new ArrayList<>();
        for (Client client : clients){
            arrayList.addAll(getClientPage(client).getReplies());
        }
        return arrayList;

    }
    public ArrayList<Retweet> getAllRetweets(){

        ArrayList<Retweet> arrayList=new ArrayList<>();
        for (Client client : clients){
            arrayList.addAll(getClientPage(client).getRetweets());
        }
        return arrayList;

    }




}