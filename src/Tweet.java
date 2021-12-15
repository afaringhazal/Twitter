import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import java.time.LocalDateTime;

public class Tweet  extends Message{
    Client client;

    public Tweet(Client client,char[]text){
        this.client=client;
        this.date= LocalDateTime.now();
        this.text=text;

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

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Tweet tweet = (Tweet) o;
//        return (this.)
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(client);
//    }
}











/*

public class Tweet  extends Message{
    Client client;

    public Tweet(Client client, char[] text){

        super(LocalDateTime.now(),text);
        this.client=client;

    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

//    //!!!!!!
//    @Override
//    public String toString(){
//        return "";
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return (this.getClient().equals(tweet.getClient()))&& (super.getText().equals(tweet.getText())) && super.getDate().equals(tweet.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(client);
    }
}
*/