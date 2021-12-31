package main.java.org.ce.ap.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;







public class ConnectionService {
    private  Socket socket ;
    private  ObjectOutputStream objectOutputStream = null;
    public  ObjectInputStream objectInputStream = null;

    public ConnectionService() throws IOException {

        socket = new Socket("127.0.0.1", 1111);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        System.out.println("Connected!");

    }
    public String receiveFromServer() throws IOException, ClassNotFoundException {
        return (String) objectInputStream.readObject();


    }

   public void sendToServer(String s) throws IOException {
        objectOutputStream.writeObject(s);
       System.out.println("sent to server");


   }

    public String stop() throws IOException {
        objectInputStream.close();
        objectOutputStream.close();
        socket.close();

        return "Done Client";
    }

}
