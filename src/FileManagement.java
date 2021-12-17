import java.io.*;

public class FileManagement {

    private File notesFolder = null;
    private File filesInFolder[] = null;


    public FileManagement() {
        notesFolder = new File("Notes");
        if (!notesFolder.exists()) {
            notesFolder.mkdir();
        }

        filesInFolder = notesFolder.listFiles();
    }


    /** saves the server data
     * @param server server data
     */
    public void saveAll(Server server) {
        String fileName = "Saves.bin";
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(server);
            objectOutputStream.flush();
            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            refreshFilesInFolder();
        }

    }


    /**loads the server data
     * @return returns server data
     */

    public Server loadAll() {
        refreshFilesInFolder();
        Server server = null;
        try {

            FileInputStream fileInputStream = null;
            ObjectInputStream objectInputStream = null;

            if (filesInFolder != null)
                try {
                    File file = filesInFolder[0];
                    fileInputStream = new FileInputStream(file.getCanonicalPath());
                    objectInputStream = new ObjectInputStream(fileInputStream);

                    server = (Server) objectInputStream.readObject();


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
        return server;
    }


    /**
     *deletes saves if existing
     */

    public void deleteSave() {
        refreshFilesInFolder();
        if (filesInFolder != null) {
            filesInFolder[0].delete();
        }
    }


    /**
     *refreshes the files in folder
     */
    private void refreshFilesInFolder() {
        filesInFolder =
                notesFolder.listFiles();
    }


}