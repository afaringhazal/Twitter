import org.json.JSONException;
import org.json.JSONObject;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    private Socket socket;
    private boolean clientIsConnected;
    private AuthenticationService authenticationService;
    private TweetingService tweetingService;
    JSONObject jo = new JSONObject();


    public ClientHandler(Socket socket, AuthenticationService authenticationService, TweetingService tweetingService) {
        this.socket = socket;
        this.clientIsConnected = true;


        this.authenticationService = authenticationService;
        this.tweetingService = tweetingService;

    }

    @Override
    public void run() {

        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        JSONObject clientToHandler;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            while (clientIsConnected) {
                clientToHandler = (JSONObject) objectInputStream.readObject();

                if((clientToHandler.get("Title")).equals("Sign Up")) {
                    // String firstName, String lastName, LocalDate birthday, String userName, String password)
                    Page page = authenticationService.signUpRequest((Client)clientToHandler.get("Client"),clientToHandler.getString("Bio"),clientToHandler.getString("Id"));

                    jo.put("Page",page);
                    objectOutputStream.writeObject(jo);

                }
                if((clientToHandler.get("Title")).equals("Sign In")) {

                    Page page =authenticationService.signInRequest(((PersonalInformation)clientToHandler.get("Personal Information")).getUserName(),
                            (((PersonalInformation) clientToHandler.get("Personal Information")).getPassword()));

                    jo.put("Page",page);
                    objectOutputStream.writeObject(jo);


                }
                if(clientToHandler.getString("Title").equals("get Followers"))
                {
                    //need page
                    Page page = (Page) clientToHandler.get("Page");
                    jo.put("Followers",page.getFollowers());
                    objectOutputStream.writeObject(jo);

                }
                if(clientToHandler.getString("Title").equals("Delete Follower"))
                {






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
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }






}


