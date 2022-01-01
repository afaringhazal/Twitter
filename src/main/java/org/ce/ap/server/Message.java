package main.java.org.ce.ap.server;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public  class Message implements Serializable {
    public String clientUsername;

    public int id;
    public LocalDateTime date;
    public String text;
    private ArrayList<String> likes = new ArrayList<>();
    private ArrayList<Message> replies = new ArrayList<>();
    protected ArrayList<String> retweets = new ArrayList<>();


    public ArrayList<Message> getReplies() {
        return replies;
    }

    public void addReply(Message message) {
        replies.add(message);
    }

    public void removeReply(Message message) {
        replies.remove(message);
    }

    public void Like(String userName) {
        likes.add(userName);
    }

    public void dislike(String clientId) {
        likes.remove(clientId);
    }

    public ArrayList<String> getLikes() {
        return likes;
    }


    public String getText() {
        return text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<String> getRetweets() {
        return retweets;
    }

    public boolean addUserNameToRetweet(String username){
      return  retweets.add(username);

    }
}


