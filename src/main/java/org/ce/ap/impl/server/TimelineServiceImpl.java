package org.ce.ap.impl.server;
import org.ce.ap.server.Client;
import org.ce.ap.server.Database;
import org.ce.ap.server.Message;
import org.ce.ap.server.TimelineService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * class : The type Timeline service.
 * @author MohammadHdi sheikhEslami
 * @author Rezvan Afari
 * @version 1.0.0
 */
public class TimelineServiceImpl implements TimelineService {

    /**
     * The Database.
     */
    Database database;
    /**
     * The Logger.
     */
    Logger logger;

    /**
     * Instantiates a new Timeline service.
     *
     * @param database the database
     */
    public TimelineServiceImpl(Database database) {
        this.database = database;
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }

    /**
     * Sort messages list.
     *
     * @param messages the messages
     * @return the list
     */

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
        logger.info("all messages were gathered and sorted. details:" + messages);
        return messages;
    }

    /**
     * Gather timeline array list.
     *
     * @param username the username
     * @return the array list
     */
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

    /**
     * Gather tweets array list.
     *
     * @param username the username
     * @return the array list
     */
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

    /**
     * Gather retweets array list.
     *
     * @param username the username
     * @return the array list
     */
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