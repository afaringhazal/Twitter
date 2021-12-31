package main.java.org.ce.ap.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface TimelineService {

    public List<Message> sortMessages(List<Message> messages);
    public ArrayList<Message> gatherTimeline(String username) ;
    public ArrayList<Message> gatherTweets(String username);

    public ArrayList<Message> gatherRetweets(String username) ;
}