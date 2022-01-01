package main.java.org.ce.ap.client;

import java.io.IOException;

/**
 * The interface Connection service.
 */
public interface ConnectionService {

    /**
     * Receive from server string.
     *
     * @return the string
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
     String receiveFromServer() throws IOException, ClassNotFoundException ;

     /**
     * Send to server.
     *
     * @param s the s
     * @throws IOException the io exception
     */
     void sendToServer(String s) throws IOException ;


    /**
     * Stop string.
     *
     * @return the string
     * @throws IOException the io exception
     */
     String stop() throws IOException ;


    /**
     * Read props.
     */
     void readProps();


    /**
     * Connect to server.
     */
     void connectToServer();

    /**
     * Socket is connected boolean.
     *
     * @return the boolean
     */
     boolean socketIsConnected();

}
