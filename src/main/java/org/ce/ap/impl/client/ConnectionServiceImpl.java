package main.java.org.ce.ap.impl.client;

import main.java.org.ce.ap.client.ConnectionService;

import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

public class ConnectionServiceImpl implements ConnectionService {

    private Socket socket;
    private ObjectOutputStream objectOutputStream = null;
    public ObjectInputStream objectInputStream = null;
    Properties props = new Properties();

    public ConnectionServiceImpl() {
        readProps();
        connectToServer();

    }

    @Override
    public String receiveFromServer() throws IOException, ClassNotFoundException {
        return (String) objectInputStream.readObject();


    }

    @Override
    public void sendToServer(String s) throws IOException {
        objectOutputStream.writeObject(s);
        System.out.println("sent to server");


    }

    @Override
    public String stop() throws IOException {
        objectInputStream.close();
        objectOutputStream.close();
        socket.close();

        return "Done Client";
    }

    @Override
    public void readProps() {

        try {
            FileReader reader;
            reader = new FileReader("src/main/resources/client-application.properties");
            props.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void connectToServer() {
        try {
            socket = new Socket("127.0.0.1", Integer.parseInt((String) props.get("server.port")));
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Couldn't connect to server,Please try again later.");
        }


    }

    @Override
    public boolean socketIsConnected() {
        if (socket==null){
            return false;
        }
        return socket.isConnected();
    }

}
