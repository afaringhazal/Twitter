package org.runApp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
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
    public ConnectionService connectionService;
    private ConsoleViewService consoleViewService = new ConsoleViewServiceImpl();
    public Designed_MenuController menuController = null;

    public static void main(String[] args) {
        new FXMLCommandParserServiceImpl();
    }

    /**
     * Instantiates a new Command parser service.
     */
    public FXMLCommandParserServiceImpl()  {
        fixGson();
        connectionService = new ConnectionServiceImpl();


    }


    public void followFromPage(String username) throws IOException {
        ArrayList<Object> result = new ArrayList<>();
        result.add(username);
        sendRequestAndListenForResponse("FollowFromPage", result);
        if (response.isHasError()) {
            System.out.println("Error");
        }
        menuController.showPage(username);

    }

    public void unfollowFromPage(String username) throws IOException {
        ArrayList<Object> result = new ArrayList<>();
        result.add(username);
        sendRequestAndListenForResponse("UnfollowFromPage", result);
        if (response.isHasError()) {
            System.out.println("Error");
        }
        menuController.showPage(username);

    }


    public void refreshRequest() {
        request = new Request("", null);
    }


    public void processSignIn(String username, String password) throws RuntimeException {   // throws IOException, ClassNotFoundException {

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

    public void processSignUp(String userName, String password, String firstName,
                              String lastName,
                              String year,
                              String month,
                              String day,
                              String id,
                              String bio) throws ExceptionNotValidInput, ExceptionNoConnection {

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

        if (text.isBlank() || text.trim().length() > 256) {

            return "Tweets should have no more than 256 characters and should not be empty.\n Please try again.";

        }

        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(text);
        sendRequestAndListenForResponse("Add Tweet", parameters);
        if (response == null || response.isHasError()) {
            return "we can't sent tweet.";
        }
        System.out.println("Your tweet was successfully added to server.");

        return "";

    }

    /**
     * Request timeline.
     */


    public VBox requestTimeline() throws IOException {

        sendRequestAndListenForResponse("Get Timeline", null);
        if (response == null || response.isHasError())
            return null;
        VBox vBoxShowAllTweet = new VBox();
        for (Object obj : response.getResults()) {

            int check = 0;
            Parent node = null;
            LinkedTreeMap<String, Object> treeMap = (LinkedTreeMap<String, Object>) obj;
            for (String s : treeMap.keySet()) {
                if (s.equals("tweet")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("New Retweet.fxml"));
                    node = fxmlLoader.load();
                    NewRetweetController newRetweetController = fxmlLoader.getController();
                    newRetweetController.getData(treeMap);

                    check = 1;

                }
            }
            if (check == 0) {

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
        if (response == null||  response.isHasError()) {
            return null;

        }


        FXMLLoader loader = new FXMLLoader(getClass().getResource("Page_Menu.fxml"));
        Parent root = loader.load();
        Page_MenuController pageMenuController = loader.getController();
        pageMenuController.getData(response.getResults());




        sendRequestAndListenForResponse("Get Tweet And Retweet for PageMenu", parameters);
        if (response == null||  response.isHasError()) {
            return null;

        }
        pageMenuController.getDataPart2(response.getResults());


        return root;
    }

//    public void requestSetImageInPage(String s) throws ExceptionNotValidInput {
//        ArrayList<Object> parameters = new ArrayList<>();
//        parameters.add(s);
//        sendRequestAndListenForResponse("Set Image", parameters);
//        if (response == null || response.isHasError()) {
//            throw new ExceptionNotValidInput();
//        }
//
//    }
//
//
//    public Image requestGetImageFromPage() throws ExceptionNotValidInput {
//
//        sendRequestAndListenForResponse("GetImageFromPage", null);
//        if (response == null || response.isHasError()) {
//            throw new ExceptionNotValidInput();
//        }
//
//        return (Image) response.getResults().get(0);
//
//
//    }


    public void DisplayPageInformation() {
        sendRequestAndListenForResponse("Display Page Information", null);
        if (response == null || response.isHasError())
            return;

    }

    /**
     * Request to like.
     */


    public void requestToLike(String idToLike) throws IOException {

        System.out.println("The id to like is " + idToLike);

        ArrayList<Object> requestLike = new ArrayList<>();
        requestLike.add(idToLike);
        sendRequestAndListenForResponse("LikeMessage", requestLike);
        if (response == null || response.isHasError()) {
            System.out.println("we can't like message");

            return;

        }

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


    public void fixGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new Server.LocalDateSerializer());

        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new Server.LocalDateTimeSerializer());

        gsonBuilder.registerTypeAdapter(LocalDate.class, new Server.LocalDateDeserializer());

        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new Server.LocalDateTimeDeserializer());

        gson = gsonBuilder.setPrettyPrinting().create();

    }

    public String requestToRetweet(String idToRetweet, String QuoteTweet) {

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


    public Parent showFollowersOf(String username) throws IOException {
        ArrayList<Object> usernameP = new ArrayList<>();
        usernameP.add(username);
        VBox vBoxShowFollowers = new VBox();
        sendRequestAndListenForResponse("showFollowersAndFollowingsOf", usernameP);
        if (response == null || response.isHasError())
            return null;

        ArrayList<Object> result = response.getResults();
        ArrayList<Object> followers = (ArrayList<Object>) result.get(0);

        for (Object userNameFollower : followers) {
            ArrayList<Object> parameters = new ArrayList<>();
            parameters.add(userNameFollower);
            sendRequestAndListenForResponse("Get Page Info", parameters);
            if (response == null || response.isHasError()) {

                return null;

            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search_Person.fxml"));
            Parent root = loader.load();
            search_PersonController search_personController = loader.getController();
            search_personController.getData(response.getResults());


            vBoxShowFollowers.getChildren().add(root);
        }

        return vBoxShowFollowers;

    }


    public Parent showFollowingsOf(String username) throws IOException {
        ArrayList<Object> usernameP = new ArrayList<>();
        usernameP.add(username);
        VBox vBoxShowFollowings = new VBox();

        sendRequestAndListenForResponse("showFollowersAndFollowingsOf", usernameP);
        if (response == null || response.isHasError())
            return null;

        ArrayList<Object> result = response.getResults();
        ArrayList<Object> followings = (ArrayList<Object>) result.get(1);

        for (Object userNameFollower : followings) {
            ArrayList<Object> parameters = new ArrayList<>();
            parameters.add(userNameFollower);
            sendRequestAndListenForResponse("Get Page Info", parameters);
            if (response == null || response.isHasError()) {
                return null;

            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search_Person.fxml"));
            Parent root = loader.load();
            search_PersonController search_personController = loader.getController();
            search_personController.getData(response.getResults());


            vBoxShowFollowings.getChildren().add(root);
        }

        return vBoxShowFollowings;

    }

    public void editPage(String firstName,
                         String lastName,
                         String year,
                         String month,
                         String day,
                         String id,
                         String bio) throws ExceptionNotValidInput, ExceptionNoConnection {


        ArrayList<Object> pageParameters = new ArrayList<>();
        pageParameters.add(firstName);
        pageParameters.add(lastName);
        pageParameters.add(year);
        pageParameters.add(month);
        pageParameters.add(day);
        pageParameters.add(id);
        pageParameters.add(bio);
        sendRequestAndListenForResponse("Edit Profile", pageParameters);
        if (response == null || response.isHasError())
            throw new ExceptionNoConnection();
        System.out.println("edit successful.");
    }


    public void deleteTweet(String id) {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(id);
        sendRequestAndListenForResponse("Delete Tweet", parameters);
        if (response == null || response.isHasError()) {
            return;
        }
        System.out.println("You successfully Deleted tweet with id " + id + " .");
    }



    public Parent searchByString(String text) throws IOException {
        ArrayList<Object>  parameters= new ArrayList<>();
        parameters.add(text);
        VBox searchResultsVbox = new VBox();
        sendRequestAndListenForResponse("searchByString", parameters);
        if (response == null || response.isHasError()) {

            return null;

        }
        for (Object username : response.getResults()) {
            parameters = new ArrayList<>();
            parameters.add(username);
            sendRequestAndListenForResponse("Get Page Info", parameters);
            if (response == null || response.isHasError()) {

                return null;

            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search_Person.fxml"));
            Parent root = loader.load();
            search_PersonController search_personController = loader.getController();
            search_personController.getData(response.getResults());


            searchResultsVbox.getChildren().add(root);
        }

        return searchResultsVbox;

    }

    public void checkFastLogin(){

        if (connectionService.getProps().getProperty("shouldRemember").equals("true")) {
            connectionService.connectToServer();
            processSignIn(connectionService.getProps().getProperty("Client.username"), connectionService.getProps().getProperty("Client.password"));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Designed_Menu.fxml"));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            menuController = fxmlLoader.getController();
            App.setScene(root);

        }
    }
}