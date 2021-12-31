package main.java.org.ce.ap.server;

import main.java.org.ce.ap.impl.server.TweetingServiceImpl;

import java.io.*;
import java.security.NoSuchAlgorithmException;

public interface FileManagement {




    public void refreshFilesInFolder(File Folder);


    public void readProps();


    public void initLogger();


    public void readDatabaseFolder();


    public void initFolders();

    public void saveDatabase(Database database, TweetingServiceImpl tweetingService);

    public Database loadDatabase(TweetingService tweetingService) throws NoSuchAlgorithmException;


    public void initTweetingService(TweetingServiceImpl tweetingService);
}