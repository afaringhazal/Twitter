package org.runApp;

import com.google.gson.internal.LinkedTreeMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.TreeMap;

public class Designed_MenuController {
    FXMLCommandParserServiceImpl fxmlCommandParserServiceImpl =App.fxmlCommandParserService;

    @FXML
    private ScrollPane ShowSection;


    public void initialize() throws IOException {
      //  ShowSection.getChildrenUnmodifiable().add(fxmlCommandParserServiceImpl.requestTimeline());

        //ShowSection.getChildrenUnmodifiable()
        ShowSection.setContent(fxmlCommandParserServiceImpl.requestTimeline());
       // App.f().show();

       // Stage s = (Stage) ShowSection.getScene().getWindow();

    }

    @FXML
    void ButtonCreatTweet(ActionEvent event) throws IOException {

        App.setScene(FXMLLoader.load(getClass().getResource("AddTweet.fxml")));


    }

    @FXML
    void ButtonShowMyPage(ActionEvent event) throws IOException {
        showPage(null);


    }

    public void showPage(String username) throws IOException {
        ShowSection.setContent(fxmlCommandParserServiceImpl.getPageInformation(username));
    }
    public void showFollowers() throws IOException {
        ShowSection.setContent(fxmlCommandParserServiceImpl.showFollowers());
    }
    public void showFollowings() throws IOException {
        ShowSection.setContent(fxmlCommandParserServiceImpl.showFollowings());
    }

    @FXML
    void ButtonTimeLine(ActionEvent event) throws IOException {


        ShowSection.setContent(fxmlCommandParserServiceImpl.requestTimeline());

       //ShowSection.getChildrenUnmodifiable().get(0);
       // ShowSection.getChildrenUnmodifiable().add(fxmlCommandParserServiceImpl.requestTimeline());

    }


    @FXML
    void ButtonSearch(ActionEvent event) {

    }


}
