package org.ce.ap.impl.server;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.image.Image;
import org.ce.ap.ParameterValue;
import org.ce.ap.Request;
import org.ce.ap.Response;
import org.ce.ap.server.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * class : The type Client handler.
 * @author MohammadHdi sheikhEslami
 * @author Rezvan Afari
 * @version 1.0.0
 */
public class ClientHandlerImpl implements ClientHandler {

    private Socket socket;
    private boolean clientIsConnected;
    private AuthenticationService authenticationService;
    private TweetingService tweetingService;
    private ObserverService observerService;
    private TimelineService timelineService;
    /**
     * The Response.
     */
    Response response = new Response(false, 0, 0, null);
    /**
     * The Request.
     */
    Request request;
    /**
     * The Object input stream.
     */
    ObjectInputStream objectInputStream;
    /**
     * The Object output stream.
     */
    ObjectOutputStream objectOutputStream;
    /**
     * The Message.
     */
    String message = "";
    /**
     * The Page.
     */
    Page page = null;


    /**
     * The Gson.
     */
    Gson gson;

    /**
     * Instantiates a new Client handler.
     *
     * @param socket                the socket
     * @param authenticationService the authentication service
     * @param tweetingService       the tweeting service
     * @param observerService       the observer service
     * @param timelineService       the timeline service
     */
    public ClientHandlerImpl(Socket socket, AuthenticationService authenticationService, TweetingService tweetingService, ObserverService observerService, TimelineService timelineService) {
        fixGson();
        this.socket = socket;
        this.clientIsConnected = true;
        this.authenticationService = authenticationService;
        this.tweetingService = tweetingService;
        this.observerService = observerService;
        this.timelineService = timelineService;

    }

    @Override
    public void run() {

        System.out.println("********");
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            while (clientIsConnected) {
                if (!socket.isConnected()) {
                    clientIsConnected = false;
                    break;
                }
                refreshResponse();
                message = (String) objectInputStream.readObject();
                System.out.println("request received");

                request = gson.fromJson(message, Request.class);
                System.out.println(request.getTitle() + "  " + request.getParameterValue());

                if (request.getTitle().equals("Sign Up")) {
                    System.out.println("requested sign up");
                    processSignUp();

                } else if (request.getTitle().equals("Sign In")) {
                    System.out.println("Sign in requested");
                    processSignIn();

                } else if (request.getTitle().equals("Get Timeline")) {
                    processTimeLine();

                } else if (request.getTitle().equals("Show Followers")) {
                    ShowAllFollowers();
//.......................................................................................
                }
                else if(request.getTitle().equals("Get Tweet And Retweet for PageMenu")){
                    GetTweetAndRetweetForPageMenu();
                }
                else if(request.getTitle().equals("FollowFromPage")){
                    requestFollowFromPage();
                }
                else if(request.getTitle().equals("UnfollowFromPage")){
                    requestUnfollowFromPage();
                }
                else if(request.getTitle().equals("showFollowersAndFollowingsOf")){
                    showFollowersAndFollowingsOf();
                }

                else if(request.getTitle().equals("searchByString")){
                    searchByString();
                }

                //...........................................................................
                else if (request.getTitle().equals("Delete Follower")) {
                    requestDeleteFollower();
                } else if (request.getTitle().equals("unfollow")) {
                    requestUnfollow();
                } else if (request.getTitle().equals("Add Tweet")) {
                    processTweet();
                } else if (request.getTitle().equals("Show AllUsernames")) {
                    ShowAllUsernames();
                } else if (request.getTitle().equals("follow")) {
                    requestFollow();
                } else if (request.getTitle().equals("showFollowersAndFollowings")) {
                    showFollowersAndFollowings();
                } else if (request.getTitle().equals("myTweet")) {
                    sendMyTweetAndRetweet();
                } else if (request.getTitle().equals("myFavoriteTweets")) {
                    myFavoriteTweets();
                } else if (request.getTitle().equals("Display Page Information")) {
                    DisplayPageInformation();
                } else if (request.getTitle().equals("LikeMessage")) {
                    LikedMessage();
                } else if (request.getTitle().equals("RetweetMessage")) {
                    retweetMessage();
                } else if (request.getTitle().equals("Edit Profile")) {
                    editProfile();
                } else if (request.getTitle().equals("Delete Tweet")) {

                    deleteTweet();
                }else if (request.getTitle().equals("Set Image")) {
                    SetImageToPage();
                }else if (request.getTitle().equals("GetImageFromPage")) {
                    GetImageFromPage();
                }else if (request.getTitle().equals("Get Page Info")) {
                    getPageInfo();
                } else if (request.getTitle().equals("Get Person")) {
                    getPersonInfo();

                }


            }
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();

        } catch (EOFException e) {
            clientIsConnected = false;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }

