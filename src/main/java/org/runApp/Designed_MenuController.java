package org.runApp;

import com.google.gson.internal.LinkedTreeMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    public void showFollowers(String username) throws IOException {
        ShowSection.setContent(fxmlCommandParserServiceImpl.showFollowersOf(username));
    }
    public void showFollowings(String username) throws IOException {
        ShowSection.setContent(fxmlCommandParserServiceImpl.showFollowingsOf(username));
    }

    @FXML
    void ButtonTimeLine(ActionEvent event) throws IOException {


        ShowSection.setContent(fxmlCommandParserServiceImpl.requestTimeline());


    }


    @FXML
    void ButtonSearch(ActionEvent event) throws IOException {
    FXMLLoader loader= new FXMLLoader(App.class.getResource("searchBar.fxml"));

    ShowSection.setContent(loader.load());
    }

    @FXML
    void ButtonHideOnSystemTray(ActionEvent event) {

        App.f().hide();
    }
    @FXML
    void ButtonOfLogOut(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        //fxmlCommandParserServiceImpl.menuController=fxmlLoader.getController();
        fxmlCommandParserServiceImpl.connectionService.stop();
        //App.fxmlCommandParserService = new FXMLCommandParserServiceImpl();
       // App.setNewFxmlCommandParserService();
        App.setScene(root);

    }


}
