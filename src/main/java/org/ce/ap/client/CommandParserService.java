package main.java.org.ce.ap.client;

import com.google.gson.Gson;
import main.java.org.ce.ap.ParameterValue;
import main.java.org.ce.ap.Request;
import main.java.org.ce.ap.Response;
import main.java.org.ce.ap.server.Page;
import main.java.org.ce.ap.server.PersonalInformation;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

import java.io.IOException;
import java.net.PortUnreachableException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class CommandParserService {
    Scanner scanner = new Scanner(System.in);
    Gson gson = new Gson();
    Response response = null;
    Request request= new Request("",null);

    boolean shouldRun=true;
    private  ConnectionService connectionService;
    private ConsoleViewService consoleViewService = new ConsoleViewService();
  // private static JSONObject jsonObject = new JSONObject();




    public static void main(String[] args) {new CommandParserService();}
    public CommandParserService(){

        try {
            connectionService = new ConnectionService();
        } catch (IOException e) {
            System.out.println("Not connected to server ...\nfinish.");
            return;
        }

        while (shouldRun) {
            System.out.println("1- sign in 2- sign up 3- exit");
            try {
                int n =scanner.nextInt();
                scanner.nextLine();
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



    public  void menu()  {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int n;
            System.out.println("1 - add tweet 2- delete tweet 3- Like   show page");
            try {
                n = scanner.nextInt();
                scanner.next();
            } catch (Exception e) {
                System.out.println("Invalid number\nplease again!");
                continue;
            }

            //Title , ParameterValues => Requests
            //
            if (n == 1) {
                addTweet();


            } else if (n == 2) {
                //show all tweet
                JSONObject j = ConsoleViewService.ShowAllTweetForOnePage(page);
                System.out.println("Which one do you want to delete?");
                int number;
                while (true)
                {
                    try {
                        number =scanner.nextInt();
                        if(number >0 || number <= page.getTweets().size())
                            break;
                        else
                            System.out.println("The number entered is not in the range. Please enter again");
                    }catch (Exception e)
                    {
                        System.out.println("Not Valid input,Enter again!");

                    }
                }
                Tweet tweet = (Tweet) j.get(String.valueOf(number));

                jsonObject.put("Title", "Delete Tweet");
                jsonObject.put("ParameterValues",tweet);

                try {
                    connectionService.connectCommandParserToServer(jsonObject);
                } catch (IOException e) {
                    System.out.println("We can't connect to the server to delete tweet.\nplease again!");
                    continue;
                }
                System.out.println("Successful!");

            }
           else if (n == 3) {
                //Can like any existing tweet

                jsonObject.put("Title", "All Tweet");
                jsonObject.put("ParameterValues","");

                try {
                    connectionService.connectCommandParserToServer(jsonObject);
                } catch (IOException e) {
                    System.out.println("We can't connect to the server to give all tweet.\nplease again!");
                    continue;
                }
                JSONObject jsonObject1;

                try {
                   jsonObject1 = ConsoleViewService.ShowAllTweet((ArrayList<Tweet>)connectionService.connectServerToCommandParser().get("Results"));
                } catch (IOException |ClassNotFoundException e) {
                    System.out.println("We can't connect to the server to receive information \nplease again!");
                    continue;
                }

                System.out.println("Which one?");
                int number;
                while (true) {
                    try {
                        number = scanner.nextInt();
                        if(number < jsonObject1.getInt("Count") || number>0)
                            break;
                        else
                        {
                            System.out.println("The number entered is not in the range. Please enter again ");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input!");
                        scanner.nextLine();
                        continue;
                    }
                }

                Tweet t = (Tweet) jsonObject1.get(String.valueOf(number));
                //now send Tweet  , me for like

                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("Tweet",t);
                jsonObject2.put("MyClientToLike", page.getClient());


                jsonObject.put("Title", "Like");
                jsonObject.put("ParameterValues",jsonObject2);

                try {
                    connectionService.connectCommandParserToServer(jsonObject);
                } catch (IOException e) {
                    System.out.println("We can't connect to the server to send tweet for like.\nplease again!");
                    continue;
                }

                try {
                    ConsoleViewService.ShowTweet((Tweet) connectionService.connectServerToCommandParser().get("Results"));
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("We can not connect to the server to get the result of the Like operation.\nplease again!");
                    continue;
                }

            }


        }


    }
    private void processSignIn()
    {
        System.out.println("Enter UserName and password : ");
        String username = scanner.nextLine();
        String password = scanner.nextLine();



        request.setTitle("Sign In");
        ParameterValue p = new ParameterValue("UserName",username);
        ParameterValue p1 = new ParameterValue("Password",password);
        ArrayList<Object> parameterValue = new ArrayList<>();
        parameterValue.add(p);
        parameterValue.add(p1);
        request.setParameterValue(parameterValue);

        try {
            connectionService.connectCommandParserToServer(gson.toJson(request));
        }catch (IOException e)
        {
            System.out.println("We can't connect to server for sign in.\nplease again");
            return;
        }

        try{
            response = gson.fromJson(connectionService.connectServerToCommandParser(),Response.class);

            if(response.isHasError())
                throw new RuntimeException();

        }catch (IOException | ClassNotFoundException e)
        {
            System.out.println("We can't connect to server for receive page\nplease again");
            return;
        }

        menu();

    }




    public void processSignUp()
    {



        System.out.println("Enter UserName and password: ");
        String userName = scanner.nextLine();
        String password = scanner.nextLine();

        ArrayList<Object> parameters = new ArrayList<>();

        parameters.add( new ParameterValue("UserName", userName));
        parameters.add(new ParameterValue("Password", password));
        request.setTitle("Sign Up");
        request.setParameterValue(parameters);

        try {
            connectionService.connectCommandParserToServer(gson.toJson(request));
        }catch (IOException e)
        {
            System.out.println("We can't connect to server for sign up.\nplease again");
            return;
        }

        try{
            response = gson.fromJson(connectionService.connectServerToCommandParser(),Response.class);

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
        int year = scanner.nextInt();
        int month = scanner.nextInt();
        int day = scanner.nextInt();
        scanner.nextLine();
        String id = scanner.nextLine();
        String bio = scanner.nextLine();

        // LocalDate localDate = LocalDate.of(year,month,day);

        ArrayList<Object> signUpParameters=new ArrayList<>();
        signUpParameters.add(new ParameterValue("FirstName",firstName));
        signUpParameters.add(new ParameterValue("LastName",lastName));
        signUpParameters.add(new ParameterValue("BirthYear",""+year));
        signUpParameters.add(new ParameterValue("BirthMonth",month+""));
        signUpParameters.add(new ParameterValue("BirthDay",day+""));
        signUpParameters.add(new ParameterValue("id",id));
        signUpParameters.add(new ParameterValue("Bio",bio));
        request.setTitle("Sign Up Details");
        request.setParameterValue(signUpParameters);
        try {
            connectionService.connectCommandParserToServer(gson.toJson(request));
        }catch (IOException e)
        {
            System.out.println("We can't connect to server for sign up Details.\nplease again");
            return;
        }

        try{
            response = gson.fromJson(connectionService.connectServerToCommandParser(),Response.class);

            if(response.isHasError())
                throw new RuntimeException();
        }catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Couldn't create new profile\nplease again");
            return;
        }



    }


    private void addTweet()
    {         //add tweet
        System.out.println("Please Enter the tweet text : ");
        String s = scanner.nextLine();
        if(s.length()>256)
        {
            System.out.println("more than 256");
            return;
        }

        request.setTitle("Add Tweet");
        ArrayList<Object> parameters = new ArrayList<>();

        parameters.add( new ParameterValue("Tweet", s));
        request.setParameterValue(parameters);
        try {
            connectionService.connectCommandParserToServer(gson.toJson(request));
        }catch (IOException e)
        {
            System.out.println("We can't connect to server for add tweet.\nplease again");
            return;
        }

        try{
            response = gson.fromJson(connectionService.connectServerToCommandParser(),Response.class);

            if(response.isHasError())
            {
                throw new RuntimeException();
            }

        }catch (IOException | ClassNotFoundException e)
        {
            System.out.println("We can't connect to server for receive tweet\nplease again");
            return;
        }



        try{
            response = gson.fromJson(connectionService.connectServerToCommandParser(),Response.class);

            if(response.isHasError())
                throw new RuntimeException();

        }catch (IOException | ClassNotFoundException e)
        {
            System.out.println("We can't connect to server for receive page\nplease again");
            return;
        }


        consoleViewService.ShowTweet(response);



    }

}





