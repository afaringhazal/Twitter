package org.runApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class AddTweetController {

    FXMLCommandParserServiceImpl fxmlCommandParserServiceImpl = App.fxmlCommandParserService;

    @FXML
    private Label ErrorTweetTextId;

    @FXML
    private TextArea TweetTextId;

    @FXML
    private Label IdRetweet;


    @FXML
    void buttonBack(ActionEvent event) throws IOException {
        //App.setScene(fxmlCommandParserServiceImpl.requestTimeline());
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Designed_Menu.fxml"));
        Parent root = fxmlLoader.load();
        fxmlCommandParserServiceImpl.menuController=fxmlLoader.getController();

        App.setScene(root);
        //App.setScene(FXMLLoader.load(getClass().getResource("Designed_Menu.fxml")));



    }

    @FXML
    void buttonSendTweet(ActionEvent event) throws IOException {

        ErrorTweetTextId.setText("");
        ErrorTweetTextId.setText(fxmlCommandParserServiceImpl.addTweet(TweetTextId.getText()));
        if(ErrorTweetTextId.getText().isEmpty() || ErrorTweetTextId.getText().isBlank()){
           // App.setScene(fxmlCommandParserServiceImpl.requestTimeline());

            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Designed_Menu.fxml"));
            Parent root = fxmlLoader.load();
            fxmlCommandParserServiceImpl.menuController=fxmlLoader.getController();

            App.setScene(root);


            //App.setScene(FXMLLoader.load(getClass().getResource("Designed_Menu.fxml")));
        }

    }

    @FXML
    void buttonRetweet(ActionEvent event) throws IOException {

        ErrorTweetTextId.setText("");
        if (TweetTextId.getText().length() > 256) {
            ErrorTweetTextId.setText("Tweets should have no more than 256 characters and should not be empty.\n Please try again.");


        } else {
            fxmlCommandParserServiceImpl.requestToRetweet(IdRetweet.getText(), TweetTextId.getText());


            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Designed_Menu.fxml"));
            Parent root = fxmlLoader.load();
            fxmlCommandParserServiceImpl.menuController=fxmlLoader.getController();

            App.setScene(root);
           // App.setScene(FXMLLoader.load(getClass().getResource("Designed_Menu.fxml")));


            // App.setScene(fxmlCommandParserServiceImpl.requestTimeline());
        }
    }






}
