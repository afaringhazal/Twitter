package org.runApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.ce.ap.ExceptionNoConnection;
import org.ce.ap.ExceptionNotValidInput;

import java.io.IOException;
import java.time.LocalDate;

public class EditPageController {
    FXMLCommandParserServiceImpl fxmlCommandParserServiceImpl = App.fxmlCommandParserService;

    @FXML
    private TextField FirstName;


    @FXML
    private TextField LastName;

    @FXML
    private TextField IdOfPage;

    @FXML
    private DatePicker Birthday;

    @FXML
    private Label HasError;

    @FXML
    private TextField BioOfPage;



    @FXML
    void ButtonOfBack(ActionEvent event) throws IOException {
        fxmlCommandParserServiceImpl.getPageInformation(null);
    }

    @FXML
    void ButtonOfConform(ActionEvent event) throws IOException {

        LocalDate date = Birthday.getValue();

        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        int check = 0;

        try {
            fxmlCommandParserServiceImpl.editPage( FirstName.getText(),
                    LastName.getText(), String.valueOf(year), String.valueOf(month), String.valueOf(day), IdOfPage.getText(), BioOfPage.getText());


        } catch (ExceptionNotValidInput e) {
            HasError.setText("Username already taken.\n Please try again.");
            return;
        } catch (ExceptionNoConnection e) {
            HasError.setText("Invalid Input!.\n Please try again.");
            return;
        }

        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Designed_Menu.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.setController(fxmlCommandParserServiceImpl.menuController);
        App.setScene(root);
    }

}
