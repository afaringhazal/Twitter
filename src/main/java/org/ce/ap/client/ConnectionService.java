package main.java.org.ce.ap.client;

import java.io.IOException;


public interface ConnectionService {

    public String receiveFromServer() throws IOException, ClassNotFoundException ;


   public void sendToServer(String s) throws IOException ;


    public String stop() throws IOException ;


    public void readProps();


    public void connectToServer();

}