    /**
     * Send my tweet and replies.
     *
     * @throws IOException the io exception
     */
    @Override
    public void sendMyTweetAndRetweet() throws IOException {
        ArrayList<Object> result = new ArrayList<>();
        result.addAll(timelineService.sortMessages(observerService.sendMyTweet(page.getClient().getUserName())));
        //result.addAll(observerService.sendMyTweet(page.getClient().getUserName()));
        response.setResults(result);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();
    }

    /**
     * Process sign up.
     *
     * @throws IOException              the io exception
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws ClassNotFoundException   the class not found exception
     */
    @Override
    public void processSignUp() throws IOException, NoSuchAlgorithmException, ClassNotFoundException {

        String username = (String) request.getParameterValue().get(0);
        String password = (String) request.getParameterValue().get(1);
        System.out.println(username + "  " + password);
        PersonalInformation personalInformation = authenticationService.signUpRequest(username, password);

        if (personalInformation == null) {
            response.setHasError(true);
            response.setErrorCode(20);
            System.out.println("signup user is already used.");
            objectOutputStream.writeObject(gson.toJson(response));
            refreshResponse();
            return;
        }
        System.out.println("sign up user/pass is allowed.");

        response.setCount(1);

        ArrayList<Object> result = new ArrayList<>();
        ParameterValue p = new ParameterValue("Result", "Connected");
        result.add(p);

        response.setResults(result);

        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();


        message = (String) objectInputStream.readObject();
        System.out.println("received further request.");
        request = gson.fromJson(message, Request.class);
        System.out.println(request.getParameterValue().get(2));
        System.out.println(request.getParameterValue().get(3));
        System.out.println(request.getParameterValue().get(4));
        int year = Integer.parseInt((String) request.getParameterValue().get(2));
        System.out.println("year " + year);

        int month = Integer.parseInt((String) request.getParameterValue().get(3));
        int day = Integer.parseInt((String) request.getParameterValue().get(4));
        LocalDate localDate = LocalDate.of(year, month, day);
        System.out.println("2222222");
        System.out.println(localDate);

        Client client = new Client((String) request.getParameterValue().get(0), (String) request.getParameterValue().get(1),
                localDate, personalInformation);

        page = authenticationService.signUp(client, (String) request.getParameterValue().get(5), (String) request.getParameterValue().get(6));
        response.setCount(1);
        System.out.println("Authentication completed.");
        result = new ArrayList<>();
        p = new ParameterValue("Result", "Connected");
        result.add(p);

        response.setResults(result);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();
        System.out.println("signUp successful.");
    }

    /**
     * Process sign in.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws IOException              the io exception
     */
    @Override
    public void processSignIn() throws NoSuchAlgorithmException, IOException {


        page = authenticationService.signInRequest((String) (request.getParameterValue().get(0)),
                (String) (request.getParameterValue().get(1)));
        if (page == null) {
            System.out.println("*****************************+page is null");
            response.setHasError(true);
            response.setErrorCode(15);

        }
        System.out.println("no problem");

        response.setCount(1);

        ArrayList<Object> result = new ArrayList<>();
        ParameterValue p = new ParameterValue("Result", "Connect!");
        result.add(p);

        response.setResults(result);
        objectOutputStream.writeObject(gson.toJson(response));
        System.out.println("sent response");
        refreshResponse();

    }

    /**
     * Process tweet.
     *
     * @throws IOException the io exception
     */
    @Override
    public void processTweet() throws IOException {

        String text = (String) (request.getParameterValue().get(0));
        if (!tweetingService.addTweet(new Tweet(page.getClient().getUserName(), text))) {
            response.setHasError(true);
            response.setErrorCode(23);
        }
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();

    }

    /**
     * Process time line.
     *
     * @throws IOException the io exception
     */
    @Override
    public void processTimeLine() throws IOException {

        System.out.println((page.getClient().getUserName()));
        ArrayList<Object> result = new ArrayList<>();
        result.addAll(timelineService.gatherTimeline(page.getClient().getUserName()));
        System.out.println(result);
        response.setResults(result);
        System.out.println(gson.toJson(response));
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();


    }

