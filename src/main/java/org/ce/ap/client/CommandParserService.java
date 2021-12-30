package main.java.org.ce.ap.client;

import com.google.gson.Gson;
//import main.java.org.ce.ap.ParameterValue;
import com.google.gson.GsonBuilder;
import main.java.org.ce.ap.Request;
import main.java.org.ce.ap.Response;
import main.java.org.ce.ap.server.Server;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class CommandParserService {
    Scanner scanner = new Scanner(System.in);
    Gson gson ;
    Response response = null;
    Request request= new Request("",null);

    boolean shouldRun=true;
    private  ConnectionService connectionService;
    private ConsoleViewService consoleViewService = new ConsoleViewService();




    public static void main(String[] args) {new CommandParserService();}


    public CommandParserService(){
    fixGson();
        try {
            connectionService = new ConnectionService();
        } catch (IOException e) {
            System.out.println("Not connected to server ...\nfinish.");
            return;
        }

        while (shouldRun) {
            System.out.println("1-Sign in\n2-Sign up\n3-Exit");
            try {
                int n =Integer.parseInt(scanner.nextLine());

                if (n == 1) {
                    processSignIn();


                } else if (n == 2) {
                    processSignUp();


                } else if (n == 3) {

                    shouldRun=false;
                    connectionService.stop();
                }
                else {
                    System.out.println("Invalid number!");
                }
            } catch (Exception e) {
                System.out.println("Invalid number\nPlease again!");
               // scanner.nextLine();
            }
        }


    }



    public  void showMainMenu() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int n;
            System.out.println("1-Add tweet\n2-Timeline\n3-My Page\n4-Sign Out");
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

            }
           else if (n == 3) {


               showPageMenu();
               // add tweet
                //show tweet
                //add following
                //delete follower & following =>show follower


                // delete
            }
           else if(n==4)
            {
               break;
            }


        }


    }

    public void refreshRequest(){
        request= new Request("",null);
    }


    private void processSignIn() throws IOException, ClassNotFoundException {

        System.out.println("Enter UserName and password : ");
        String username = scanner.nextLine();
        String password = scanner.nextLine();



        request.setTitle("Sign In");

        ArrayList<Object> parameterValue = new ArrayList<>();
        parameterValue.add(username);
        parameterValue.add(password);
        request.setParameterValue(parameterValue);

        try {
            connectionService.sendToServer(gson.toJson(request));
            refreshRequest();
        }catch (IOException e)
        {
            System.out.println("We can't connect to server for sign in.\nplease again");
            return;
        }

        try{
            response = gson.fromJson(connectionService.receiveFromServer(),Response.class);

            if(response.isHasError())
                throw new RuntimeException();

        }catch (IOException | ClassNotFoundException e)
        {
            System.out.println("We can't connect to server for receive page\nplease again");

        }

        showMainMenu();

    }


    public void processSignUp() throws IOException, ClassNotFoundException {


        System.out.println("Enter UserName and password: ");
        String userName = scanner.nextLine();
        String password = scanner.nextLine();

        ArrayList<Object> parameters = new ArrayList<>();

        parameters.add(userName);
        parameters.add(password);
        request.setTitle("Sign Up");
        request.setParameterValue(parameters);

        try {
            connectionService.sendToServer(gson.toJson(request));
            refreshRequest();
        }catch (IOException e)
        {
            System.out.println("We can't connect to server for sign up.\nplease again");
            return;
        }

        try{
            response = gson.fromJson(connectionService.receiveFromServer(),Response.class);

            if(response.isHasError())
                throw new RuntimeException();

        }catch (IOException | ClassNotFoundException e)
        {
            System.out.println("this user name exists\nplease again");
            return;
        }



        System.out.println("Enter firstName , Last name, birthday(year,month,day each in a separate line),id,bio.  :");
        String firstName = scanner.nextLine();
        String lastName = scanner.nextLine();
//        Integer year = Integer.parseInt(scanner.nextLine());
//        Integer month = Integer.parseInt(scanner.nextLine());
//        Integer day = Integer.parseInt(scanner.nextLine());
        String year = scanner.nextLine();
        String month = scanner.nextLine();
        String day = scanner.nextLine();

        String id = scanner.nextLine();
        String bio = scanner.nextLine();

        ArrayList<Object> signUpParameters=new ArrayList<>();
        signUpParameters.add(firstName);
        signUpParameters.add(lastName);
        signUpParameters.add(year);
        signUpParameters.add(month);
        signUpParameters.add(day);
        signUpParameters.add(id);
        signUpParameters.add(bio);
        request.setTitle("Sign Up Details");
        request.setParameterValue(signUpParameters);
        try {
            connectionService.sendToServer(gson.toJson(request));
            refreshRequest();
        }catch (IOException e)
        {
            System.out.println("We can't connect to server for sign up Details.\nplease again");
            return;
        }

        try{
            response = gson.fromJson(connectionService.receiveFromServer(),Response.class);

            if(response.isHasError())
                throw new RuntimeException();
        }catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Couldn't create new profile\nplease again");
            return;
        }
        showMainMenu();



    }


    private void addTweet() {         //add tweet
        System.out.println("Please Enter the tweet text : ");
        String s = scanner.nextLine();
        if(s.length()>256)
        {
            System.out.println("more than 256");
            return;
        }

        request.setTitle("Add Tweet");
        ArrayList<Object> parameters = new ArrayList<>();

        parameters.add(s);
        request.setParameterValue(parameters);
        try {
            connectionService.sendToServer(gson.toJson(request));
            refreshRequest();
        }catch (IOException e)
        {
            System.out.println("We can't connect to server for add tweet.\nplease again");
            return;
        }

        try{
            response = gson.fromJson(connectionService.receiveFromServer(),Response.class);

            if(response.isHasError())
            {
                throw new RuntimeException();
            }

        }catch (IOException | ClassNotFoundException e)
        {
            System.out.println("We can't connect to server for receive tweet\nplease again");
            return;
        }

    }


    public void requestTimeline(){
        request.setTitle("Get Timeline");
        request.setParameterValue(null);
        try {
            connectionService.sendToServer(gson.toJson(request));
            refreshRequest();
        }catch (IOException e)
        {
            System.out.println("We can't connect to server to get Timeline.\nplease again");
            return;
        }

        try{
            String s=connectionService.receiveFromServer();
            response = gson.fromJson(s,Response.class);
            System.out.println("received string "+s);
            consoleViewService.printAllTweets(response);
            if(response.isHasError())
                throw new RuntimeException();
        }catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Couldn't create new profile\nplease again");
            return;
        }




    }


    public void showPageMenu() throws IOException, ClassNotFoundException {

        while (true) {
            Scanner scanner = new Scanner(System.in);

            int n;
            //System.out.println("1-Follow\n2-Unfollow\n3-remove follower\n4-exit");
            System.out.println("1-Followers and followings\n2-Add Tweet\n3-my Tweets & replies\n4-Likes  ,......");
            System.out.println("");
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

            }
            else if(n==4) {
                myFavoriteTweets();

            }
            else if(n==5){
                break;
            }
        }
    }

    public void follow() throws IOException, ClassNotFoundException {

        request.setTitle("Show AllUsernames");
        request.setParameterValue(null);
        try {
            connectionService.sendToServer(gson.toJson(request));
            refreshRequest();
        }catch (IOException e)
        {
            System.out.println("We can't connect to server to add tweet.\nplease again");
            return;
        }

        try{
            response = gson.fromJson(connectionService.receiveFromServer(),Response.class);

            if(response.isHasError())
            {
                throw new RuntimeException();
            }

        }catch (IOException | ClassNotFoundException e)
        {
            System.out.println("We can't connect to server for receive tweet\nplease again");
            return;
        }


        consoleViewService.printList(response);

        System.out.println("How many?");
        ArrayList<Object> result =new ArrayList<>();
        int num = Integer.parseInt(scanner.nextLine());
        for (int i=0 ;i<num ;i++)
        {
            System.out.println("Enter username: ");
            String userName =scanner.nextLine();
            result.add(userName);

        }

        request.setTitle("follow");
        request.setParameterValue(result);

        try {
            connectionService.sendToServer(gson.toJson(request));
            refreshRequest();
        }catch (IOException e)
        {
            System.out.println("We can't connect to server to add tweet.\nplease again");
            return;
        }

        try{
            response = gson.fromJson(connectionService.receiveFromServer(),Response.class);

            if(response.isHasError())
            {
                throw new RuntimeException();
            }

        }catch (IOException | ClassNotFoundException e)
        {
            System.out.println("We can't connect to server for receive tweet\nplease again");
            return;
        }




    }

    public void unfollow(){
//        request.setTitle("Show Followings");
//        request.setParameterValue(null);
//
//        try {
//            connectionService.sendToServer(gson.toJson(request));
//            refreshRequest();
//        }catch (IOException e)
//        {
//            System.out.println("We can't connect to server to add tweet.\nplease again");
//            return;
//        }
//
//        try{
//            response = gson.fromJson(connectionService.receiveFromServer(),Response.class);
//
//            if(response.isHasError())
//            {
//                throw new RuntimeException();
//            }
//
//        }catch (IOException | ClassNotFoundException e)
//        {
//            System.out.println("We can't connect to server for receive tweet\nplease again");
//            return;
//        }
//
//        consoleViewService.printList(response);


        System.out.println("Enter user name :");
        String use = scanner.nextLine();
        request.setTitle("unfollow");
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(use);
        request.setParameterValue(parameters);



        try {
            connectionService.sendToServer(gson.toJson(request));
            refreshRequest();
        }catch (IOException e)
        {
            System.out.println("We can't connect to server for add tweet.\nplease again");
            return;
        }

        try{
            response = gson.fromJson(connectionService.receiveFromServer(),Response.class);

            if(response.isHasError())
            {
                throw new RuntimeException();
            }

        }catch (IOException | ClassNotFoundException e)
        {
            System.out.println("We can't connect to server for receive tweet\nplease again");
            return;
        }



    }

    public void deleteFollower(){

//        request.setTitle("Show Followers");
//        request.setParameterValue(null);
//
//        try {
//            connectionService.sendToServer(gson.toJson(request));
//            refreshRequest();
//        }catch (IOException e)
//        {
//            System.out.println("We can't connect to server for add tweet.\nplease again");
//            return;
//        }
//
//        try{
//            response = gson.fromJson(connectionService.receiveFromServer(),Response.class);
//
//            if(response.isHasError())
//            {
//                throw new RuntimeException();
//            }
//
//        }catch (IOException | ClassNotFoundException e)
//        {
//            System.out.println("We can't connect to server for receive tweet\nplease again");
//            return;
//        }
//
//        consoleViewService.printList(response);


        System.out.println("Enter user name :");
        String use = scanner.nextLine();
        request.setTitle("Delete Follower");
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(use);
        request.setParameterValue(parameters);



        try {
            connectionService.sendToServer(gson.toJson(request));
            refreshRequest();
        }catch (IOException e)
        {
            System.out.println("We can't connect to server for add tweet.\nplease again");
            return;
        }

        try{
            response = gson.fromJson(connectionService.receiveFromServer(),Response.class);

            if(response.isHasError())
            {
                throw new RuntimeException();
            }

        }catch (IOException | ClassNotFoundException e)
        {
            System.out.println("We can't connect to server for receive tweet\nplease again");
            return;
        }


    }

    public void fixGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new Server.LocalDateSerializer());

        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new Server.LocalDateTimeSerializer());

        gsonBuilder.registerTypeAdapter(LocalDate.class, new Server.LocalDateDeserializer());

        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new Server.LocalDateTimeDeserializer());

        gson = gsonBuilder.setPrettyPrinting().create();

    }

    public void showFollowersAndFollowing() throws IOException {
        request.setTitle("showFollowersAndFollowings");
        request.setParameterValue(null);

        try {
            connectionService.sendToServer(gson.toJson(request));
            refreshRequest();
        }catch (IOException e)
        {
            System.out.println("We can't connect to server for add tweet.\nplease again");
            return;
        }

        try{
            response = gson.fromJson(connectionService.receiveFromServer(),Response.class);

            if(response.isHasError())
            {
                throw new RuntimeException();
            }

        }catch (IOException | ClassNotFoundException e)
        {
            System.out.println("We can't connect to server for receive tweet\nplease again");
            return;
        }


        consoleViewService.printFollowersAndFollowings(response);

    }

    public void  followersAndFollowingsMenu() throws IOException, ClassNotFoundException {
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
           }
           else {
               System.out.println("Invalid number!\nplease again.");
           }

       }

    }

    public void myTweetAndReplies(){

        request.setTitle("myTweetAndReplies");
        request.setParameterValue(null);

        try {
            connectionService.sendToServer(gson.toJson(request));
            refreshRequest();
        }catch (IOException e)
        {
            System.out.println("We can't connect to server to add tweet.\nplease again");
            return;
        }

        try{
            response = gson.fromJson(connectionService.receiveFromServer(),Response.class);

            if(response.isHasError())
            {
                throw new RuntimeException();
            }

        }catch (IOException | ClassNotFoundException e)
        {
            System.out.println("We can't connect to server for receive tweet\nplease again");
            return;
        }

        consoleViewService.printMyTweets(response.getResults(),0);

    }

    public void myFavoriteTweets(){



    }

}





