public class Server {


    public static void main(String[] args) {
        newServer();
    }


    public static void newServer() {
        Database database = loadDatabase();
        ObserverService observerService = new ObserverService(database);
        AuthenticationService authenticationService = new AuthenticationService(database);
        TimelineService timelineService = new TimelineService(database);
        TweetingService tweetingService = new TweetingService(database, observerService);
    }




    public static Database loadDatabase() {
        FileManagement fileManagement = new FileManagement();
        return fileManagement.loadAll();
    }



}
