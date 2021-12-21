import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class ClientHandler implements Runnable {

    private Socket socket;
    private boolean clientIsConnected;
    private AuthenticationService authenticationService;
    private TweetingService tweetingService;

    public ClientHandler(Socket socket, AuthenticationService authenticationService, TweetingService tweetingService) {
        this.socket = socket;
        this.clientIsConnected = true;


        this.authenticationService = authenticationService;
        this.tweetingService = tweetingService;

    }

    @Override
    public void run() {

//        ObjectInputStream objectInputStream = null;
//        ObjectOutputStream objectOutputStream = null;
//        Object clientToHandler;
//        try {
//            objectInputStream = new ObjectInputStream(socket.getInputStream());
//            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//
//            while (clientIsConnected) {
//                clientToHandler = objectInputStream.readObject();
//
//                if(clientToHandler instanceof Client)
//                {
//                    //sign up or sign in
//
//                    objectOutputStream.writeObject(authenticationService.signUp((Client) clientToHandler),"","");
//
//                }
//
//                if(clientToHandler instanceof PersonalInformation)
//                {
//                    //GFG is correct
//                    objectOutputStream.writeObject(authenticationService.signIn(((PersonalInformation) clientToHandler).getUserName(),((PersonalInformation) clientToHandler).getPassword()));
//
//                }
//
//            }
//
//            objectInputStream.close();
//            objectOutputStream.close();
//            socket.close();
//        } catch (EOFException e) {
//            clientIsConnected = false;
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//
//
//


    }
}
