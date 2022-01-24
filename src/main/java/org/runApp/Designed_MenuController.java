package org.runApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Designed_MenuController {
    FXMLCommandParserServiceImpl fxmlCommandParserServiceImpl =App.fxmlCommandParserService;

    @FXML
    private ScrollPane ShowSection;


    public void initialize() throws IOException {
      //  ShowSection.getChildrenUnmodifiable().add(fxmlCommandParserServiceImpl.requestTimeline());

        //ShowSection.getChildrenUnmodifiable()
        ShowSection.setContent(fxmlCommandParserServiceImpl.requestTimeline());

    }

    @FXML
    void ButtonCreatTweet(ActionEvent event) throws IOException {

        App.setScene(FXMLLoader.load(getClass().getResource("AddTweet.fxml")));


    }

    @FXML
    void ButtonShowMyPage(ActionEvent event) {







    }

    @FXML
    void ButtonTimeLine(ActionEvent event) throws IOException {


        ShowSection.setContent(fxmlCommandParserServiceImpl.requestTimeline());

       //ShowSection.getChildrenUnmodifiable().get(0);
       // ShowSection.getChildrenUnmodifiable().add(fxmlCommandParserServiceImpl.requestTimeline());

    }

}
