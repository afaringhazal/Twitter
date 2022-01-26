package org.runApp;

import com.google.gson.internal.LinkedTreeMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.ArrayList;

public class NewRetweetController {

    FXMLCommandParserServiceImpl fxmlCommandParserServiceImpl = App.fxmlCommandParserService;

    @FXML
    private Button LikeButton;

    @FXML
    private AnchorPane Retweet;

    @FXML
    private Button RetweetButton;

    @FXML
    private Label RetweetClientUsername;

    @FXML
    private Label RetweetDate;

    @FXML
    private Label RetweetID;

    @FXML
    private Label RetweetLikes;

    @FXML
    private Circle RetweetPicture;

    @FXML
    private Label RetweetRetweets;

    @FXML
    private Label RetweetText;

    @FXML
    private AnchorPane RetweetedMessage;

    @FXML
    private Label TweetClientUsername;

    @FXML
    private Label TweetDate;

    @FXML
    private Label TweetID;

    @FXML
    private Label TweetLikes;

    @FXML
    private Circle TweetPicture;

    @FXML
    private Label TweetRetweets;

    @FXML
    private Label TweetText;

    @FXML
    void LikeTweet(ActionEvent event) throws IOException {

        System.out.println(RetweetID.getText());
        fxmlCommandParserServiceImpl.requestToLike(RetweetID.getText());
        //App.setScene(fxmlCommandParserServiceImpl.requestTimeline());
        App.setScene(FXMLLoader.load(getClass().getResource("Designed_Menu.fxml")));
    }

    @FXML
    void RetweetRetweet(ActionEvent event) throws IOException {
        Parent node = FXMLLoader.load(getClass().getResource("AddTweet.fxml"));
        HBox hBox =(HBox) node.getChildrenUnmodifiable().get(2);
        Button ReTweet = (Button) hBox.getChildren().get(1);
        Button Send = (Button) hBox.getChildren().get(0);
        ReTweet.setVisible(true);
        Send.setVisible(false);
        Label label = (Label) node.getChildrenUnmodifiable().get(4);
        label.setText(RetweetID.getText());
        //setId(idTweet.getText());
        App.setScene(node);


    }

    public Label getRetweetClientUsername() {
        return RetweetClientUsername;
    }

    public void setRetweetClientUsername(Label retweetClientUsername) {
        RetweetClientUsername = retweetClientUsername;
    }

    public Label getRetweetDate() {
        return RetweetDate;
    }

    public void setRetweetDate(Label retweetDate) {
        RetweetDate = retweetDate;
    }

    public Label getRetweetID() {
        return RetweetID;
    }

    public void setRetweetID(Label retweetID) {
        RetweetID = retweetID;
    }

    public Label getRetweetLikes() {
        return RetweetLikes;
    }

    public void setRetweetLikes(Label retweetLikes) {
        RetweetLikes = retweetLikes;
    }

    public Circle getRetweetPicture() {
        return RetweetPicture;
    }

    public void setRetweetPicture(Circle retweetPicture) {
        RetweetPicture = retweetPicture;
    }

    public Label getRetweetRetweets() {
        return RetweetRetweets;
    }

    public void setRetweetRetweets(Label retweetRetweets) {
        RetweetRetweets = retweetRetweets;
    }

    public Label getRetweetText() {
        return RetweetText;
    }

    public void setRetweetText(Label retweetText) {
        RetweetText = retweetText;
    }

    public Label getTweetClientUsername() {
        return TweetClientUsername;
    }

    public void setTweetClientUsername(Label tweetClientUsername) {
        TweetClientUsername = tweetClientUsername;
    }

    public Label getTweetDate() {
        return TweetDate;
    }

    public void setTweetDate(Label tweetDate) {
        TweetDate = tweetDate;
    }

    public Label getTweetID() {
        return TweetID;
    }

    public void setTweetID(Label tweetID) {
        TweetID = tweetID;
    }

    public Label getTweetLikes() {
        return TweetLikes;
    }

    public void setTweetLikes(Label tweetLikes) {
        TweetLikes = tweetLikes;
    }

    public Circle getTweetPicture() {
        return TweetPicture;
    }

    public void setTweetPicture(Circle tweetPicture) {
        TweetPicture = tweetPicture;
    }

    public Label getTweetRetweets() {
        return TweetRetweets;
    }

    public void setTweetRetweets(Label tweetRetweets) {
        TweetRetweets = tweetRetweets;
    }

    public Label getTweetText() {
        return TweetText;
    }

    public void setTweetText(Label tweetText) {
        TweetText = tweetText;
    }
    public void getData(LinkedTreeMap<String, Object> treeMap ) {
        RetweetID.setText(treeMap.get("id")+"");
        RetweetDate.setText(treeMap.get("date")+"");
        RetweetClientUsername.setText(treeMap.get("clientUsername")+"");
        RetweetText.setText(treeMap.get("text")+"");
        RetweetLikes.setText("Likes : "+((ArrayList<String>) treeMap.get("likes")).size());
        RetweetRetweets.setText("Retweets :"+((ArrayList<Object>) treeMap.get("retweets")).size());

        LinkedTreeMap<String, Object> treeMapToRetweet = (LinkedTreeMap<String, Object>) treeMap.get("tweet");

        TweetID.setText(treeMapToRetweet.get("id")+"");
        TweetDate.setText(treeMapToRetweet.get("date")+"");
        TweetClientUsername.setText(""+ treeMap.get("clientUsername"));
        TweetText.setText(treeMapToRetweet.get("text")+"");
        TweetLikes.setText("Likes : "+((ArrayList<String>) treeMapToRetweet.get("likes")).size());
        TweetRetweets.setText("Retweet :"+((ArrayList<Object>) treeMapToRetweet.get("retweets")).size());


    }
}
