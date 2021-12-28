package main.java.org.ce.ap.server;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Tweet  extends Message {
    String clientUsername;

    public int id;

    public Tweet(String client, String text, int id) throws RuntimeException {
        if (text.length() < 256)
            this.text = text;
        else {
            throw new RuntimeException();
        }
        this.clientUsername = client;
        this.date = LocalDateTime.now();
        this.id = id;
    }

    public void changeDate() {
        date = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {

        return (o instanceof Tweet &&
                ((Tweet) o).date == this.date &&
                ((Tweet) o).clientUsername.equals(clientUsername) &&
                ((Tweet) o).text == text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientUsername);
    }

    public String getClient() {
        return clientUsername;
    }

    public void addClientToRetweetThisTweet(Client client) {
        retweets.add(client.getUserName());
    }


}