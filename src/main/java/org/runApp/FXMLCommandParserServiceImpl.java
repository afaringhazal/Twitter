package org.runApp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.ce.ap.ExceptionNoConnection;
import org.ce.ap.ExceptionNotValidInput;
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
    public Designed_MenuController menuController=null;

    public static void main(String[] args) {
        new FXMLCommandParserServiceImpl();
    }
    /**
     * Instantiates a new Command parser service.
     */
    public FXMLCommandParserServiceImpl() {
        fixGson();
        connectionService = new ConnectionServiceImpl();
    }


    public void refreshRequest() {
        request = new Request("", null);
    }


    public void processSignIn(String username,String password) throws RuntimeException{   // throws IOException, ClassNotFoundException {

        ArrayList<Object> parameterValue = new ArrayList<>();
        parameterValue.add(username);
        parameterValue.add(password);
        sendRequestAndListenForResponse("Sign In", parameterValue);
        if (response == null || response.isHasError()) {
            System.out.println("couldn't sign in.");
            throw new RuntimeException();
        }
        System.out.println("Successfully signed in.");
        // showMainMenu();

    }

    /**
     * Process sign up.
     *
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */

    public void processSignUp(String userName, String password ,String firstName,
                              String lastName,
                              String year,
                              String month,
                              String day,
                              String id ,
                              String bio) throws ExceptionNotValidInput, ExceptionNoConnection {
//        System.out.println("Enter UserName and password: ");
//        String userName = scanner.nextLine();
//        String password = scanner.nextLine();
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(userName);
        parameters.add(password);
        sendRequestAndListenForResponse("Sign Up", parameters);
        if (response == null)
            throw new ExceptionNotValidInput();
        else if (response.isHasError()) {
            System.out.println("Username already taken.\n Please try again.");
            throw new ExceptionNotValidInput();
        }

        System.out.println("Username is allowed. Enter details in separate lines as follows: ");
        ArrayList<Object> signUpParameters = new ArrayList<>();
//
//        String firstName;
//        String lastName;
//        String year;
//        String month ;
//        String day;
//        String id ;
//        String bio;
//        while (true) {
//            System.out.println("FirstName , Last name, Birthday(year,month,day each in a separate line),Page Id,Bio.");
//            firstName = scanner.nextLine();
//            lastName = scanner.nextLine();
//            year = scanner.nextLine();
//            month = scanner.nextLine();
//            day = scanner.nextLine();
//            id = scanner.nextLine();
//            bio = scanner.nextLine();
//
//
//            try {
//                int d = Integer.parseInt(day);
//                int m = Integer.parseInt(month);
//                int y = Integer.parseInt(year);
//
//                if (d > 31 || m > 12 || d < 0 || m < 0 || y < 0)
//                    throw new RuntimeException();
//            } catch (Exception e) {
//                System.out.println("Invalid number!");
//                continue;
//            }
//            break;
//        }
        signUpParameters.add(firstName);
        signUpParameters.add(lastName);
        signUpParameters.add(year);
        signUpParameters.add(month);
        signUpParameters.add(day);
        signUpParameters.add(id);
        signUpParameters.add(bio);
        sendRequestAndListenForResponse("Sign Up Details", signUpParameters);
        if (response == null || response.isHasError())
            throw new ExceptionNoConnection();
        System.out.println("Sign up successful.");
    }

    /**
     * Add tweet.
     */

    public String addTweet(String text) {
//        System.out.println("Please enter the tweet text.\nTweets should not have more than 256 characters.\nWhen finished enter -1 as last line.");
//        StringBuilder stringBuilder = new StringBuilder();
//        while (true) {
//            String s = scanner.nextLine();
//            if (s.equals("-1")) {
//                break;
//            }
//            stringBuilder.append(s);
//
//        }


        if (text.isBlank() || text.trim().length() > 256) {
            // System.out.println("Tweets should have no more than 256 characters and should not be empty.\n Please try again.");

            return "Tweets should have no more than 256 characters and should not be empty.\n Please try again.";

        }

        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(text);
        sendRequestAndListenForResponse("Add Tweet", parameters);
        if (response == null || response.isHasError()) {
            return "we can't sent tweet.";
        }
        System.out.println("Your tweet was successfully added to server.");

        return "" ;

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
                Parent node = null ;
                LinkedTreeMap<String, Object> treeMap = (LinkedTreeMap<String, Object>) obj;
                for (String s : treeMap.keySet()) {
                    if (s.equals("tweet")) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("New Retweet.fxml"));
                        node = fxmlLoader.load();
                        NewRetweetController newRetweetController = fxmlLoader.getController();
                        newRetweetController.getData(treeMap);

                        check =1;

                    }
                }
                if(check==0) {

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("New Tweet.fxml"));
                    node = fxmlLoader.load();
                    NewTweetController newTweetController = fxmlLoader.getController();
                    newTweetController.getData(treeMap);

                }
                vBoxShowAllTweet.getChildren().add(node);
            }

            return vBoxShowAllTweet;

        }


    public Parent getPageInformation(String ClientUsername) throws IOException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(ClientUsername);
        sendRequestAndListenForResponse("Get Page Info", parameters);
        if (response == null || response.isHasError()) {
            return null;

        }


        FXMLLoader loader = new FXMLLoader(getClass().getResource("Page_Menu.fxml"));
        Parent root = loader.load();
        Page_MenuController pageMenuController = loader.getController();
        pageMenuController.getData(response.getResults());


        return root;
    }

    public void requestSetImageInPage(String s) throws ExceptionNotValidInput {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(s);
        sendRequestAndListenForResponse("Set Image", parameters);
        if (response == null || response.isHasError()) {
            throw new ExceptionNotValidInput();
        }

    }


    public String requestGetImageFromPage() throws ExceptionNotValidInput {

        sendRequestAndListenForResponse("GetImageFromPage", null);
        if (response == null || response.isHasError()) {
            throw new ExceptionNotValidInput();
        }

        return (String) response.getResults().get(0);


    }


    public void DisplayPageInformation()  {
        sendRequestAndListenForResponse("Display Page Information", null);
        if (response == null || response.isHasError())
            return;




        //consoleViewService.printList(response);

    }

    /**
     * Request to like.
     */


    public void requestToLike(String idToLike ) throws IOException {

        System.out.println("The id to like is "+idToLike);

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


    public String requestToRetweet(String idToRetweet , String QuoteTweet ) {
//        System.out.println("Enter id Message : ");
//        String idToRetweet ;//= scanner.nextLine();

//        while (true){
//            idToRetweet = scanner.nextLine();
//
//            try {
//                int check = Integer.parseInt(idToRetweet);
//            }catch (Exception e)
//            {
//                System.out.println("Invalid input!\nplease try again!");
//                continue;
//            }
//            break;
//        }
//
//        System.out.println("Enter text(Quote Tweet) : ");
//        String TextToRetweet = scanner.nextLine();
        ArrayList<Object> requestRetweet = new ArrayList<>();
        requestRetweet.add(idToRetweet);
        requestRetweet.add(QuoteTweet);
        sendRequestAndListenForResponse("RetweetMessage", requestRetweet);
        if (response == null || response.isHasError()) {
            System.out.println("Retweet failed. Please try again.");
            return "we can't send retweet";
        }

        System.out.println("successfully retweeted.!");

        return null;
    }
/*
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