    /**
     * Show all followers.
     *
     * @throws IOException the io exception
     */
    @Override
    public void ShowAllFollowers() throws IOException {
        ArrayList<Object> UserNameFollower = null;
        try {
            UserNameFollower.addAll(observerService.getFollowers(page.getClient().getUserName()));

        } catch (Exception e) {
            response.setHasError(true);
            response.setErrorCode(12);
        }

        response.setResults(UserNameFollower);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();

    }

    /**
     * Request delete follower.
     *
     * @throws IOException the io exception
     */
    @Override
    public void requestDeleteFollower() throws IOException {

        String userName = (String) request.getParameterValue().get(0);
        if (!observerService.deleteFollower(userName, page.getClient().getUserName())) {

            response.setHasError(true);
            response.setErrorCode(22);

        }
        response.setCount(1);
        response.setResults(null);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();
    }

    /**
     * Request unfollow.
     *
     * @throws IOException the io exception
     */
    @Override
    public void requestUnfollow() throws IOException {

        String userName = (String) request.getParameterValue().get(0);
        if (!observerService.unfollow(userName, page.getClient().getUserName())) {

            response.setHasError(true);
            response.setErrorCode(23);

        }
        response.setCount(1);
        response.setResults(null);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();
    }

    /**
     * Request follow.
     *
     * @throws IOException the io exception
     */
    @Override
    public void requestFollow() throws IOException {
        ArrayList<Object> follow = request.getParameterValue();
        ArrayList<Object> checked = new ArrayList<>();
        int counter = 0;
        for (Object obj : follow) {
            if (observerService.follow((String) obj, page.getClient().getUserName())) {
                checked.add(true);
                counter++;
            } else {
                checked.add(false);
                counter++;
            }
        }
        counter = 0;
        for (Object obj : checked) {
            if (obj.equals(false)) {
                counter++;
            }
        }

        if (counter == checked.size()) {
            response.setHasError(true);
            response.setErrorCode(22);
        }

        response.setResults(checked);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();

    }

    /**
     * Refresh response.
     */
    @Override
    public void refreshResponse() {
        response = new Response(false, 0, 0, null);
    }


    /**
     * Show all usernames.
     *
     * @throws IOException the io exception
     */
    @Override
    public void ShowAllUsernames() throws IOException {

        ArrayList<Object> x = new ArrayList<>();
        x.addAll(authenticationService.AllUserNames());
        x.remove(page.getClient().getUserName());
        for (String following : page.getFollowingsList()) {
            x.remove(following);
        }

        response.setResults(x);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();
    }

    /**
     * Show followers and followings.
     *
     * @throws IOException the io exception
     */
    @Override
    public void showFollowersAndFollowings() throws IOException {

        ArrayList<Object> UserNameFollowers = new ArrayList<>();
        ArrayList<Object> UserNameFollowings = new ArrayList<>();
        System.out.println("Show in observerService (section follower)  : ");

        System.out.println(observerService.getFollowers(page.getClient().getUserName()));
        try {
            UserNameFollowers.addAll(observerService.getFollowers(page.getClient().getUserName()));
            UserNameFollowings.addAll(observerService.getFollowings(page.getClient().getUserName()));

        } catch (Exception e) {
            response.setHasError(true);
            response.setErrorCode(12);
        }

        ArrayList<Object> result = new ArrayList<>();
        result.add(UserNameFollowers);
        result.add(UserNameFollowings);
        System.out.println("UserName followers:");
        System.out.println(UserNameFollowers);
        System.out.println("UserNameFollowings :");
        System.out.println(UserNameFollowings);

        response.setResults(result);


        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();

    }

    /**
     * My favorite tweets.
     *
     * @throws IOException the io exception
     */
    @Override
    public void myFavoriteTweets() throws IOException {
        ArrayList<Object> result = new ArrayList<>();
        result.addAll(page.getLikedTweetsList());
        response.setResults(result);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();

    }

    /**
     * Display page information.
     *
     * @throws IOException the io exception
     */
    @Override
    public void DisplayPageInformation() throws IOException {

        ArrayList<Object> result = new ArrayList<>();
        result.add("UserName : " + page.getClient().getUserName()); //usrName
        result.add("FirstName : " + page.getClient().getFirstName());//firstName
        result.add("LastName : " + page.getClient().getLastName());//lastName
        result.add("Birthday : ");
        result.add(page.getClient().getBirthday());//birthday
        result.add("Bio : " + page.getBiography()); //bio
        result.add("Id : " + page.getId());//id
        result.add("JoinDate : ");
        result.add(page.getJoinDate()); //joinDate


        response.setResults(result);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();


    }


