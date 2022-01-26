package org.runApp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.ArrayList;

public class search_PersonController {
FXMLCommandParserServiceImpl fxmlCommandParserServiceImpl=App.fxmlCommandParserService;
    @FXML
    private Label ClientUserName;

    @FXML
    private Circle Picture;

    @FXML
    private Label idClient;

    @FXML
    public void showPageMenu(MouseEvent e) throws IOException {
        fxmlCommandParserServiceImpl.menuController.showPage(ClientUserName.getText());
    }

    void getData(ArrayList<Object> response) throws IOException {

        String  username= (String) response.get(0);
        String  Id= (String) response.get(5);

        this.ClientUserName.setText(username);
        idClient.setText("@"+Id);
    }

}
