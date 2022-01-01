package main.java.org.ce.ap.impl.server;

import main.java.org.ce.ap.server.Client;
import main.java.org.ce.ap.server.Database;
import main.java.org.ce.ap.server.Message;
import main.java.org.ce.ap.server.TimelineService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class TimelineServiceImpl implements TimelineService {

    Database database;
Logger logger;
    public TimelineServiceImpl(Database database) {
        this.database = database;
        logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }

    @Override
    public List<Message> sortMessages(List<Message> messages) {
        List<Message> messageList = messages;
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
        logger.info("all messages were gathered and sorted. details:" +messages);
        return messages;
    }

    @Override
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
        timeline = (ArrayList<Message>) sortMessages(timeline);
        System.out.println("sorted");
        System.out.println(timeline);
        return timeline;
    }

    @Override
    public ArrayList<Message> gatherTweets(String username) {
        Client client = database.getClientFromUsername(username);
        ArrayList<Message> followingsTweets = new ArrayList<>();
        for (String userName : database.getClientPage(client).getFollowingsList()) {
            followingsTweets.addAll(database.getClientPageFromUsername(userName).getTweets());
        }
        followingsTweets.addAll(database.getClientPage(client).getTweets());
        return followingsTweets;
    }

    @Override
    public ArrayList<Message> gatherRetweets(String username) {
        Client client = database.getClientFromUsername(username);
        ArrayList<Message> followingsRetweets = new ArrayList<>();
        for (String userName : database.getClientPage(client).getFollowingsList()) {
            followingsRetweets.addAll(database.getClientPageFromUsername(userName).getRetweets());
        }
        followingsRetweets.addAll(database.getClientPage(client).getRetweets());
        return followingsRetweets;

    }

}