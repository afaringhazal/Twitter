import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Message {

    public LocalDateTime date;
    public char[] text;


    private ArrayList<Client> peopleWhoLikedThisMessage = new ArrayList<>();


    /** adds a like to the message
     * @param client client who liked the message
     */
    public void addLike(Client client)
    {
        peopleWhoLikedThisMessage.add(client);
    }


    /**removes a like from the message
     * @param client client who liked the message
     */
    public void deleteLike(Client client)
    {
        peopleWhoLikedThisMessage.remove(client);
    }


    /** returns list of people who liked this message
     * @return list of people who liked this message
     */
    public ArrayList<Client> getPeopleWhoLikedThisMessage()
    {
        return peopleWhoLikedThisMessage;
    }


}


