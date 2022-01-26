package org.runApp;

import com.google.gson.internal.LinkedTreeMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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


    }

    public void openPage(MouseEvent e) throws IOException {


        fxmlCommandParserServiceImpl.menuController.showPage(TweetClientUsername.getText());
    }

    void getData( LinkedTreeMap<String, Object> treeMap) {
        TweetClientUsername.setText(treeMap.get("clientUsername") + "");
        TweetDate.setText(treeMap.get("date") + "");
        idTweet.setText(treeMap.get("id") + "");
        TweetText.setText("" + treeMap.get("text"));
        TweetRetweets.setText("Retweets :" + ((ArrayList<Object>) treeMap.get("retweets")).size());
        TweetLikes.setText("likes :" + ((ArrayList<String>) treeMap.get("likes")).size());


    }
}
