package main.java.org.ce.ap.server;

import java.util.ArrayList;
import java.util.List;

public class TimelineService {

    Database database;
    public TimelineService(Database database){
        this.database = database;
    }
    public List<Message> sortMessages(List<Message> messages) {
        List<Message> messageList =messages;
        List<Message> newList = new ArrayList<>();

        while (!messageList.isEmpty()) {
            Message latestMessage = messageList.get(0);
            for (Message message : messageList) {
                if (message.date.isAfter(latestMessage.date)) {
                    latestMessage = message;
                }
            }
            newList.add(latestMessage);
            messageList.remove(latestMessage);
        }
        messages = newList;

        return messages;
    }

    public ArrayList<Message> followingsTweets(Client client) {

        ArrayList<Message> followingsTweets = new ArrayList<>();
        for (Page page : database.getClientPage(client).getFollowingsList()) {
            followingsTweets.addAll(page.getTweets());
        }
        sortMessages(followingsTweets);
        return followingsTweets;
    }

    public ArrayList<Message> followingsLikes(Client client) {
        ArrayList<Message> followingsTweets = new ArrayList<>(database.getClientPage(client).getLikedTweetsList());
        sortMessages(followingsTweets);
        return followingsTweets;
    }

    public ArrayList<Message> followingsRetweets(Client client) {

        ArrayList<Message> followingsTweets = new ArrayList<>();
        for (Page page : database.getClientPage(client).getFollowingsList()) {
            followingsTweets.addAll(page.getTweets());
        }
        sortMessages(followingsTweets);
        return followingsTweets;

    }

    public ArrayList<Message> followingsReplies(Client client) {


        ArrayList<Message> followingsTweets = new ArrayList<>();
        for (Page page : database.getClientPage(client).getFollowingsList()) {
            followingsTweets.addAll(page.getTweets());
        }
        sortMessages(followingsTweets);
        return followingsTweets;
    }
}