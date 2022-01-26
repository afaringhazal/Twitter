package org.runApp;

import com.google.gson.internal.LinkedTreeMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import org.ce.ap.ExceptionNotValidInput;
import java.io.IOException;
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

        fxmlCommandParserServiceImpl.menuController.showFollowers(username.getText());

    }

    @FXML
    void showFollowings(MouseEvent event) throws IOException {

        fxmlCommandParserServiceImpl.menuController.showFollowings(username.getText());

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
    void ButtonOfFollowAndUnFollow(ActionEvent event) throws IOException {
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

    private void follow() throws IOException {
        fxmlCommandParserServiceImpl.followFromPage(username.getText());
    }

    private void unfollow() throws IOException {
        fxmlCommandParserServiceImpl.unfollowFromPage(username.getText());
    }

    private void editPage() throws IOException {
        Parent node = FXMLLoader.load(getClass().getResource("EditPage.fxml"));
        App.setScene(node);

    }

    @FXML
    void ButtonOfChangePicture(MouseEvent event) throws ExceptionNotValidInput {

    }

    public void getDataPart2(ArrayList<Object> results) throws IOException {

        VBox vBoxShowAllTweet = new VBox();
        for (Object obj : results) {

            int check = 0;
            Parent node = null ;
            LinkedTreeMap<String, Object> treeMap = (LinkedTreeMap<String, Object>) obj;
            for (String s : treeMap.keySet()) {
                if (s.equals("tweet")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("New Retweet.fxml"));
                    node = fxmlLoader.load();
                    Button b =(Button) node.getChildrenUnmodifiable().get(10);
                    b.setVisible(true);
                    NewRetweetController newRetweetController = fxmlLoader.getController();
                    newRetweetController.getData(treeMap);

                    check =1;

                }
            }
            if(check==0) {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("New Tweet.fxml"));
                node = fxmlLoader.load();
                Button b =(Button) node.getChildrenUnmodifiable().get(9);
                b.setVisible(true);
                NewTweetController newTweetController = fxmlLoader.getController();
                newTweetController.getData(treeMap);

            }
            vBoxShowAllTweet.getChildren().add(node);
        }

        Tweets.getChildren().add(vBoxShowAllTweet);



    }


}