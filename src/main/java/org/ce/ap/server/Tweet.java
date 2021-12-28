package main.java.org.ce.ap.server;

import main.java.org.ce.ap.client.Client;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Tweet  extends Message{
    Client client;
    private ArrayList<Client> retweets = new ArrayList<>();

    public Tweet(Client client,String text) throws RuntimeException{
        if(text.length() < 256)
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
        retweets.add(client);
    }

    public ArrayList<Client> getRetweets() {
        return retweets;
    }
}
