import java.util.ArrayList;
import java.util.List;

public class TimelineService {


    /** returns all tweets of people a client has followed
     * @param client the follower
     * @return
     */
    public ArrayList<Message> getFollowingsTweets(Client client) {

        ArrayList<Message> followingsTweets = new ArrayList<>();
        for (Page page : Server.getClientPage(client).getFollowingsList()) {
            followingsTweets.addAll(page.getTweets());
        }

        return (ArrayList<Message>) sortMessages(followingsTweets);
    }

    /** returns all liked tweets of people a client has followed
     * @param client the follower
     * @return returns all liked tweets of people a client has followed
     */
    public ArrayList<Message> getFollowingsLikes(Client client) {
        ArrayList<Message> followingsLikes = new ArrayList<>(Server.getClientPage(client).getLikedTweetsList());

        return (ArrayList<Message>)sortMessages(followingsLikes);
    }

    /** returns all retweets of people a client has followed
     * @param client the follower
     * @return all retweets of people a client has followed
     */
    public ArrayList<Message> getFollowingsRetweets(Client client) {

        ArrayList<Message> followingsRetweets = new ArrayList<>(Server.getClientPage(client).getRetweets());

        return (ArrayList<Message>) sortMessages(followingsRetweets);

    }

    /** returns all replies of people a client has followed
     * @param client  the follower
     * @return all replies of people a client has followed
     */
    public ArrayList<Message> getFollowingsReplies(Client client) {

        ArrayList<Message> followingsReplies = new ArrayList<>(Server.getClientPage(client).getReplies());
        return (ArrayList<Message>) sortMessages(followingsReplies);
    }


    /** sorts a list of messages by their date
     * @param messages list of messages
     * @return sorted list of messages
     */
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
}