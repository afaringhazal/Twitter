import java.util.ArrayList;

public class Page extends Client {

   // private Client client;
    private ArrayList<Tweet>tweets;
    private ArrayList<Page>followers ;
    private ArrayList<Page>following ;
    //-----------------------------
    //private ArrayList<Tweet>otherTweet;
    //----------------------------------
    //PM => Send a text message and talk



    public Page(Client client) {
       //this.client = client;
        super(client.getFirstName(),client.getLastName(),client.getAge(),client.getBiography(),client.getJoinDate(),client.getUserName(),client.getPassword());
       tweets =new ArrayList<>();
       followers=new ArrayList<>();
       following = new ArrayList<>();

    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }




//    public Tweet getOneTweet()
//    {
//        return tweets.get()
//    }
//    public void addTweet(Tweet tweet)
//    {
//        tweets.add(tweet);
//    }
//    public void deleteTweet(Tweet tweet)
//    {
//        tweets.remove(tweet);
//    }
//    public void addLike(Tweet tweet)
//    {
//
//    }

















}
