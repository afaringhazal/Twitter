package org.ce.ap.server;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * class :The type Database.
 * @author MohammadHdi sheikhEslami
 * @author Rezvan Afari
 * @version 1.0.0
 */
public class Database implements Serializable {

    private ArrayList<Client> clients;
    private ArrayList<Tweet> allTweets;
    private HashMap<Client, Page> ClientPage ;
    private HashMap<String, Client> ClientId ;


    /**
     * Instantiates a new Database.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public Database() throws NoSuchAlgorithmException {
        clients=new ArrayList<>();
        allTweets=new ArrayList<>();
        ClientPage = new HashMap<>();
        ClientId= new HashMap<>();

    }


    /**
     * Gets clients.
     *
     * @return the clients
     */
    public ArrayList<Client> getClients() {
        return clients;
    }

    /**
     * Gets all client pages.
     *
     * @return the all client pages
     */
    public HashMap<Client, Page> getAllClientPages() {
        return ClientPage;
    }

    /**
     * Gets client page.
     *
     * @param client the client
     * @return the client page
     */
    public Page getClientPage(Client client) {
        return ClientPage.get(client);
    }


    /**
     * Gets client page from username.
     *
     * @param s the s
     * @return the client page from username
     */
    public Page getClientPageFromUsername(String s) {
        return getClientPage(ClientId.get(s));
    }


    /**
     * Get client ids hash map.
     *
     * @return the hash map
     */
    public HashMap<String,Client> getClientIds(){

        return ClientId;
    }


    /**
     * Get client from username client.
     *
     * @param s the s
     * @return the client
     */
    public Client getClientFromUsername(String s){
        Client client=null;
        for (Client c:clients){
            if (c.getUserName().equals(s)){
                client=c;
            }
        }
        return client;
    }


    /**
     * Get user names array list .
     *
     * @return the array list
     */
    public ArrayList <String> getUserNames(){
        ArrayList<String> usernames=new ArrayList<>();
        for (Client client:clients){

            usernames.add(client.getUserName());

        }

        return usernames;
    }


    /**
     * Get all tweets array list.
     *
     * @return the array list
     */
    public ArrayList<Tweet> getAllTweets(){

        ArrayList<Tweet> arrayList=new ArrayList<>();
        for (Client client : clients){
            arrayList.addAll(getClientPage(client).getTweets());
        }
        return arrayList;

    }

    /**
     * Get all retweets array list.
     *
     * @return the array list
     */
    public ArrayList<Retweet> getAllRetweets(){

        ArrayList<Retweet> arrayList=new ArrayList<>();
        for (Client client : clients){
            arrayList.addAll(getClientPage(client).getRetweets());
        }
        return arrayList;

    }




}