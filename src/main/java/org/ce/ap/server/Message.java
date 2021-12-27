package main.java.org.ce.ap.server;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Message {

    public LocalDateTime date;
    public char[] text;
    private ArrayList<Client> likes = new ArrayList<>();
    private ArrayList<Message> replies=new ArrayList<>();


    public ArrayList<Message> getReplies() {
        return replies;
    }

    public void addReply(Message message) {
       replies.add(message);
    }

    public void removeReply(Message message) {
        replies.remove(message);
    }

    public void Like(Client client)
    {
        likes.add(client);
    }

    public void dislike(Client client)
    {
        likes.remove(client);
    }

    public ArrayList<Client> getLikes()
    {
        return likes;
    }


    public char[] getText() {
        return text;
    }

    public LocalDateTime getDate() {
        return date;
    }
}


