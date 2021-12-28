package main.java.org.ce.ap.server;

import main.java.org.ce.ap.client.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    TweetingService tweetingService;
    TimelineService timelineService;
    static AuthenticationService  authenticationService;
    ObserverService observerService;
    Database database;
    boolean shouldRun = true;
    ServerSocket serverSocket;


    ExecutorService executorService = Executors.newCachedThreadPool();

    public Server() {

        database =new Database();//= loadDatabase();
        observerService = new ObserverService(database);
        authenticationService = new AuthenticationService(database);
        timelineService = new TimelineService(database);
        tweetingService = new TweetingService(database, observerService);


        try {
            serverSocket = new ServerSocket(1234);
            while (shouldRun) {
                Socket socket = serverSocket.accept();
                System.out.println("socket.toString() = " + socket.toString());
                executorService.execute(new ClientHandler(socket,authenticationService,tweetingService));


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public Database loadDatabase() {
        FileManagement fileManagement = new FileManagement();
        return fileManagement.loadAll();
    }


    public static void main(String[] args) throws NoSuchAlgorithmException {

        new Server();

        PersonalInformation personalInformation = new PersonalInformation("ff","12");
        Client c =new Client("f","a", LocalDate.now(),personalInformation);
        //Page p = new Page(c,"fff",LocalDate.now());
        //authenticationService.signUpRequest(c,"jj","id");
        System.out.println("---------");



    }
}