    /**
     * Liked message.
     *
     * @throws IOException the io exception
     */
    @Override
    public void LikedMessage() throws IOException {
        System.out.println((String) request.getParameterValue().get(0));
        System.out.println("---------");
        int IdMessageToLike = (int) Double.parseDouble((String) request.getParameterValue().get(0));


     //   int IdMessageToLike = Integer.parseInt((String) request.getParameterValue().get(0));
        System.out.println("The ID for Like is" + IdMessageToLike);

        ParameterValue parameterValue = tweetingService.findMessage(IdMessageToLike);
        if(parameterValue == null){
            response.setHasError(true);
            response.setErrorCode(33);
            objectOutputStream.writeObject(gson.toJson(response));
            refreshResponse();
            return;
        }
        else if (parameterValue.getName().equals("Tweet")) {
            System.out.println("Now Like Tweet :");
            Tweet tweet = (Tweet) parameterValue.getValue();
            System.out.println("The text Of tweet to like :" + tweet.getText());
            tweetingService.like(tweet.clientUsername, tweet, page.getClient().getUserName());
        } else if (parameterValue.getName().equals("Retweet")) {

            Retweet retweet = (Retweet) parameterValue.getValue();
            tweetingService.LikeRetweet(retweet.clientUsername, retweet, page.getClient().getUserName());
        } else {

            System.out.println("*******");
            response.setHasError(true);
            response.setErrorCode(33);
        }
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();

    }

    /**
     * Retweet message.
     *
     * @throws IOException the io exception
     */
    @Override
    public void retweetMessage() throws IOException {
        int IdMessageToRetweet = (int) Double.parseDouble((String) request.getParameterValue().get(0));
        String quoteTweet = (String) request.getParameterValue().get(1);
        // System.out.println("The ID for Like is"+IdMessageToLike);

        ParameterValue parameterValue = tweetingService.findMessage(IdMessageToRetweet);


        if(parameterValue == null){
            response.setHasError(true);
            response.setErrorCode(33);
            objectOutputStream.writeObject(gson.toJson(response));
            refreshResponse();
            return;
        }
        else if (parameterValue.getName().equals("Tweet")) {
            Tweet tweet = (Tweet) parameterValue.getValue();
            if (!tweetingService.addRetweet(tweet, page.getClient().getUserName(), quoteTweet)) {
                response.setHasError(true);
                response.setErrorCode(44);
            }

        } else if (parameterValue.getName().equals("Retweet")) {

            Retweet retweet = (Retweet) parameterValue.getValue();
            if (!tweetingService.addRetweet(retweet, page.getClient().getUserName(), quoteTweet)) {
                response.setHasError(true);
                response.setErrorCode(44);
            }
        } else if (parameterValue.getName().equals("Reply")) {
            Reply reply = (Reply) parameterValue.getValue();
            if (!tweetingService.addRetweet(reply, page.getClient().getUserName(), quoteTweet)) {
                response.setHasError(true);
                response.setErrorCode(44);
            }

        } else {

            System.out.println("*******");
            response.setHasError(true);
            response.setErrorCode(33);
        }
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();

    }

    /**
     * Delete tweet.
     *
     * @throws IOException the io exception
     */
    @Override
    public void deleteTweet() throws IOException {
        String userName = (String) request.getParameterValue().get(0);
        if (!tweetingService.deleteTweet((int) Double.parseDouble(userName),page.getClient().getUserName())) {
            response.setHasError(true);
            response.setErrorCode(33);
        }
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();

    }

    /**
     * Edit profile.
     */

    @Override
    public void editProfile() {

        System.out.println("received further request.");
        request = gson.fromJson(message, Request.class);
        String fistName = (String) request.getParameterValue().get(0);
        String lastName = (String) request.getParameterValue().get(1);
        int year = Integer.parseInt((String) request.getParameterValue().get(2));
        System.out.println("year " + year);
        int month = Integer.parseInt((String) request.getParameterValue().get(3));
        int day = Integer.parseInt((String) request.getParameterValue().get(4));
        String id = (String) request.getParameterValue().get(5);
        String bio = (String) request.getParameterValue().get(6);
        LocalDate localDate = LocalDate.of(year, month, day);
        System.out.println("2222222");
        System.out.println(localDate);
        page.getClient().setFirstName(fistName);
        page.getClient().setLastName(lastName);
        page.getClient().setBirthday(localDate);
        page.setId(id);
        page.setBiography(bio);
        refreshResponse();
        try {
            objectOutputStream.writeObject(gson.toJson(response));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    /**
     * Fix gson.
     */
    public void fixGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new Server.LocalDateSerializer());

        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new Server.LocalDateTimeSerializer());

