package main.java.org.ce.ap.server;

import java.io.*;
import java.security.NoSuchAlgorithmException;

public class FileManagement {

    private File Folder = null;
    private File filesInFolder[] = null;


    public FileManagement() {
        Folder = new File("Notes");
        if (!Folder.exists()) {
            Folder.mkdir();
        }

        filesInFolder = Folder.listFiles();
    }


    public void saveAll(Database database) {
        String fileName = "Saves.bin";
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(database);
            objectOutputStream.flush();
            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            refreshFilesInFolder();
        }

    }


    public Database loadAll() throws NoSuchAlgorithmException {
        refreshFilesInFolder();
        Database database = new Database();
        try {

            FileInputStream fileInputStream = null;
            ObjectInputStream objectInputStream = null;

            if (filesInFolder != null)
                try {
                    File file = filesInFolder[0];
                    fileInputStream = new FileInputStream(file.getCanonicalPath());
                    objectInputStream = new ObjectInputStream(fileInputStream);

                    database = (Database) objectInputStream.readObject();


                    objectInputStream.close();
                    fileInputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

        } finally {
            refreshFilesInFolder();
        }
        return database;
    }



    public void deleteSaves() {
        refreshFilesInFolder();
        if (filesInFolder != null) {
            filesInFolder[0].delete();
        }
    }


    private void refreshFilesInFolder() {
        filesInFolder =
                Folder.listFiles();
    }


}