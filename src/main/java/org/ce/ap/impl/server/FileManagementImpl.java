package main.java.org.ce.ap.impl.server;
import main.java.org.ce.ap.server.Database;
import main.java.org.ce.ap.server.FileManagement;
import main.java.org.ce.ap.server.TweetingService;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/**
 * class : The type File management.
 * @author MohammadHdi sheikhEslami
 * @author Rezvan Afari
 * @version 1.0.0
 */
public class FileManagementImpl implements FileManagement {

    private File databaseFolder = null;
    private File[] filesInDatabaseFolder;
    /**
     * The Logger.
     */
    public Logger logger;
    /**
     * The Props.
     */
    Properties props = new Properties();
    /**
     * The Fh.
     */
    FileHandler fh = null;

    /**
     * Instantiates a new File management.
     */
    public FileManagementImpl() {
        readProps();
        initFolders();
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        readDatabaseFolder();
    }

    /**
     * Save database.
     *
     * @param database        the database
     * @param tweetingService the tweeting service
     */
    @Override
    public void saveDatabase(Database database, TweetingServiceImpl tweetingService) {
        String fileName;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileName = props.getProperty("server.database.file") + "/database.bin";
            fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(database);
            logger.info("database saved.");
            props.setProperty("numberOfMessages", tweetingService.counter + "");
            String propsName = "src/main/resources/server-application.properties";
            props.store(new FileOutputStream(propsName), null);
            objectOutputStream.flush();
            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            logger.info("database save failed.\n");
        } finally {
            refreshFilesInFolder(databaseFolder);
        }

    }

    /**
     * Load database database.
     *
     * @param tweetingService the tweeting service
     * @return the database
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    @Override
    public Database loadDatabase(TweetingService tweetingService) throws NoSuchAlgorithmException {
        refreshFilesInFolder(databaseFolder);
        Database database = new Database();
        try {
            FileInputStream fileInputStream = null;
            ObjectInputStream objectInputStream = null;

            if (filesInDatabaseFolder != null && filesInDatabaseFolder.length > 0) {
                try {
                    File file = filesInDatabaseFolder[0];
                    fileInputStream = new FileInputStream(file.getCanonicalFile());
                    objectInputStream = new ObjectInputStream(fileInputStream);
                    database = (Database) objectInputStream.readObject();
                    logger.info("database read.");
                    objectInputStream.close();
                    fileInputStream.close();
                } catch (IOException | ClassNotFoundException e) {
                    logger.info("database load failed.\n");

                } finally {
                    refreshFilesInFolder(databaseFolder);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return database;
    }

    /**
     * Refresh files in folder.
     *
     * @param Folder the folder
     */
    @Override
    public void refreshFilesInFolder(File Folder) {
        filesInDatabaseFolder = Folder.listFiles();

    }

    /**
     * Read props.
     */
    @Override
    public void readProps() {

        try {
            FileReader reader;
            reader = new FileReader("src/main/resources/server-application.properties");
            props.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initLogger() {
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


        logger.info("properties read and logger started.");
    }

    /**
     * Read database folder.
     */
    @Override
    public void readDatabaseFolder() {

        databaseFolder = new File(props.getProperty("server.database.file"));
        if (!databaseFolder.exists()) {
            databaseFolder.mkdir();
        }
        logger.info("database Folder Successfully read.");
    }

    /**
     * Init folders.
     */
    @Override
    public void initFolders() {
        File notesFolder;
        notesFolder = new File("files");
        if (!notesFolder.exists()) {
            notesFolder.mkdir();
        }
        notesFolder = new File("files/model");
        if (!notesFolder.exists()) {
            notesFolder.mkdir();
        }
        notesFolder = new File("files/log");
        if (!notesFolder.exists()) {
            notesFolder.mkdir();
        }

    }

    /**
     * Init tweeting service.
     *
     * @param tweetingService the tweeting service
     */
    @Override
    public void initTweetingService(TweetingServiceImpl tweetingService) {
        readProps();
        if (!props.keySet().contains("numberOfMessages")) {
            tweetingService.counter = 1;
        } else {
            tweetingService.counter = Integer.parseInt((String) props.get("numberOfMessages"));
        }


    }

}

