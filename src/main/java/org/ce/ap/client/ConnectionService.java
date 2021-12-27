package main.java.org.ce.ap.client;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;




public class ConnectionService {
    private static Socket socket ;
    private static ObjectOutputStream objectOutputStream = null;
    public static ObjectInputStream objectInputStream = null;

    public ConnectionService() throws IOException {

            socket = new Socket("127.0.0.1", 1234);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

    }

    public String connectServerToCommandParser() throws IOException, ClassNotFoundException {
        return (String) objectInputStream.readObject();


    }

   public void connectCommandParserToServer(String s) throws IOException {
        objectOutputStream.writeObject(s);
    //   objectOutputStream.writeObject("ffff");
   }
    public void connectCommandParserToServer(JSONObject s) throws IOException {
        objectOutputStream.writeObject(s);
        //   objectOutputStream.writeObject("ffff");
    }
    public String stop() throws IOException {
        objectInputStream.close();
        objectOutputStream.close();
        socket.close();

        return "Done Client";
    }

}
