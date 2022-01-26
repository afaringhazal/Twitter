package org.runApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {


    private static Scene scene;
    public  static FXMLCommandParserServiceImpl fxmlCommandParserService=new FXMLCommandParserServiceImpl();



    private static Stage stage1;

    public static Stage f() {
        return stage1;
    }

    @Override
    public void start(Stage stage) throws IOException {

        stage1 = stage;

        scene = new Scene(loadFXML("login"), 640, 480);
        stage.setScene(scene);
        fxmlCommandParserService.checkFastLogin();
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        return fxmlLoader.load();
    }

     static void setScene(Parent root) {
        scene.setRoot(root);
    }



    public static void main(String[] args) {
        launch();
    }
}

class MyAppLauncher {
    public static void main(String[] args) {
        App.main(args);
    }

}