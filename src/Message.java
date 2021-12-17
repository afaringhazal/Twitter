import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Message {

    public LocalDateTime date;
    public char[] text;

    //add for like
    private ArrayList<Client> saveLiked = new ArrayList<>();


    public void addSaveLiked(Client client)
    {
        saveLiked.add(client);
    }


    public void deleteSaveLike(Client client)
    {
        saveLiked.remove(client);
    }


    public ArrayList<Client> getSaveLiked()
    {
        return saveLiked;
    }


}


