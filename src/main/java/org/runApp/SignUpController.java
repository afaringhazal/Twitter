package org.runApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.ce.ap.ExceptionNoConnection;
import org.ce.ap.ExceptionNotValidInput;

import java.io.IOException;
import java.time.LocalDate;

public class SignUpController {
    FXMLCommandParserServiceImpl fxmlCommandParserServiceImpl = App.fxmlCommandParserService;

    @FXML
    private TextField BioOfPage;

    @FXML
    private DatePicker Birthday;


    @FXML
    private TextField FirstName;

    @FXML
    private TextField IdOfPage;

    @FXML
    private TextField LastName;

    @FXML
    private TextField Password;

    @FXML
    private TextField UserNameId;

    @FXML
    private Label HasError;


    @FXML
    void ButtonOfBack(ActionEvent event) throws IOException {
        App.setScene(FXMLLoader.load(getClass().getResource("login.fxml")));
    }

    @FXML
    void ButtonOfSignUp(ActionEvent event) throws IOException {

        LocalDate date = Birthday.getValue();

        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        int check = 0;

        try {
            fxmlCommandParserServiceImpl.processSignUp(UserNameId.getText(), Password.getText(), FirstName.getText(),
                    LastName.getText(), String.valueOf(year), String.valueOf(month), String.valueOf(day), IdOfPage.getText(), BioOfPage.getText());


        } catch (ExceptionNotValidInput e) {
            HasError.setText("Username already taken.\n Please try again.");
            return;
        } catch (ExceptionNoConnection e) {
            HasError.setText("Invalid Input!.\n Please try again.");
           return;
        }

        App.setScene(fxmlCommandParserServiceImpl.requestTimeline());


    }

}