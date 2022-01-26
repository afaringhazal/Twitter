package org.runApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.ce.ap.ExceptionNotValidInput;
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
    private Button Button;


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
    private Circle Picture;

    @FXML
    void ShowFollowers(MouseEvent event) throws IOException {

        //fxmlCommandParserServiceImpl.showFollowers()

        fxmlCommandParserServiceImpl.menuController.showFollowers();

    }

    @FXML
    void showFollowings(MouseEvent event) throws IOException {

        fxmlCommandParserServiceImpl.menuController.showFollowings();

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
        String buttonText="Edit Page";
        if (status.equals("Followed")) {
            buttonText = "Unfollow";
        }
        else if (status.equals("NotFollowed")) {
            buttonText = "Follow";
        }

        Button.setText(buttonText);
    }


    @FXML
    void ButtonOfFollowAndUnFollow(ActionEvent event) {
        switch (status){
            case "OwnPage":
                editPage();
                break;
            case "Followed":
                unfollow();
                break;
            case "NotFollowed":
                follow();
                break;
        }


    }

    private void follow(){

    }

    private void unfollow(){

    }

    private void editPage(){}

    @FXML
    void ButtonOfChangePicture(MouseEvent event) throws ExceptionNotValidInput {
        String s ="C:/Users/ASUS/Desktop/v.png";
        System.out.println(s);


       // Image image = new Image("C:/Users/ASUS/Desktop/v.png");
      //  System.out.println("-----");


        //fxmlCommandParserServiceImpl.requestSetImageInPage(s);
      // Image image1 = fxmlCommandParserServiceImpl.requestGetImageFromPage();


        //Picture.setFill(new ImagePattern(image1));


    }

}