import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    private static ArrayList<Client>clients;
    private  static HashMap<Client,Page> basic = new HashMap<Client,Page>();
    //-----------------------
    //private ArrayList<Tweet>tweets  => order date : show
    //---------------------------


    public static ArrayList<Client> getClients() {
        return clients;
    }

//    public static void setClients(ArrayList<Client> clients) {
//        Server.clients = clients;
//    }

    public static Page getClientPage(Client client)
    {
        return basic.get(client);
    }

    public static ArrayList<Client> getClient()
    {
        return clients;
    }



    public static void addClient(Page page , Client client)
    {
        clients.add(client);
        basic.put(client , page);
    }




    //check out user






}
