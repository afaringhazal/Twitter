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

    public void followerMessages(Client client) {


    }
}
