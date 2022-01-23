package org.runApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.ce.ap.impl.client.ConnectionServiceImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MessageController{
    FXMLCommandParserServiceImpl fxmlCommandParserServiceImpl = App.fxmlCommandParserService ;

    @FXML
    private Button Like;

    @FXML
    private Button Retweet;

    @FXML
    private TextField idTweet;

    @FXML
    void LikeTweet(ActionEvent event) throws IOException {
        System.out.println(idTweet.getText());
       fxmlCommandParserServiceImpl.requestToLike(idTweet.getText());
      App.setScene(fxmlCommandParserServiceImpl.requestTimeline());

    }

    @FXML
    void RetweetTweet(ActionEvent event) {

    }

//
//    public void setFxmlCommandParserService(FXMLCommandParserServiceImpl fxmlCommandParserService){
//        this.fxmlCommandParserServiceImpl  =fxmlCommandParserService;
//    }

//    @FXML
//    public void initialize() throws IOException, ClassNotFoundException {
//
//        // File file = new File("connectionService.bin");
//        FileInputStream fileInputStream = new FileInputStream("connectionService.bin");
//        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
////        database = (Database) objectInputStream.readObject();
////        logger.info("database read.");
////        objectInputStream.close();
////        fileInputStream.close();
//
//
//
//        ConnectionServiceImpl connectionService = (ConnectionServiceImpl) objectInputStream.readObject();
//
//        fxmlCommandParserService.handleConnectionServiceImpl(connectionService);
//        objectInputStream.close();
//        fileInputStream.close();
//
//
//
//    }

}