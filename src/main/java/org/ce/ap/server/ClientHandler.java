package main.java.org.ce.ap.server;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface ClientHandler extends Runnable {


    @Override
    public void run() ;

    public void sendMyTweetAndReplies() throws IOException;

    public void processSignUp() throws IOException, NoSuchAlgorithmException, ClassNotFoundException ;


    public void processSignIn() throws NoSuchAlgorithmException, IOException ;


    public void processTweet() throws IOException ;
    public void processTimeLine() throws IOException;

    public void ShowAllFollowers() throws IOException ;

    public void requestDeleteFollower() throws IOException ;

    public void requestUnfollow() throws IOException ;

    public void requestFollow() throws IOException ;

    public void refreshResponse();

    public void ShowAllUsernames() throws IOException ;

    public void showFollowersAndFollowings() throws IOException ;

//    public void sentMyTweetAndReplies() throws IOException ;

    public void myFavoriteTweets() throws IOException ;

    public void DisplayPageInformation() throws IOException ;

    public void editProfile();

}



