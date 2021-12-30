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
    Properties props=new Properties();
    ExecutorService executorService = Executors.newCachedThreadPool();


    public static void main(String[] args) throws NoSuchAlgorithmException {
        new Server();
    }



    public Server() {
        initializeServer();
        acceptClients();
    }



    private void initializeServer() {
        readProps();
        try {
            database = new Database();
            authenticationService = new AuthenticationService(database);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        observerService = new ObserverService(database);
        timelineService = new TimelineService(database);
        tweetingService = new TweetingService(database);

    }


    private void readProps(){

        try {
            FileReader reader;
            reader=new FileReader("src/main/resources/server-application.properties");
            props.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void acceptClients(){

        try {
            serverSocket = new ServerSocket(Integer.parseInt((String) props.get("server.port")));
            while (shouldRun) {
                Socket socket = serverSocket.accept();
                System.out.println("socket " + socket.toString());
                executorService.execute(new ClientHandler(socket,authenticationService,tweetingService,observerService,timelineService));


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public Database loadDatabase() throws NoSuchAlgorithmException {
        FileManagement fileManagement = new FileManagement();
        return fileManagement.loadDatabase();
    }



    public static class LocalDateSerializer implements JsonSerializer < LocalDate > {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");

        @Override
        public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(localDate));
        }
    }

    public static class LocalDateTimeSerializer implements JsonSerializer < LocalDateTime > {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss");

        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(localDateTime));
        }
    }

    public static class LocalDateDeserializer implements JsonDeserializer < LocalDate > {
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
