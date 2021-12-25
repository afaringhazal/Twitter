import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import java.time.LocalDateTime;

public class Tweet  extends Message{
    Client client;
    private ArrayList<Client> retweet = new ArrayList<>();

    public Tweet(Client client,char[]text) throws RuntimeException{
        if(text.length < 256)
            this.text=text;
        else {
            throw new RuntimeException();
        }
        this.client=client;
        this.date= LocalDateTime.now();
    }
    public void changeDate()
    {
        date = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {

        return (o instanceof Tweet &&
                ((Tweet) o).date == this.date &&
                ((Tweet) o).client.equals(client) &&
                ((Tweet) o).text == text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client);
    }

    public Client getClient() {
        return client;
    }

    public void addClientToRetweetThisTweet(Client client)
    {
        retweet.add(client);
    }

    public ArrayList<Client> getRetweet() {
        return retweet;
    }
}
