package main.java.org.ce.ap.server;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FileManagement {

    private File databaseFolder = null;
    private File[] filesInDatabaseFolder;
    public Logger logger;
    Properties props;

    public FileManagement() {
        readProps();
        initLogger();
        readDatabaseFolder();
    }


    public void saveDatabase(Database database) {
        String fileName;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileName = databaseFolder.getPath() + "/database.bin";
            fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(database);
            logger.info("database saved.");
            objectOutputStream.flush();
            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            logger.info("database save failed.\n" + e.toString()+ e.getCause());
        } finally {
            refreshFilesInFolder(databaseFolder);
        }

    }


    public Database loadDatabase() throws NoSuchAlgorithmException {
        refreshFilesInFolder(databaseFolder);
        Database database = new Database();
        try {
            FileInputStream fileInputStream = null;
            ObjectInputStream objectInputStream = null;

            if (filesInDatabaseFolder != null)
                try {
                    File file = filesInDatabaseFolder[0];
                    fileInputStream = new FileInputStream(file.getPath());
                    objectInputStream = new ObjectInputStream(fileInputStream);
                    database = (Database) objectInputStream.readObject();
                    logger.info("database read.");
                    objectInputStream.close();
                    fileInputStream.close();
                } catch (IOException | ClassNotFoundException e) {
                    logger.info("database load failed.\n" + e.toString()+ e.getCause());

                }

        } finally {
            refreshFilesInFolder(databaseFolder);
        }
        return database;
    }


    private void refreshFilesInFolder(File Folder) {
        filesInDatabaseFolder = Folder.listFiles();
        logger.info("folder: "+ Folder.getName() +"refreshed.");
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


    private void initLogger() {
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        File logFolder = new File(props.getProperty("server.log.file"));
        if (!logFolder.exists()) {

            logFolder.mkdir();

        }

        FileHandler fh;
        try {
            fh = new FileHandler(logFolder.getPath() + "/log.log");
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.addHandler(fh);

        } catch (IOException e) {
            e.printStackTrace();
        }

    logger.info("properties read and logger started.");
    }



    private void readDatabaseFolder() {

        databaseFolder = new File(props.getProperty("server.database.file"));
        if (!databaseFolder.exists()) {
            databaseFolder.mkdir();
        }
        logger.info("database Folder Successfully read.");
    }

}