package org.runApp;

import com.google.gson.internal.LinkedTreeMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.ArrayList;

public class NewTweetController {

    FXMLCommandParserServiceImpl fxmlCommandParserServiceImpl = App.fxmlCommandParserService ;



    @FXML
    private Button Like;

    @FXML
    private ImageView LikeButton;

    @FXML
    private Button Retweet;

    @FXML
    private Label TweetClientUsername;

    @FXML
    private Label TweetDate;

    @FXML
    private Label TweetLikes;

    @FXML
    private Circle TweetPicture;

    @FXML
    private Label TweetRetweets;

    @FXML
    private Label TweetText;

    @FXML
    private Label idTweet;

    @FXML
    void LikeTweet(ActionEvent event) throws IOException {
        System.out.println(idTweet.getText());
        fxmlCommandParserServiceImpl.requestToLike(idTweet.getText());
        App.setScene(FXMLLoader.load(getClass().getResource("Designed_Menu.fxml")));

    }

    @FXML
    void RetweetTweet(ActionEvent event) throws IOException {

        Parent node = FXMLLoader.load(getClass().getResource("AddTweet.fxml"));
        HBox hBox =(HBox) node.getChildrenUnmodifiable().get(2);
        Button ReTweet = (Button) hBox.getChildren().get(1);
        Button Send = (Button) hBox.getChildren().get(0);
        ReTweet.setVisible(true);
        Send.setVisible(false);
        Label label = (Label) node.getChildrenUnmodifiable().get(4);
        label.setText(idTweet.getText());
        //setId(idTweet.getText());
        App.setScene(node);


        //TextArea textArea = (TextArea) node.getChildrenUnmodifiable().get(1);;
//        while (ReTweet.isPressed()) {
//            textArea = (TextArea) node.getChildrenUnmodifiable().get(1);
//        }

        // fxmlCommandParserServiceImpl.requestToRetweet(idTweet.getText(),textArea.getText());

    }

    public ImageView getLikeButton() {
        return LikeButton;
    }

    public void setLikeButton(ImageView likeButton) {
        LikeButton = likeButton;
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

    public Label getIdTweet() {
        return idTweet;
    }

    public void setIdTweet(Label idTweet) {
        this.idTweet = idTweet;
    }

    void getData( LinkedTreeMap<String, Object> treeMap ) {
        TweetClientUsername.setText(treeMap.get("clientUsername") + "");
        TweetDate.setText(treeMap.get("date") + "");
        idTweet.setText(treeMap.get("id") + "");
        TweetText.setText("" + treeMap.get("text"));
        TweetRetweets.setText("Retweets :" + ((ArrayList<Object>) treeMap.get("retweets")).size());
        TweetLikes.setText("likes :" + ((ArrayList<String>) treeMap.get("likes")).size());


    }
}
