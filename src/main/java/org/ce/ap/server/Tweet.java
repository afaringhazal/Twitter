package org.ce.ap.server;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * class : The type Tweet.
 * @author MohammadHdi sheikhEslami
 * @author Rezvan Afari
 * @version 1.0.0
 */
public class Tweet  extends Message {

    /**
     * Instantiates a new Tweet.
     *
     * @param client the client
     * @param text   the text
     * @throws RuntimeException the runtime exception
     */
    public Tweet(String client, String text) throws RuntimeException {
        System.out.println(text);
        if (text.length() < 256)
            this.text = text;
        else {
            throw new RuntimeException();
        }
        this.clientUsername = client;
        this.date = LocalDateTime.now();

    }

    /**
     * Change date.
     */
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

    /**
     * Gets client.
     *
     * @return the client
     */
    public String getClient() {
        return clientUsername;
    }

    /**
     * Add client to retweet this tweet.
     *
     * @param client the client
     */
    public void addClientToRetweetThisTweet(Client client) {
        retweets.add(client.getUserName());
    }


}