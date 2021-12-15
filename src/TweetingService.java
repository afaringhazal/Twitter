public class TweetingService {

//
//    public TweetingService(ArrayList<Client> clients, HashMap<Client, Page> ll) {
//        super(clients, ll);
//    }

    public void addTweet(Tweet tweet , Client client)
    {
        for(Tweet t : Server.getClientPage(client).getTweets())
        {
            if(t.equals(tweet))
            {
                ObserverService.DeletingForOtherPeople(client,tweet);
                //update date
               //better idea
                ObserverService.notification(client, tweet);
                return;
            }
        }

        Server.getClientPage(client).getTweets().add(tweet);
        ObserverService.notification(client,tweet);
    }

    public void deleteTweet(Tweet tweet,Client client)
    {
        Server.getClientPage(client).getTweets().remove(tweet);
        ObserverService.DeletingForOtherPeople(client,tweet);
    }


//    public void retweet(Client client , Tweet tweet)
//    {
//
//        for(Tweet t:Server.getClientPage(client).getTweets())
//        {
//            if(t.equals(tweet))
//            {
//                //update date
//                return;
//            }
//        }
//
//        Server.getClientPage(client).getTweets().add(tweet);
//    }


    public void like(Client client , Tweet tweet, Client mine)
    {
        for(Tweet t:Server.getClientPage(client).getTweets())
        {
            if(t.equals(tweet))
            {
                for (Client client1 : t.getSaveLiked())
                {
                    if(client1.equals(mine))
                    {
                        // before exit
                        disLike(client,tweet, mine);
                    }
                }

                t.addSaveLiked(mine);
                //Like++
                return;
            }
        }

    }

    public void disLike(Client client , Tweet tweet, Client mine)
    {
        for(Tweet t : Server.getClientPage(client).getTweets())
        {
            if(t.equals(tweet))
            {
                t.deleteSaveLike(mine);
                //Like--
                return;
            }
        }


    }
















}
