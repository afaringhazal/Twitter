package main.java.org.ce.ap.server;

import java.io.*;
import java.security.NoSuchAlgorithmException;

public interface FileManagement {


    public void saveDatabase(Database database);

    public Database loadDatabase() throws NoSuchAlgorithmException;

    public void refreshFilesInFolder(File Folder);


    public void readProps();


    public void initLogger();


    public void readDatabaseFolder();


    public void initFolders();
}