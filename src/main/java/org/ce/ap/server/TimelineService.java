package main.java.org.ce.ap.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public ArrayList<Message> gatherTimeline(String username) {
        ArrayList<Message> timeline = new ArrayList<>(gatherRetweets(username));
        System.out.println("retweets received");
        timeline.addAll(gatherTweets(username));
        System.out.println("tweets received");
        System.out.println(timeline);
        Set<Message> set = new HashSet<>(timeline);
        System.out.println(set);
        timeline.clear();
        timeline.addAll(set);
        System.out.println(timeline);
        //sortMessages(timeline);
        System.out.println("sorted");
        System.out.println(timeline);
        return timeline;
    }

    public ArrayList<Message> gatherTweets(String username){
        Client client=database.getClientFromUsername(username);
        ArrayList<Message> followingsTweets = new ArrayList<>();
        for (String userName : database.getClientPage(client).getFollowingsList()) {
            followingsTweets.addAll(database.getClientPageFromUsername(userName).getTweets());
        }
        followingsTweets.addAll(database.getClientPage(client).getTweets());
        return followingsTweets;
    }

    public ArrayList<Message> gatherRetweets(String username) {
        Client client=database.getClientFromUsername(username);
        ArrayList<Message> followingsRetweets = new ArrayList<>();
        for (String userName : database.getClientPage(client).getFollowingsList()) {
            followingsRetweets.addAll(database.getClientPageFromUsername(userName).getRetweets());
        }
        return followingsRetweets;

    }

}