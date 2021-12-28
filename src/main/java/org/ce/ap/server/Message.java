package main.java.org.ce.ap.server;

import main.java.org.ce.ap.client.Client;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Message {

    public LocalDateTime date;
    public String text;
    private ArrayList<String> likes = new ArrayList<>();
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

    public void Like(String userName)
    {
        likes.add(userName);
    }

    public void dislike(String clientId)
    {
        likes.remove(clientId);
    }

    public ArrayList<String> getLikes()
    {
        return likes;
    }


    public String getText() {
        return text;
    }

    public LocalDateTime getDate() {
        return date;
    }
}


