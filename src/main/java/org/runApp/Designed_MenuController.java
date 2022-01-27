package org.runApp;

import com.google.gson.internal.LinkedTreeMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.TreeMap;

public class Designed_MenuController {
    FXMLCommandParserServiceImpl fxmlCommandParserServiceImpl =App.fxmlCommandParserService;

    @FXML
    private ScrollPane ShowSection;

    @FXML
    private MenuItem idDarkMode;

    @FXML
    private MenuItem idLightMode;

    @FXML
    private Button idButtonCreatTweet;

    @FXML
    private Button idButtonSearch;

    @FXML
    private Button idButtonShowPage;


    @FXML
    private Button idButtonTimeLine;

    public void initialize() throws IOException {
      //  ShowSection.getChildrenUnmodifiable().add(fxmlCommandParserServiceImpl.requestTimeline());

        //ShowSection.getChildrenUnmodifiable()
        ShowSection.setContent(fxmlCommandParserServiceImpl.requestTimeline());
        setSaveAccelerator(idButtonTimeLine);
        setSaveAccelerator1(idButtonShowPage);
        setSaveAccelerator2(idButtonSearch);
        setSaveAccelerator3(idButtonCreatTweet);


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



    @FXML
    void ButtonHelp(ActionEvent event) {
        Label label = new Label("Log in : You can enter by pressing this key  ");
        Label label1 = new Label("sign up : register");
        Label label2= new Label("exit :Exit the application");
        Label label3 = new Label("Like : Favorite a tweet ");
        Label label4 = new Label("Retweet : Repost a tweet ");
        Label label5 = new Label("follow : follow a person and see their tweets ");
        Label label6 = new Label("un follow : un follow a person ");
        Label label7 = new Label("Open time line : ctrl + s");

        Label label8 = new Label("Open page : ctrl + n");

        Label label9 = new Label("Open search : ctrl + p");
        Label label10 = new Label("Creat tweet  : ctrl +z");


        VBox vBox = new VBox();
        vBox.getChildren().addAll(label,label1,label2,label3,label4,label5,label6,label7,label8,label9,label10);

        Scene scene = new Scene(vBox);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void ButtonAbout(ActionEvent event) {

        Label label = new Label( " Mohammad hadi Shekholeslami : 9931097 , hafezsheikh@gmail.com");
        Label label1 = new Label("Rezvan Afari : 9827003 , afaringhazal@gmail.com ");
        VBox vBox = new VBox();
        vBox.getChildren().add(label);
        vBox.getChildren().add(label1);

        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        vBox.setAlignment(Pos.TOP_CENTER);



        Scene scene = new Scene(vBox);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void fullScreen(ActionEvent event) {

        App.f().setFullScreen(!App.f().isFullScreen());

    }

    @FXML
    void DarkMode(ActionEvent event) {

        Scene scene = App.f().getScene();
        scene.getRoot().setStyle("-fx-base:black");

    }

    @FXML
    void LightMode(ActionEvent event) {
        Scene scene = App.f().getScene();
        scene.getRoot().setStyle("");
    }

//    @FXML
//    void ButtonTimeLine(KeyEvent event) {
//
//        if(event.getText().equals('a')){
//            System.out.println("----");
//
//        }
//
//
//        App.f().getScene().setOnKeyPressed(e -> {
//            if (e.getCode() == KeyCode.A) {
//                System.out.println("A key was pressed");
//            }
//        });
//
//    }
//
//
//    @FXML
//    private void initialize() throws SQLException, ClassNotFoundException, IOException {
//        setSaveAccelerator(idButtonTimeLine);
//    }

//
    private void setSaveAccelerator(final Button button) {
        Scene scene = App.f().getScene();
        if (scene == null) {
            throw new IllegalArgumentException("setSaveAccelerator must be called when a button is attached to a scene");
        }

        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN),
                new Runnable() {
                    @FXML public void run() {
                        button.fire();
                    }
                }
        );
    }

    private void setSaveAccelerator1(final Button button) {
        Scene scene = App.f().getScene();
        if (scene == null) {
            throw new IllegalArgumentException("setSaveAccelerator must be called when a button is attached to a scene");
        }

        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN),
                new Runnable() {
                    @FXML public void run() {
                        button.fire();
                    }
                }
        );
    }
    private void setSaveAccelerator2(final Button button) {
        Scene scene = App.f().getScene();
        if (scene == null) {
            throw new IllegalArgumentException("setSaveAccelerator must be called when a button is attached to a scene");
        }

        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.P, KeyCombination.SHORTCUT_DOWN),
                new Runnable() {
                    @FXML public void run() {
                        button.fire();
                    }
                }
        );
    }
    private void setSaveAccelerator3(final Button button) {
        Scene scene = App.f().getScene();
        if (scene == null) {
            throw new IllegalArgumentException("setSaveAccelerator must be called when a button is attached to a scene");
        }

        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.Z, KeyCombination.SHORTCUT_DOWN),
                new Runnable() {
                    @FXML public void run() {
                        button.fire();
                    }
                }
        );
    }
}
