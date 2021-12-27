package main.java.org.ce.ap.client;

import com.google.gson.Gson;
import main.java.org.ce.ap.server.Page;
import main.java.org.ce.ap.server.PersonalInformation;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CommandParserService {

   private static ConnectionService connectionService;
  // private static JSONObject jsonObject = new JSONObject();


    public static void main(String[] args) {

        Gson gson = new Gson();
        try {
            connectionService = new ConnectionService();
        } catch (IOException e) {
            System.out.println("Not connected to server ...\nfinish.");
            return;
        }


        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("1- sign in 2- sign up 3- exit");
            try {
                int n =scanner.nextInt();
                scanner.nextLine();
                if (n == 1) {
                    System.out.println("Enter UserName and password : ");
                    String username = scanner.nextLine();
                    String password = scanner.nextLine();

                    //System.out.println("password = " + password);
                   // System.out.println(username);

//                    JSONObject jsonObject1 =new JSONObject();
//                    jsonObject1.put("UserName", username);
//                    jsonObject1.put("Password", password);


//                    jsonObject.put("Title","Sign in");
//                    jsonObject.put("ParameterValues",jsonObject1);
                    PersonalInformation personalInformation = new PersonalInformation(username,password);

                    try {
                        String s = gson.toJson(personalInformation);
                       // System.out.println(s);
                        connectionService.connectCommandParserToServer(s);
                    }catch (IOException e)
                    {
                        System.out.println("We can't connect to server for sign in.\nplease again");
                        continue;
                    }
                    Page page = null;
                    try{
                        page = (Page) connectionService.connectServerToCommandParser().get("Page");

                    }catch (IOException | ClassNotFoundException e)
                    {
                        System.out.println("We can't connect to server for receive page\nplease again");
                    }

                    //show page
                    ConsoleViewService.ShowPage(page);

                    //Activities after the show => like , add Tweet , ...
                    menu(page);


                } else if (n == 2) {

                } else if (n == 3) {
                    //disconnect any socket , ...
                    return;
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


    public static void menu(Page page) throws JSONException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int n;
            System.out.println("1 - add tweet 2- delete tweet 3- Like   show page");
            try {
                n = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid number\nplease again!");
                scanner.nextLine();
                continue;
            }

            //Title , ParameterValues => Requests
            //
            if (n == 1) {
                //add tweet
                System.out.println("Please Enter the tweet tex : ");
                String s = scanner.nextLine();
                // handle in has error!
//                if (s.length() > 256) {
//                    System.out.println("The maximum length is 256 characters");
//                    continue;
//                }

                jsonObject.put("Title", "Add Tweet");
                //jsonObject.put("description" ,"Validate tweet and send it"); // not matter

                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("Content", s);
                jsonObject1.put("Client",page.getClient());

                jsonObject.put("ParameterValues",jsonObject1);


                try {
                    connectionService.connectCommandParserToServer(jsonObject);
                    System.out.println("Send tweet.");
                } catch (IOException e) {
                    System.out.println("We can't connect to the server to send tweet.\nplease again!");
                    continue;
                }



                //show
                try {
                    JSONObject jsonObject = connectionService.connectServerToCommandParser();
                    ConsoleViewService.ShowTweet((Tweet) jsonObject.get("Tweet"));

                } catch (IOException  | ClassNotFoundException e ) {
                    System.out.println("We can't connect to the server to receive information \nplease again!");
                    continue;
                }


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
}
