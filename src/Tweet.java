import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import java.time.LocalDateTime;

public class Tweet  extends Message{
    Client client;

    /** constructor of tweet
     * @param client the client who tweeted
     * @param text the tweet
     */
    public Tweet(Client client,char[]text){
        this.client=client;
        this.date= LocalDateTime.now();
        this.text=text;

    }

    /** compares an object to this tweet
     * @param o the object to compare
     * @return true if two objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {

        return (o instanceof Tweet &&
                ((Tweet) o).date == this.date &&
                ((Tweet) o).client.equals(client) &&
                ((Tweet) o).text == text);
    }


}
