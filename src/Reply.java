import java.time.LocalDateTime;

public class Reply extends Message {
    Message tweet;
    Client replier;

    public Reply(Message tweet, Client replier, char[] text) {

        this.tweet = tweet;


        if (tweet instanceof Tweet){}
        else if(tweet instanceof Reply){

        }

        this.replier = replier;
        this.date = LocalDateTime.now();
        this.text = text;

    }

    //!!!!!!!
    @Override
    public String toString() {
        return "";
    }



}