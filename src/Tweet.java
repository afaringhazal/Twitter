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


}
