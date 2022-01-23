package org.runApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
        App.setScene(fxmlCommandParserServiceImpl.requestTimeline());
    }

    @FXML
    void RetweetTweet(ActionEvent event) {

    }

}
