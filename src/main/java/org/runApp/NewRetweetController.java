package org.runApp;

import com.google.gson.internal.LinkedTreeMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
        //App.setScene(FXMLLoader.load(getClass().getResource("Designed_Menu.fxml")));
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Designed_Menu.fxml"));
        Parent root = fxmlLoader.load();
        fxmlCommandParserServiceImpl.menuController=fxmlLoader.getController();

        App.setScene(root);
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

    public void openTweetClientPage(MouseEvent e) throws IOException {


        fxmlCommandParserServiceImpl.menuController.showPage(TweetClientUsername.getText());
    }
    public void openRetweetClientPage(MouseEvent e) throws IOException {


        fxmlCommandParserServiceImpl.menuController.showPage(RetweetClientUsername.getText());
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
        TweetClientUsername.setText(""+ treeMapToRetweet.get("clientUsername"));
        TweetText.setText(treeMapToRetweet.get("text")+"");
        TweetLikes.setText("Likes : "+((ArrayList<String>) treeMapToRetweet.get("likes")).size());
        TweetRetweets.setText("Retweet :"+((ArrayList<Object>) treeMapToRetweet.get("retweets")).size());


    }
}
