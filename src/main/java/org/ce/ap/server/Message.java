package main.java.org.ce.ap.server;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 * class :The type Message.
 * @author MohammadHdi sheikhEslami
 * @author Rezvan Afari
 * @version 1.0.0
 */
public  class Message implements Serializable {
    /**
     * The Client username.
     */
    public String clientUsername;

    /**
     * The Id.
     */
    public int id;
    /**
     * The Date.
     */
    public LocalDateTime date;
    /**
     * The Text.
     */
    public String text;
    private ArrayList<String> likes = new ArrayList<>();
    private ArrayList<Message> replies = new ArrayList<>();
    /**
     * The Retweets.
     */
    protected ArrayList<String> retweets = new ArrayList<>();


    /**
     * Gets replies.
     *
     * @return the replies
     */
    public ArrayList<Message> getReplies() {
        return replies;
    }

    /**
     * Add reply.
     *
     * @param message the message
     */
    public void addReply(Message message) {
        replies.add(message);
    }

    /**
     * Remove reply.
     *
     * @param message the message
     */
    public void removeReply(Message message) {
        replies.remove(message);
    }

    /**
     * Like.
     *
     * @param userName the user name
     */
    public void Like(String userName) {
        likes.add(userName);
    }

    /**
     * Dislike.
     *
     * @param clientId the client id
     */
    public void dislike(String clientId) {
        likes.remove(clientId);
    }

    /**
     * Gets likes.
     *
     * @return the likes
     */
    public ArrayList<String> getLikes() {
        return likes;
    }


    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Sets text.
     *
     * @param text the text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets retweets.
     *
     * @return the retweets
     */
    public ArrayList<String> getRetweets() {
        return retweets;
    }

    /**
     * Add user name to retweet boolean.
     *
     * @param username the username
     * @return the boolean
     */
    public boolean addUserNameToRetweet(String username){
      return  retweets.add(username);

    }
}


