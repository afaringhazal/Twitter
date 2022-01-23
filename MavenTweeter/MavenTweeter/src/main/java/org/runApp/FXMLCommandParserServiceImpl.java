package org.runApp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.ce.ap.Request;
import org.ce.ap.Response;
import org.ce.ap.client.ConnectionService;
import org.ce.ap.client.ConsoleViewService;
import org.ce.ap.impl.client.ConnectionServiceImpl;
import org.ce.ap.impl.client.ConsoleViewServiceImpl;
import org.ce.ap.server.Server;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class FXMLCommandParserServiceImpl {
//}
//package Twitter;
//
//        import com.google.gson.Gson;
//        import com.google.gson.GsonBuilder;
//        import com.google.gson.internal.LinkedTreeMap;
//        import javafx.scene.Group;
//        import javafx.scene.Parent;
//        import javafx.scene.Scene;
//        import javafx.scene.control.Button;
//        import javafx.scene.control.TextField;
//        import javafx.scene.layout.BorderPane;
//        import javafx.scene.layout.HBox;
//        import javafx.scene.layout.VBox;
//        import org.ce.ap.Request;
//        import org.ce.ap.Response;
//        import org.ce.ap.client.CommandParserService;
//        import org.ce.ap.client.ConnectionService;
//        import org.ce.ap.client.ConsoleViewService;
//        import org.ce.ap.impl.client.ConnectionServiceImpl;
//        import org.ce.ap.impl.client.ConsoleViewServiceImpl;
//        import org.ce.ap.server.Server;
//
//        import java.io.IOException;
//        import java.time.LocalDate;
//        import java.time.LocalDateTime;
//        import java.util.ArrayList;
//        import java.util.Scanner;
//
//
////package org.ce.ap.impl.client;
//        import com.google.gson.Gson;
//        import com.google.gson.GsonBuilder;
//        import org.ce.ap.Request;
//        import org.ce.ap.Response;
//        import org.ce.ap.client.CommandParserService;
//        import org.ce.ap.client.ConnectionService;
//        import org.ce.ap.client.ConsoleViewService;
//        import org.ce.ap.server.Server;
//        import java.io.IOException;
//        import java.time.LocalDate;
//        import java.time.LocalDateTime;
//        import java.util.ArrayList;
//        import java.util.Scanner;
///**
// * class : The type Command parser service.
// * @author MohammadHdi sheikhEslami
// * @author Rezvan Afari
// * @version 1.0.0
// */
//public class FXMLCommandParserServiceImpl {

    /**
     * The Gson.
     */
    Gson gson;
    /**
     * The Response.
     */
    Response response = null;
    /**
     * The Request.
     */
    Request request = new Request("", null);
    /**
     * The Should run.
     */
    boolean shouldRun = false;
    /**
     * The Scanner.
     */
    Scanner scanner = new Scanner(System.in);
    private ConnectionService connectionService;
    private ConsoleViewService consoleViewService = new ConsoleViewServiceImpl();


    public static void main(String[] args) {
        new FXMLCommandParserServiceImpl();
    }
    /**
     * Instantiates a new Command parser service.
     */
    public FXMLCommandParserServiceImpl() {
        fixGson();
       connectionService = new ConnectionServiceImpl();

//        if (connectionService.socketIsConnected()) {
//            shouldRun = true;
//        }
//        while (shouldRun) {
//            System.out.println("1-Sign in\n2-Sign up\n3-Exit");
//            try {
//
//                int n = Integer.parseInt(scanner.nextLine());
//
//                if (n == 1) {
//                    processSignIn();
//
//
//                } else if (n == 2) {
//                    processSignUp();
//
//
//                } else if (n == 3) {
//
//                    shouldRun = false;
//                    connectionService.stop();
//                } else {
//                    System.out.println("Invalid number!");
//                }
//            } catch (Exception e) {
//                System.out.println("Invalid input\nPlease try again!");
//            }
//        }


    }


//    public void handleConnectionServiceImpl(ConnectionServiceImpl connectionServiceImpl){
//        this.connectionService =  connectionServiceImpl;
//
//    }
//
//    /**
//     * Show main menu.
//     *
//     * @throws IOException            the io exception
//     * @throws ClassNotFoundException the class not found exception
//     */
//    @Override
//    public void showMainMenu() throws IOException, ClassNotFoundException {
//        while (true) {
//            int n = 0;
//
//            System.out.println("1-Add tweet\n2-timeline\n3-My Page\n4-Sign Out");
//            try {
//                n = Integer.parseInt(scanner.nextLine());
//
//            } catch (Exception e) {
//
//                System.out.println("Invalid number\nplease again!");
//                continue;
//            }
//            if (n == 1) {
//                addTweet();
//            } else if (n == 2) {
//                requestTimeline();
//
//            } else if (n == 3) {
//                showPageMenu();
//
//            } else if (n == 4) {
//                break;
//            }
//
//
//        }
//
//
//    }


    public void refreshRequest() {
        request = new Request("", null);
    }


    public void processSignIn(String username,String password){   // throws IOException, ClassNotFoundException {

        ArrayList<Object> parameterValue = new ArrayList<>();
        parameterValue.add(username);
        parameterValue.add(password);
        sendRequestAndListenForResponse("Sign In", parameterValue);
        if (response == null || response.isHasError()) {
            System.out.println("couldn't sign in.");
            return;
        }
        System.out.println("Successfully signed in.");
        // showMainMenu();

    }
/*
    /**
     * Process sign up.
     *
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception

    @Override
    public void processSignUp() throws IOException, ClassNotFoundException {
        System.out.println("Enter UserName and password: ");
        String userName = scanner.nextLine();
        String password = scanner.nextLine();
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(userName);
        parameters.add(password);
        sendRequestAndListenForResponse("Sign Up", parameters);
        if (response == null)
            return;
        else if (response.isHasError()) {
            System.out.println("Username already taken.\n Please try again.");
            return;
        }
        System.out.println("Username is allowed. Enter details in separate lines as follows: ");
        ArrayList<Object> signUpParameters = new ArrayList<>();

        String firstName;
        String lastName;
        String year;
        String month ;
        String day;
        String id ;
        String bio;
        while (true) {
            System.out.println("FirstName , Last name, Birthday(year,month,day each in a separate line),Page Id,Bio.");
            firstName = scanner.nextLine();
            lastName = scanner.nextLine();
            year = scanner.nextLine();
            month = scanner.nextLine();
            day = scanner.nextLine();
            id = scanner.nextLine();
            bio = scanner.nextLine();


            try {
                int d = Integer.parseInt(day);
                int m = Integer.parseInt(month);
                int y = Integer.parseInt(year);

                if (d > 31 || m > 12 || d < 0 || m < 0 || y < 0)
                    throw new RuntimeException();
            } catch (Exception e) {
                System.out.println("Invalid number!");
                continue;
            }
            break;
        }
        signUpParameters.add(firstName);
        signUpParameters.add(lastName);
        signUpParameters.add(year);
        signUpParameters.add(month);
        signUpParameters.add(day);
        signUpParameters.add(id);
        signUpParameters.add(bio);
        sendRequestAndListenForResponse("Sign Up Details", signUpParameters);
        if (response == null || response.isHasError())
            return;
        System.out.println("Sign up successful.");
        showMainMenu();


    }

    /**
     * Add tweet.
     */
    /*
    @Override
    public void addTweet() {
        System.out.println("Please enter the tweet text.\nTweets should not have more than 256 characters.\nWhen finished enter -1 as last line.");
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String s = scanner.nextLine();
            if (s.equals("-1")) {
                break;
            }
            stringBuilder.append(s);

        }


        if (stringBuilder.toString().isBlank() || stringBuilder.toString().trim().length() > 256) {
            System.out.println("Tweets should have no more than 256 characters and should not be empty.\n Please try again.");
            return;

        }

        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(stringBuilder.toString());
        sendRequestAndListenForResponse("Add Tweet", parameters);
        if (response == null || response.isHasError()) {
            return;
        }
        System.out.println("Your tweet was successfully added to server.");


    }

    /**
     * Request timeline.
     */

    // @Override


    public VBox requestTimeline() throws IOException {

        sendRequestAndListenForResponse("Get Timeline", null);
        if (response == null || response.isHasError())
            return null;
        VBox vBoxShowAllTweet = new VBox();
        for (Object obj : response.getResults()) {

            int check = 0;
            //Parent p ;
            Parent node = null;
            LinkedTreeMap<String, Object> treeMap = (LinkedTreeMap<String, Object>) obj;
            for (String s : treeMap.keySet()) {
                if (s.equals("tweet")) {
                     node = FXMLLoader.load(getClass().getResource("ReTweetMessage.fxml"));
                    check =1;

                }
            }
            if(check==0) {
                 node = FXMLLoader.load(getClass().getResource("Message.fxml"));
            }



            HBox hBox = (HBox) node.getChildrenUnmodifiable().get(0);




            TextField textField = (TextField) hBox.getChildren().get(0);
                    //(TextField) node.getChildrenUnmodifiable().get(1);
            textField.setText(""+treeMap.get("clientUsername"));

            textField = (TextField) hBox.getChildren().get(1);
                    // (TextField) node.getChildrenUnmodifiable().get(2);
            textField.setText(treeMap.get("id")+"");


            textField = (TextField) hBox.getChildren().get(2);
                    //(TextField) node.getChildrenUnmodifiable().get(3);
            textField.setText(treeMap.get("date")+"");

            //   textField222.setText(treeMap.get("clientUsername") + "          id : " + treeMap.get("id") + "          " + treeMap.get("date"));

            int i=0;
            for (String s : treeMap.keySet()) {
                if (s.equals("tweet")) {
                    HBox hBox1 = (HBox) node.getChildrenUnmodifiable().get(1);

                    LinkedTreeMap<String, Object> treeMapToRetweet = new LinkedTreeMap<>();
                    treeMapToRetweet = (LinkedTreeMap<String, Object>) treeMap.get("tweet");

                    textField = (TextField) hBox1.getChildren().get(0);
                            //(TextField) node.getChildrenUnmodifiable().get(5);
                    textField.setText(""+treeMapToRetweet.get("clientUsername"));

                    textField = (TextField) hBox1.getChildren().get(1);
                    textField.setText(""+ treeMapToRetweet.get("id"));

                    textField =(TextField) hBox1.getChildren().get(2);
                    textField.setText(treeMapToRetweet.get("date")+"");

                    //textField.setText("        "+treeMapToRetweet.get("clientUsername") + "          id : " + treeMapToRetweet.get("id") + "          " + treeMapToRetweet.get("date"));
                    textField = (TextField) node.getChildrenUnmodifiable().get(2);
                    textField.setText(""+treeMapToRetweet.get("text"));
                    textField = (TextField) node.getChildrenUnmodifiable().get(3);
                    textField.setText("        " + ((ArrayList<Object>) treeMapToRetweet.get("retweets")).size() + " Retweets, " + ((ArrayList<String>) treeMapToRetweet.get("likes")).size() + " Likes");
                    i+=3;
                }

            }
            TextField textField11 = (TextField) node.getChildrenUnmodifiable().get(i+1);
            textField11.setText(""+treeMap.get("text"));
            TextField textField12 = (TextField) node.getChildrenUnmodifiable().get(i+2);
            textField12.setText(""+((ArrayList<Object>) treeMap.get("retweets")).size() + " Retweets, " + ((ArrayList<String>) treeMap.get("likes")).size() + " Likes");
            vBoxShowAllTweet.getChildren().add(node);
        }

        return vBoxShowAllTweet;

    }
/*
    /**
     * Show page menu.
     *
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
   /*
   @Override
    public void showPageMenu() {

        while (true) {
            DisplayPageInformation();
            System.out.println();
            int n = 0;
            System.out.println("1-Followers and followings\n2-Add Tweet\n3-my Tweets\n4-Likes\n5-Edit profile\n6-exit");
            try {
                n = Integer.parseInt(scanner.nextLine());

            } catch (Exception e) {
                System.out.println("Invalid number\nplease again!");
                continue;
            }

            if (n == 1) {
                followersAndFollowingsMenu();
            } else if (n == 2) {
                addTweet();
            } else if (n == 3) {
                myTweet();
            } else if (n == 4) {
                myFavoriteTweets();
            } else if (n == 5) {
                editProfile();

            } else if (n == 6) {
                break;
            }
        }
    }

    /**
     * Follow.
     *
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    /*
    @Override
    public void follow() {

        sendRequestAndListenForResponse("Show AllUsernames", null);
        if (response == null || response.isHasError()) {
            return;
        }
        consoleViewService.printList(response);
        System.out.println("How many people do you want to follow now?");
        ArrayList<Object> result = new ArrayList<>();
        int num = 0;
        while (true) {
            try {
                num = Integer.parseInt(scanner.nextLine());
                if (num > response.getResults().size())
                    throw new RuntimeException();
                break;

            } catch (Exception e) {
                System.out.println("Invalid number!please try again");
                continue;
            }

        }

        for (int i = 0; i < num; i++) {
            System.out.println("Enter username: ");
            String userName = scanner.nextLine();
            result.add(userName);
        }

        sendRequestAndListenForResponse("follow", result);
        if (response == null) {
            return;
        }
        for (int i = 0; i < response.getResults().size(); i++) {
            if (response.getResults().get(i).equals(true))
                System.out.println("successful  : " + result.get(i));
            else
                System.out.println("failed : " + result.get(i));
        }


    }

    /**
     * Unfollow.
     */
    /*
    @Override
    public void unfollow() {
        System.out.println("Enter user name :");
        String username = scanner.nextLine();
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(username);
        sendRequestAndListenForResponse("unfollow", parameters);
        if (response == null || response.isHasError()) {
            return;
        }
        System.out.println("You successfully unfollowed " + username + " .");

    }

    /**
     * Delete follower.
     */
    /*
    @Override
    public void deleteFollower() {
        System.out.println("Enter user name :");
        String use = scanner.nextLine();
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(use);
        sendRequestAndListenForResponse("Delete Follower", parameters);
        if (response == null || response.isHasError()) {
            return;
        }
        System.out.println("You successfully Deleted follower: " + use + " .");
    }

    /**
     * Delete tweet.
     */
    /*
    public void deleteTweet() {
        System.out.println("Enter tweet id: ");
        String id = scanner.nextLine();
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(id);
        sendRequestAndListenForResponse("Delete Tweet", parameters);
        if (response == null || response.isHasError()) {
            return;
        }
        System.out.println("You successfully Deleted tweet with id " + id + " .");
    }

    /**
     * Show followers and following.
     *
     * @throws IOException the io exception
     */
    /*
    @Override
    public void showFollowersAndFollowing() {
        sendRequestAndListenForResponse("showFollowersAndFollowings", null);
        if (response == null || response.isHasError())
            return;
        consoleViewService.printFollowersAndFollowings(response);

    }

    /**
     * Followers and followings menu.
     *
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    /*
    @Override
    public void followersAndFollowingsMenu() {
        while (true) {
            showFollowersAndFollowing();
            System.out.println();
            int number = 0;
            System.out.println("1-Follow\n2-Unfollow\n3-remove follower\n4-exit");
            try {
                number = Integer.parseInt(scanner.nextLine());

            } catch (Exception e) {
                System.out.println("Invalid number!");
                continue;
            }

            if (number == 1) {
                follow();
            } else if (number == 2) {
                unfollow();
            } else if (number == 3) {
                deleteFollower();
            } else if (number == 4) {
                break;
            } else {
                System.out.println("Invalid number!\nplease again.");
            }


        }

    }

    @Override
    public void myTweet() {
        sendRequestAndListenForResponse("myTweet", null);
        if (response == null || response.isHasError())
            return;
        consoleViewService.printAllTweets(response);
        while (true) {
            int number = 0;
            System.out.println("1-delete tweet\n2-back");
            try {
                number = Integer.parseInt(scanner.nextLine());

            } catch (Exception e) {
                System.out.println("Invalid number!");
                continue;
            }

            if (number == 1) {
                deleteTweet();
            } else if (number == 2) {
                break;
            } else {
                System.out.println("Invalid number!\nplease again.");
            }

        }


    }

    @Override
    public void myFavoriteTweets() {

        sendRequestAndListenForResponse("myFavoriteTweets", null);
        if (response == null || response.isHasError())
            return;
        consoleViewService.printAllTweets(response);

    }

    @Override
    public void DisplayPageInformation() {
        sendRequestAndListenForResponse("Display Page Information", null);
        if (response == null || response.isHasError())
            return;
        consoleViewService.printList(response);

    }

    /**
     * Request to like.
     */


    public void requestToLike(String idToLike ) throws IOException {

        System.out.println("The id to like is "+idToLike);

//        while (true){
//            idToLike = scanner.nextLine();
//            try {
//                int check = Integer.parseInt(idToLike);
//            }
//            catch (Exception e)
//            {
//                System.out.println("Invalid input!\nplease try again");
//                continue;
//            }
//            break;
//        }

        //String idToLike = scanner.nextLine();
        ArrayList<Object> requestLike = new ArrayList<>();
        requestLike.add(idToLike);
        sendRequestAndListenForResponse("LikeMessage", requestLike);
        if (response == null || response.isHasError()) {
            System.out.println("we can't like message");

            return;

        }
       // requestTimeline();
        //System.out.println("You successfully liked message with id: " + idToLike + " .");
    }

    /**
     * Send request and listen for response.
     *
     * @param title           the title
     * @param parameterValues the parameter values
     */

    //  @Override
    public void sendRequestAndListenForResponse(String title, ArrayList<Object> parameterValues) {
        if (!connectionService.socketIsConnected()) {
            shouldRun = false;
            try {
                connectionService.stop();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        request.setTitle(title);
        request.setParameterValue(parameterValues);
        try {
            connectionService.sendToServer(gson.toJson(request));
            refreshRequest();
        } catch (IOException e) {
            System.out.println("couldn't send request:" + title + " to server.");
            response = null;
        }

        try {
            response = gson.fromJson(connectionService.receiveFromServer(), Response.class);
            if (response.isHasError()) {
                System.out.println("server declined  request : " + title + "\nPlease  try again.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    // @Override
    public void fixGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new Server.LocalDateSerializer());

        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new Server.LocalDateTimeSerializer());

        gsonBuilder.registerTypeAdapter(LocalDate.class, new Server.LocalDateDeserializer());

        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new Server.LocalDateTimeDeserializer());

        gson = gsonBuilder.setPrettyPrinting().create();

    }
/*

    public void showTimelineMenu() {
        while (true) {
            System.out.println("1-Like\n2-Retweet-\n3-back");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());

            }catch (Exception e){
                System.out.println("Invalid number\nplease try again.");
            }
            if (choice == 1) {
                requestToLike();

            } else if (choice == 2) {
                requestToRetweet();

            } else if (choice == 3) {
                return;
            } else {
                System.out.println("invalid.");
            }
        }
    }

    /**
     * Request to retweet.
     */
    /*
    @Override
    public void requestToRetweet() {
        System.out.println("Enter id Message : ");
        String idToRetweet ;//= scanner.nextLine();

        while (true){
            idToRetweet = scanner.nextLine();

            try {
                int check = Integer.parseInt(idToRetweet);
            }catch (Exception e)
            {
                System.out.println("Invalid input!\nplease try again!");
                continue;
            }
            break;
        }

        System.out.println("Enter text(Quote Tweet) : ");
        String TextToRetweet = scanner.nextLine();
        ArrayList<Object> requestRetweet = new ArrayList<>();
        requestRetweet.add(idToRetweet);
        requestRetweet.add(TextToRetweet);
        sendRequestAndListenForResponse("RetweetMessage", requestRetweet);
        if (response == null || response.isHasError()) {
            System.out.println("Retweet failed. Please try again.");
            return;
        }

        System.out.println("successfully retweeted.!");
    }

    @Override
    public void editProfile() {
        DisplayPageInformation();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1-Re_enter page details\n2-Back");
            int n = Integer.parseInt(scanner.nextLine());

            if (n == 1) {
                System.out.println("Enter Firstname , Lastname , year , month , day , id , bio : ");
                String firstName = scanner.nextLine();
                String lastName = scanner.nextLine();
                String year = scanner.nextLine();
                String month = scanner.nextLine();
                String day = scanner.nextLine();
                String id = scanner.nextLine();
                String bio = scanner.nextLine();
                ArrayList<Object> signUpParameters = new ArrayList<>();
                signUpParameters.add(firstName);
                signUpParameters.add(lastName);
                signUpParameters.add(year);
                signUpParameters.add(month);
                signUpParameters.add(day);
                signUpParameters.add(id);
                signUpParameters.add(bio);
                try {
                    int d = Integer.parseInt(day);
                    int m = Integer.parseInt(month);
                    int y = Integer.parseInt(year);

                    if (d > 31 || m > 12 || d < 0 || m < 0 || y < 0)
                        throw new RuntimeException();
                } catch (Exception e) {
                    System.out.println("Invalid number!");
                    return;
                }
                sendRequestAndListenForResponse("Edit Profile", signUpParameters);
                if (response == null || response.isHasError())
                    return;
                System.out.println("Profile edit successful.");


            } else if (n == 2) {
                break;

            } else {

                System.out.println("Invalid number!\nplease again.");
            }
        }

    }
    */
}