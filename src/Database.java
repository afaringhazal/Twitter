import java.util.ArrayList;
import java.util.HashMap;

public class Database {

    private ArrayList<Client> clients;
    private HashMap<Client, Page> ClientPage = new HashMap<>();

    public ArrayList<Client> getClients() {
        return clients;
    }

    public HashMap<Client, Page> getAllClientPages() {
        return ClientPage;
    }

    public Page getClientPage(Client client) {
        return ClientPage.get(client);
    }


}