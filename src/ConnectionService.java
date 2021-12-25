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

    public JSONObject connectServerToCommandParser() throws IOException, ClassNotFoundException {
        return (JSONObject) objectInputStream.readObject();


    }

   public void connectCommandParserToServer(JSONObject jsonObject) throws IOException {

       objectOutputStream.writeObject(jsonObject);
   }

}
