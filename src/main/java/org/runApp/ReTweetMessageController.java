package org.runApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ReTweetMessageController {

    FXMLCommandParserServiceImpl fxmlCommandParserServiceImpl = App.fxmlCommandParserService;
    @FXML
    private TextField IdRetweet;

    @FXML
    private TextField IdTweet;

    @FXML
    void LikeTweet(ActionEvent event) throws IOException {
        System.out.println(IdTweet.getText());
        fxmlCommandParserServiceImpl.requestToLike(IdTweet.getText());
        //App.setScene(fxmlCommandParserServiceImpl.requestTimeline());
        App.setScene(FXMLLoader.load(getClass().getResource("Designed_Menu.fxml")));

    }
    @FXML
    void RetweetTweet(ActionEvent event) {

    }
}
