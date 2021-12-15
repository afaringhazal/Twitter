import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import java.time.*;

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





/*
public abstract class Message {
    //date
    //text
    //client(s)

    private LocalDateTime date;
    private char[] text;

    public Message(LocalDateTime date, char[] text) {
        this.date = date;
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public char[] getText() {
        return text;
    }

    public void setText(char[] text) {
        this.text = text;
    }
}
*/