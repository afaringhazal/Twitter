import java.time.LocalDate;
import java.util.ArrayList;

public class Page  {

    private Client client;
    private String id;
    private String biography;
    private LocalDate joinDate;
    private ArrayList<Tweet>tweets;

    private ArrayList<Page>followers ;
    private ArrayList<Page>following ;

    private ArrayList<Tweet>otherTweet;

    //PM => Send a text message and talk



    public Page(Client client,String id,String biography,LocalDate joinDate) throws RuntimeException
    {
       this.client = client;
       this.id =id;
       this.biography = biography;

        this.joinDate = joinDate;

        tweets =new ArrayList<>();
        followers=new ArrayList<>();
        following = new ArrayList<>();

       if(biography.length()>256)
           throw new RuntimeException("More than 256!");
       else
           this.biography =biography;

    }

    public  ArrayList<Tweet> getTweets() {
        return tweets;
    }


    public ArrayList<Page>getFollowers(){
     return followers;
    }
    public ArrayList<Page>getFollowing()
    {
        return following;
    }

    public ArrayList<Tweet> getOtherTweet() {
        return otherTweet;
    }

    public void setOtherTweet(Tweet tweet)
    {
        otherTweet.add(tweet);
    }
    public void addFollowing(Page page)
    {
        following.add(page);
    }

    public void deleteFollowing(Page page)
    {
        following.remove(page);
    }
    public void deleteFollowers(Page page)
    {
        followers.remove(page);
    }


}
