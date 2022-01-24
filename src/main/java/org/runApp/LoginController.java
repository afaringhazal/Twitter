//package org.runApp;
//
//public class LoginController {
//}
package org.runApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.ce.ap.impl.client.ConnectionServiceImpl;
import org.ce.ap.server.Database;

import java.io.*;

public class LoginController {
    FXMLCommandParserServiceImpl fxmlCommandParserServiceImpl = App.fxmlCommandParserService ;
    //new FXMLCommandParserServiceImpl();

//    public LoginController(FXMLCommandParserServiceImpl fxmlCommandParserService){
//        this.fxmlCommandParserService =fxmlCommandParserService;
//    }

    @FXML
    private Label Error;

    @FXML
    private TextField PasswordId;

    @FXML
    private TextField UserNameId;

    @FXML
    void loginButton(ActionEvent event) throws IOException {
        try {
            fxmlCommandParserServiceImpl.processSignIn(UserNameId.getText(),PasswordId.getText());

        }
        catch (RuntimeException e){
            UserNameId.setText("");
            PasswordId.setText("");
            Error.setText("Don't exit user name");
            return;
        }
        // fxmlCommandParserServiceImpl.processSignIn(UserNameId.getText(),PasswordId.getText());
        //  Stage stage = (Stage) loginButtonId.getScene().getWindow();
        timeLine();

    }

    @FXML
    void signUpButton(ActionEvent event) throws IOException {

        Parent node = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        App.setScene(node);

    }

    @FXML
    void ExitButton(ActionEvent event) {

    }


    public void timeLine() throws IOException {

        App.setScene(fxmlCommandParserServiceImpl.requestTimeline());
        // Scene scene = new Scene(fxmlCommandParserService.requestTimeline());
        //Stage SecondaryStage = new Stage();
//        stage.setScene(scene);
//        stage.show();

    }


//    public void setFxmlCommandParserService(FXMLCommandParserServiceImpl fxmlCommandParserService){
//        this.fxmlCommandParserService =fxmlCommandParserService;
//    }




//
//    @FXML
//    public void initialize() throws IOException, ClassNotFoundException {
//
//       // File file = new File("connectionService.bin");
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
