package main.java.org.ce.ap.server;

import main.java.org.ce.ap.impl.server.TweetingServiceImpl;

import java.io.*;
import java.security.NoSuchAlgorithmException;

/**
 * The interface File management.
 */
public interface FileManagement {


    /**
     * Refresh files in folder.
     *
     * @param Folder the folder
     */
     void refreshFilesInFolder(File Folder);


    /**
     * Read props.
     */
     void readProps();
     /**
     * Init logger.
     */
     void initLogger();

     /**
     * Read database folder.
     */
     void readDatabaseFolder();


    /**
     * Init folders.
     */
     void initFolders();

    /**
     * Save database.
     *
     * @param database        the database
     * @param tweetingService the tweeting service
     */
     void saveDatabase(Database database, TweetingServiceImpl tweetingService);

    /**
     * Load database database.
     *
     * @param tweetingService the tweeting service
     * @return the database
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
     Database loadDatabase(TweetingService tweetingService) throws NoSuchAlgorithmException;


    /**
     * Init tweeting service.
     *
     * @param tweetingService the tweeting service
     */
     void initTweetingService(TweetingServiceImpl tweetingService);
}