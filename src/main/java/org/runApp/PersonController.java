package org.runApp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
public class PersonController {
    FXMLCommandParserServiceImpl fxmlCommandParserServiceImpl =App.fxmlCommandParserService;

    @FXML
    private Image profileImage;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label IdLabel;


    public void showPageMenu(MouseEvent e) throws IOException {
    fxmlCommandParserServiceImpl.getPageInformation(usernameLabel.getText());

    }


    public void getData(ArrayList<Object> response) {

        usernameLabel.setText((String) response.get(0));
        IdLabel.setText((String) response.get(1));
        // profileImage = (Image) response.get(2);
    }
}
