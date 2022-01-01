package main.java.org.ce.ap.impl.client;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.java.org.ce.ap.Request;
import main.java.org.ce.ap.Response;
import main.java.org.ce.ap.client.CommandParserService;
import main.java.org.ce.ap.client.ConnectionService;
import main.java.org.ce.ap.client.ConsoleViewService;
import main.java.org.ce.ap.server.Server;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class CommandParserServiceImpl implements CommandParserService {

    Gson gson;
    Response response = null;
    Request request = new Request("", null);
    boolean shouldRun = true;
    Scanner scanner = new Scanner(System.in);
    private ConnectionService connectionService;
    private ConsoleViewService consoleViewService = new ConsoleViewServiceImpl();


    public static void main(String[] args) {
        new CommandParserServiceImpl();
    }


    public CommandParserServiceImpl() {
        fixGson();
        connectionService = new ConnectionServiceImpl();

        while (shouldRun) {
            System.out.println("1-Sign in\n2-Sign up\n3-Exit");
            try {
                int n = Integer.parseInt(scanner.nextLine());

                if (n == 1) {
                    processSignIn();


                } else if (n == 2) {
                    processSignUp();


                } else if (n == 3) {

                    shouldRun = false;
                    connectionService.stop();
                } else {
                    System.out.println("Invalid number!");
                }
            } catch (Exception e) {
                System.out.println("Invalid number\nPlease again!");
            }
        }


    }

    @Override
    public void showMainMenu() throws IOException, ClassNotFoundException {
        while (true) {
            int n=0;

            System.out.println("1-Add tweet\n2-timeline\n3-My Page\n4-Sign Out");
            try {
                n = Integer.parseInt(scanner.nextLine());

            } catch (Exception e) {

                System.out.println("Invalid number\nplease again!");
                continue;
            }
            if (n == 1) {
                addTweet();
            } else if (n == 2) {
                requestTimeline();

            } else if (n == 3) {
                showPageMenu();

            } else if (n == 4) {
                break;
            }


        }


    }

    @Override
    public void refreshRequest() {
        request = new Request("", null);
    }

    @Override
    public void processSignIn() throws IOException, ClassNotFoundException {
        System.out.println("Enter UserName and password : ");
        String username = scanner.nextLine();
        String password = scanner.nextLine();
        ArrayList<Object> parameterValue = new ArrayList<>();
        parameterValue.add(username);
        parameterValue.add(password);
        sendRequestAndListenForResponse("Sign In", parameterValue);
        System.out.println("Successfully signed in.");
       // scanner.close();
        showMainMenu();

    }

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
        System.out.println("FirstName , Last name, Birthday(year,month,day each in a separate line),Page Id,Bio.");
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
        sendRequestAndListenForResponse("Sign Up Details", signUpParameters);
        if (response == null || response.isHasError())
            return;
        System.out.println("Sign up successful.");
        showMainMenu();


    }

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

        if (stringBuilder.toString().equals("\n") || stringBuilder.toString().equals(" ") || stringBuilder.length() > 256) {
            System.out.println("Tweets should have no more than 256 characters and should not be empty.\n Please try again.");
            return;

        }

        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(stringBuilder.toString());
        sendRequestAndListenForResponse("Add Tweet", parameters);
        if (response == null || response.isHasError()) {
            scanner.close();
            return;
        }
        System.out.println("Your tweet was successfully added to server.");


    }

    @Override
    public void requestTimeline() {

        sendRequestAndListenForResponse("Get Timeline", null);
        if (response == null || response.isHasError())
            return;
        consoleViewService.printAllTweets(response);
        showTimelineMenu();
    }

    @Override
    public void showPageMenu() {

        while (true) {
            DisplayPageInformation();
            System.out.println();
            int n = 0;
            System.out.println("1-Followers and followings\n2-Add Tweet\n3-my Tweets & replies\n4-Likes\n5-Edit profile\n6-exit");
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
                myTweetAndReplies();
            } else if (n == 4) {
                myFavoriteTweets();
            } else if (n == 5) {
                editProfile();

            } else if (n == 6) {
                break;
            }
        }
    }

    @Override
    public void follow() {

        sendRequestAndListenForResponse("Show AllUsernames", null);
        if (response == null || response.isHasError()) {
            return;
        }
        consoleViewService.printList(response);
        System.out.println("How many people do you want to follow now?");
        ArrayList<Object> result = new ArrayList<>();
        int num = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < num; i++) {
            System.out.println("Enter username: ");
            String userName = scanner.nextLine();
            result.add(userName);
        }
        sendRequestAndListenForResponse("follow", result);
        if (response == null || response.isHasError()) {
            return;
        }
      System.out.println("You successfully followed " + num + " user(s).");

    }

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

    @Override
    public void showFollowersAndFollowing() {
        sendRequestAndListenForResponse("showFollowersAndFollowings", null);
        if (response == null || response.isHasError())
            return;
        consoleViewService.printFollowersAndFollowings(response);

    }

    @Override
    public void followersAndFollowingsMenu() {
        while (true) {
            showFollowersAndFollowing();
            System.out.println();

            System.out.println("1-Follow\n2-Unfollow\n3-remove follower\n4-exit");
            int number = Integer.parseInt(scanner.nextLine());

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
    public void myTweetAndReplies() {
        sendRequestAndListenForResponse("myTweetAndReplies", null);
        if (response == null || response.isHasError())
            return;
        consoleViewService.printMyTweets(response.getResults(), 0);
    }

    @Override
    public void myFavoriteTweets() {

        sendRequestAndListenForResponse("myFavoriteTweets", null);
        if (response == null || response.isHasError())
            return;
        consoleViewService.printList(response);

    }

    @Override
    public void DisplayPageInformation() {
        sendRequestAndListenForResponse("Display Page Information", null);
        if (response == null || response.isHasError())
            return;
        consoleViewService.printList(response);

    }

    public void requestToLike() {
        System.out.println("Enter id Message : ");
        String idToLike = scanner.nextLine();
        ArrayList<Object> requestLike = new ArrayList<>();
        requestLike.add(idToLike);
        sendRequestAndListenForResponse("LikeMessage", requestLike);
        if (response == null || response.isHasError()) {
            return;

        }
        System.out.println("You successfully liked message with id: " + idToLike + " .");
    }

    public void sendRequestAndListenForResponse(String title, ArrayList<Object> parameterValues) {
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

    @Override
    public void fixGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new Server.LocalDateSerializer());

        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new Server.LocalDateTimeSerializer());

        gsonBuilder.registerTypeAdapter(LocalDate.class, new Server.LocalDateDeserializer());

        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new Server.LocalDateTimeDeserializer());

        gson = gsonBuilder.setPrettyPrinting().create();

    }



    public void showTimelineMenu() {

        System.out.println("1-Like\n2-Reply\n3-Retweet\n4-exit");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            requestToLike();

        } else if (choice == 2) {

        } else if (choice == 3) {
            requestToRetweet();
        } else if( choice == 4)
        {

            return;

        }
        else {

        }
    }

    public void requestToRetweet(){
        System.out.println("Enter id Message : ");
        String idToRetweet= scanner.nextLine();
        System.out.println("Enter text(Quote Tweet) : ");
        String TextToRetweet = scanner.nextLine();
        ArrayList<Object> requestRetweet = new ArrayList<>();
        requestRetweet.add(idToRetweet);
        requestRetweet.add(TextToRetweet);
        sendRequestAndListenForResponse("RetweetMessage",requestRetweet);
        if(response==null||response.isHasError())
        {
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
}