        gsonBuilder.registerTypeAdapter(LocalDate.class, new Server.LocalDateDeserializer());

        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new Server.LocalDateTimeDeserializer());

        gson = gsonBuilder.setPrettyPrinting().create();

    }

    public void SetImageToPage() throws IOException {
        Image image = (Image) request.getParameterValue().get(0);
        page.setImage(image);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();
    }

    public void GetImageFromPage() throws IOException {
        ArrayList<Object> result = new ArrayList<>();
        result.add(page.getImage()); //usrName

        response.setResults(result);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();

    }


    public String getPageStatus(Page page){
        if (page.equals(this.page)){
            return "OwnPage";
        }
        else if (observerService.getFollowings(this.page.getClient().getUserName()).contains(page.getClient().getUserName())){
            return "Followed";
        }
        else {
            return "NotFollowed";
        }

    }

    public void getPageInfo() throws IOException{
        ArrayList<Object> pageUsername= request.getParameterValue();
        String username=(String)pageUsername.get(0);
        String status;
        Page clientPage =observerService.getPageDetails(username);

        if (username==null){status="OwnPage";}

        else {
            status = getPageStatus(clientPage);
        }

        if (status=="OwnPage"){
            clientPage =this.page;}
        ArrayList<Object> result = new ArrayList<>();
        result.add( clientPage.getClient().getUserName()); //userName
        result.add( clientPage.getClient().getFirstName());//firstName
        result.add( clientPage.getClient().getLastName());//lastName
        result.add( clientPage.getClient().getBirthday());//birthday
        result.add( clientPage.getBiography()); //bio
        result.add( clientPage.getId());//id
        result.add(clientPage.getJoinDate()); //joinDate
        result.add(status);//status
        result.add(clientPage.getFollowers());//followers
        result.add(clientPage.getFollowingsList());//followings
        //result.add(page.getImage());//image
        response.setResults(result);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();

    }


    public void getPersonInfo() throws IOException {
        ArrayList<Object> personUsername= request.getParameterValue();
        Page page = observerService.getPageDetails((String)personUsername.get(0));

        ArrayList<Object> result = new ArrayList<>();
        result.add( page.getClient().getUserName()); //userName
        result.add( page.getId());//id
        //result.add(page.getImage());//image
        response.setResults(result);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();
    }


    public void requestFollowFromPage() throws IOException {
        String followedUsername = (String) request.getParameterValue().get(0);
        response.setHasError(observerService.follow( followedUsername, page.getClient().getUserName()));
        objectOutputStream.writeObject(gson.toJson(response));

    }
    public void requestUnfollowFromPage() throws IOException {

        String unfollowedUsername = (String) request.getParameterValue().get(0);
        response.setHasError(observerService.unfollow( unfollowedUsername, page.getClient().getUserName()));
        objectOutputStream.writeObject(gson.toJson(response));

    }
    public void showFollowersAndFollowingsOf() throws IOException {


        ArrayList<Object> UserNameFollowers = new ArrayList<>();
        ArrayList<Object> UserNameFollowings = new ArrayList<>();
        System.out.println("Show in observerService (section follower)  : ");

        System.out.println(observerService.getFollowers((String)(request.getParameterValue().get(0))));
        try {
            UserNameFollowers.addAll(observerService.getFollowers((String)(request.getParameterValue().get(0))));
            UserNameFollowings.addAll(observerService.getFollowings((String)(request.getParameterValue().get(0))));

        } catch (Exception e) {
            response.setHasError(true);
            response.setErrorCode(12);
        }

        ArrayList<Object> result = new ArrayList<>();
        result.add(UserNameFollowers);
        result.add(UserNameFollowings);
        response.setResults(result);
        objectOutputStream.writeObject(gson.toJson(response));
    }
    public void GetTweetAndRetweetForPageMenu() throws IOException {
        ArrayList<Object> pageUsername= request.getParameterValue();
        String username=(String)pageUsername.get(0);
        if(username== null){
            username = page.getClient().getUserName();
        }

        ArrayList<Object> result = new ArrayList<>();
        result.addAll(timelineService.sortMessages(observerService.sendMyTweet(username)));
        //result.addAll(observerService.sendMyTweet(page.getClient().getUserName()));
        response.setResults(result);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();

    }

    public void searchByString() throws IOException {
        ArrayList<Object> txt= request.getParameterValue();
        String text=(String)txt.get(0);
        ArrayList<Object> results=new ArrayList<>(observerService.searchForUser(text));
        response.setResults(results);
        objectOutputStream.writeObject(gson.toJson(response));
        refreshResponse();


    }
}




