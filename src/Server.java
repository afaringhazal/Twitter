import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    private ArrayList<Client>clients;
    private  HashMap<Client,Page> basic = new HashMap<Client,Page>();
    //-----------------------
    //private ArrayList<Tweet>tweets  => order date : show
    //---------------------------



    public Page getBasic(Client client)
    {
        return basic.get(client);
    }

    public ArrayList<Client> getClient()
    {
        return clients;
    }









}
