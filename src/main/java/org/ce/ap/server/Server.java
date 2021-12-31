package main.java.org.ce.ap.server;

import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    TweetingService tweetingService;
    TimelineService timelineService;
    static AuthenticationService authenticationService;
    ObserverService observerService;
    Database database;
    boolean shouldRun = true;
    ServerSocket serverSocket;
    Properties props = new Properties();
    ExecutorService executorService = Executors.newCachedThreadPool();
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws NoSuchAlgorithmException {
        new Server();
    }


    public Server() throws NoSuchAlgorithmException {
        initializeServer();
        acceptClients();
    }


    private void initializeServer() {
        readProps();
        try {
            loadDatabase();
            authenticationService = new AuthenticationService(database);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        observerService = new ObserverService(database);
        timelineService = new TimelineService(database);
        tweetingService = new TweetingService(database);

    }


    private void readProps() {

        try {
            FileReader reader;
            reader = new FileReader("src/main/resources/server-application.properties");
            props.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void acceptClients() {

        try {
            serverSocket = new ServerSocket(Integer.parseInt((String) props.get("server.port")));
            while (shouldRun) {
                Socket socket = serverSocket.accept();
                System.out.println("socket " + socket.toString());
                executorService.execute(new ClientHandler(socket, authenticationService, tweetingService, observerService, timelineService));


            }
            saveDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sampleDatabase() throws NoSuchAlgorithmException {

        authenticationService.signUp(new Client(1 + "", 1 + "", LocalDate.now(), new PersonalInformation(1 + "", 1 + "")), 1 + "", 1 + "");
        authenticationService.signUp(new Client(2 + "", 2 + "", LocalDate.now(), new PersonalInformation(2 + "", 2 + "")), 12 + "", 2 + "");
        observerService.follow(1 + "", 2 + "");
        tweetingService.addTweet(new Tweet(1 + "", "hello"));
        tweetingService.addTweet(new Tweet(2 + "", "hello"));
        tweetingService.addTweet(new Tweet(1 + "", "waeawellsd"));
        observerService.follow(2 + "", 1 + "");
    }


    private void loadDatabase() throws NoSuchAlgorithmException {
        FileManagement fileManagement = new FileManagement();
        database = fileManagement.loadDatabase();
    }


    private void saveDatabase() {
        FileManagement fileManagement = new FileManagement();
        fileManagement.saveDatabase(database);


    }


    public static class LocalDateSerializer implements JsonSerializer<LocalDate> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");

        @Override
        public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(localDate));
        }
    }

    public static class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss");

        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(localDateTime));
        }
    }

    public static class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDate.parse(json.getAsString(),
                    DateTimeFormatter.ofPattern("d-MMM-yyyy").withLocale(Locale.ENGLISH));
        }
    }

    public static class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDateTime.parse(json.getAsString(),
                    DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss").withLocale(Locale.ENGLISH));
        }
    }
}