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

    @FXML
    private Label Error;

    @FXML
    private TextField PasswordId;

    @FXML
    private TextField UserNameId;

    @FXML
    void loginButton(ActionEvent event) throws IOException {
        try {
            fxmlCommandParserServiceImpl.connectionService.connectToServer();

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
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Designed_Menu.fxml"));
        Parent root = fxmlLoader.load();
        fxmlCommandParserServiceImpl.menuController=fxmlLoader.getController();

        App.setScene(root);

    }

}
