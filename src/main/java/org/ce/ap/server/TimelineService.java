package main.java.org.ce.ap.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The interface Timeline service.
 */
public interface TimelineService {
    /**
     * Sort messages list.
     *
     * @param messages the messages
     * @return the list
     */

     List<Message> sortMessages(List<Message> messages);

    /**
     * Gather timeline array list.
     *
     * @param username the username
     * @return the array list
     */
     ArrayList<Message> gatherTimeline(String username) ;

    /**
     * Gather tweets array list.
     *
     * @param username the username
     * @return the array list
     */
     ArrayList<Message> gatherTweets(String username);

    /**
     * Gather retweets array list.
     *
     * @param username the username
     * @return the array list
     */
     ArrayList<Message> gatherRetweets(String username) ;
}