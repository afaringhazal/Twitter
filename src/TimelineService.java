import java.util.ArrayList;
import java.util.List;

public class TimelineService {

    public List<Message> sortMessages(List<Message> messageList)
    {
        List<Message> newList = new ArrayList<Message>();

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
        return newList;

    }

    public ArrayList<Message> followingsTweets(Client client) {
        ArrayList<Message> followingsTweets=new ArrayList<>();
        for (Page page : Server.getClientPage(client).getFollowingsList()) {
            followingsTweets.addAll(page.getTweets());
        }
        sortMessages(followingsTweets);
        return followingsTweets;
    }

    public ArrayList<Message> followingsLikes(Client client) {
        ArrayList<Message> followingsTweets = new ArrayList<>(Server.getClientPage(client).getLikedTweetsList());
        sortMessages(followingsTweets);
        return followingsTweets;
    }

    public void followingsRetweets(Client client) {


    }
    public void followingsReplies(Client client) {


    }







}
