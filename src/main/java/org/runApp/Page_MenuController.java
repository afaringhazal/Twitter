package org.runApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.runApp.App;
import org.runApp.FXMLCommandParserServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Page_MenuController {
    FXMLCommandParserServiceImpl fxmlCommandParserServiceImpl = App.fxmlCommandParserService ;



    private String status =null;


    private ArrayList<String> followingsList;
    private ArrayList<String> followersList;

    @FXML
    private Label username;

    @FXML
    private Label Followers;

    @FXML
    private Label Followings;
    @FXML
    private Label Name;

    @FXML
    private Label ID;


    @FXML
    private Label BirthDay;

    @FXML
    private Label joinDate;

    @FXML
    private Label Bio;


    @FXML
    private VBox Tweets;


    @FXML
    void ShowFollowers(MouseEvent event) {

    }

    @FXML
    void showFollowings(MouseEvent event) {

    }
    void getData(ArrayList<Object> response) throws IOException {

        String  username= (String) response.get(0);
        String  firstName= (String) response.get(1);
        String  lastName= (String) response.get(2);
        String birthDay= (String) response.get(3);
        String  bio= (String) response.get(4);
        String  Id= (String) response.get(5);
        String  joinDate= (String) response.get(6);
        String  status= (String) response.get(7);
        ArrayList<String> followers = (ArrayList<String>) response.get(8);
        ArrayList<String> followings = (ArrayList<String>) response.get(9);
        this.username.setText(username);
        this.Name.setText("name : "+firstName+" "+lastName);

        BirthDay.setText("birthday : "+birthDay);
        Bio.setText(bio);
        this.joinDate.setText("join date : "+joinDate);
        ID.setText("@"+Id);
        this.status=status;
        followersList=followers;
        followingsList=followings;
        Followers.setText("followers: "+String.valueOf(followers.size()));
        Followings.setText("followings: "+String.valueOf(followings.size()));


    }

    @FXML
    void ButtonOfFollowAndUnFollow(ActionEvent event) {

    }



}