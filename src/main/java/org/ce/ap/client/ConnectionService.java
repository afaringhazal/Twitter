package main.java.org.ce.ap.client;

import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;


public class ConnectionService {
    private  Socket socket ;
    private  ObjectOutputStream objectOutputStream = null;
    public  ObjectInputStream objectInputStream = null;
    Properties props=new Properties();

    public ConnectionService()  {


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

    private void readProps(){

        try {
            FileReader reader;
            reader=new FileReader("src/main/resources/client-application.properties");
            props.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void connectToServer(){
        try {
            socket = new Socket("127.0.0.1", Integer.parseInt((String)props.get("server.port")));
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
