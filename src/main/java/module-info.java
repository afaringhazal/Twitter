module Twitter {

    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.logging;


    opens Twitter to javafx.fxml,com.google.gson;
    opens org.ce.ap to javafx.fxml,com.google.gson;
    exports Twitter;
}