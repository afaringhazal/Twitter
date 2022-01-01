package main.java.org.ce.ap.server;
import com.google.gson.*;
import main.java.org.ce.ap.impl.server.*;
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
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * class :  The type Server.
 * @author MohammadHdi sheikhEslami
 * @author Rezvan Afari
 * @version 1.0.0
 */
public class Server {
    /**
     * The Executor service.
     */
    ExecutorService executorService = Executors.newCachedThreadPool();
    /**
     * The Authentication service.
     */
    AuthenticationService authenticationService;
    /**
     * The Tweeting service.
     */
    TweetingServiceImpl tweetingService;
    /**
     * The Timeline service.
     */
    TimelineService timelineService;
    /**
     * The Observer service.
     */
    ObserverService observerService;
    /**
     * The Server socket.
     */
    ServerSocket serverSocket;
    /**
     * The Database.
     */
    Database database;
    /**
     * The Logger.
     */
    Logger logger;
    /**
     * The File management.
     */
    FileManagement fileManagement =new FileManagementImpl();
    /**
     * The Sc.
     */
    Scanner sc = new Scanner(System.in);
    /**
     * The Props.
     */
    Properties props = new Properties();
    /**
     * The Should run.
     */
    boolean shouldRun = true;


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        new Server();
    }

    /**
     * Instantiates a new Server.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public Server() throws NoSuchAlgorithmException {
        initializeServer();
        acceptClients();
    }

    private void initializeServer() {
        readProps();
        initLogger();
        try {
            loadDatabase();
            authenticationService = new AuthenticationServiceImpl(database);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        observerService = new ObserverServiceImpl(database);
        timelineService = new TimelineServiceImpl(database);
        tweetingService = new TweetingServiceImpl(database);
        fileManagement.initTweetingService(tweetingService);

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

    /**
     * Init logger.
     */
    public void initLogger() {
        FileHandler fh=null;
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        File logFolder = new File(props.getProperty("server.log.file"));
        if (!logFolder.exists()) {
            logFolder.mkdir();
        }

        try {
            fh = new FileHandler(props.getProperty("server.log.file") + "/log.log");

        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);


        logger.info("properties were read and logger started.");
    }

    private void acceptClients() {

        try {
            serverSocket = new ServerSocket(Integer.parseInt((String) props.get("server.port")));
            SaveThread saveThread = new SaveThread();
            executorService.execute(saveThread);
            while (true) {
                Socket socket = serverSocket.accept();
                if (socket.isConnected()) {
                    logger.info("socket: " + socket.toString() + "Connected.");
                    System.out.println("new user joined.");
                    executorService.execute(new ClientHandlerImpl(socket, authenticationService, tweetingService, observerService, timelineService));

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDatabase() throws NoSuchAlgorithmException {
        database = fileManagement.loadDatabase(tweetingService);

    }

    private void saveDatabase() {
        fileManagement.saveDatabase(database,tweetingService);

    }


    private class SaveThread extends Thread {
        public void run() {

            while (shouldRun) {
                try {

                    sleep(60000);
                    saveDatabase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * The type Local date serializer.
     */
    public static class LocalDateSerializer implements JsonSerializer<LocalDate> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");

        @Override
        public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(localDate));
        }
    }

    /**
     * The type Local date time serializer.
     */
    public static class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss");

        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(localDateTime));
        }
    }

    /**
     * The type Local date deserializer.
     */
    public static class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDate.parse(json.getAsString(),
                    DateTimeFormatter.ofPattern("d-MMM-yyyy").withLocale(Locale.ENGLISH));
        }
    }

    /**
     * The type Local date time deserializer.
     */
    public static class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDateTime.parse(json.getAsString(),
                    DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss").withLocale(Locale.ENGLISH));
        }
    }

}