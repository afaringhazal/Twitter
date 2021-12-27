package main.java.org.ce.ap.server;

import main.java.org.ce.ap.client.Client;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {

    private ArrayList<Client> clients;
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


    public Client getClientId(String s) {
        return ClientId.get(s);
    }
    public HashMap<String,Client> getClientIds(){

        return ClientId;
    }
}