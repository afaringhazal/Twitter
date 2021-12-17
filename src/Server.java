import java.util.ArrayList;
import java.util.HashMap;

public class Server {

    private static ArrayList<Client> clients;
    private static HashMap<Client, Page> ClientPage = new HashMap<>();


    public static Page getClientPage(Client client) {
        return ClientPage.get(client);
    }


    public static void addClient(Page page, Client client) {
        clients.add(client);
        ClientPage.put(client, page);
    }


    //setters and getters

    public static ArrayList<Client> getClients() {
        return clients;
    }

    public static ArrayList<Client> getClient() {
        return clients;
    }
}