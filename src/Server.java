import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    TweetingService tweetingService;
    TimelineService timelineService;
    AuthenticationService authenticationService;
    ObserverService observerService;
    Database database;
    boolean shouldRun = true;
    ServerSocket serverSocket;

    public Server() {

        database = loadDatabase();
        observerService = new ObserverService(database);
        authenticationService = new AuthenticationService(database);
        timelineService = new TimelineService(database);
        tweetingService = new TweetingService(database, observerService);


        try {
            serverSocket = new ServerSocket(1234);
            while (shouldRun) {
                Socket socket = serverSocket.accept();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public Database loadDatabase() {
        FileManagement fileManagement = new FileManagement();
        return fileManagement.loadAll();
    }


    public static void main(String[] args) {
        new Server();
    }
}
