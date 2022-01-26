package org.runApp;


import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class SearchBarController {
    FXMLCommandParserServiceImpl fxmlCommandParserService=App.fxmlCommandParserService;
    @FXML
    private Button searchButton;

    @FXML
    private TextField searchText;

    @FXML
    private VBox SearchResults;


    @FXML
    private void searchButtonFunction() throws IOException {
        SearchResults.getChildren().clear();
        SearchResults.getChildren().addAll(fxmlCommandParserService.searchByString(searchText.getText()));



    }


}
