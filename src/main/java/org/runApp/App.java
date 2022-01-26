package org.runApp;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.ce.ap.client.ConnectionService;
import org.ce.ap.impl.client.ConnectionServiceImpl;
import org.ce.ap.impl.server.TweetingServiceImpl;
import org.ce.ap.server.Database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * JavaFX App
 */
public class App extends Application {

//    ConnectionServiceImpl connectionServiceImpl = new ConnectionServiceImpl();
//
//
//    public void saveConnectionService(ConnectionServiceImpl connectionService) {
//        String fileName;
//        FileOutputStream fileOutputStream = null;
//        ObjectOutputStream objectOutputStream = null;
//
//        try {
//            fileName = "connectionService.bin";
//            fileOutputStream = new FileOutputStream(fileName);
//            objectOutputStream = new ObjectOutputStream(fileOutputStream);
//            Gson gson = new Gson();
//            String s = gson.toJson(connectionService);
//            objectOutputStream.writeObject(s);
////            logger.info("database saved.");
////            props.setProperty("numberOfMessages", tweetingService.counter + "");
////            String propsName = "src/main/resources/server-application.properties";
////            props.store(new FileOutputStream(propsName), null);
//            objectOutputStream.flush();
//            objectOutputStream.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
////            logger.info("database save failed.\n");
//
//        }
//
//    }


    private static Scene scene;
    public final static FXMLCommandParserServiceImpl fxmlCommandParserService = new FXMLCommandParserServiceImpl();







    //LoginController loginController = new LoginController();
    //FXMLCommandParserServiceImpl fxmlCommandParserService = new FXMLCommandParserServiceImpl();
//    @Override
//    public void init(){
//         fxmlCommandParserService = new FXMLCommandParserServiceImpl();
//
//
//
//
//    }

private static Stage stage1 ;

public  static Stage f(){
    return stage1;
}

    @Override
    public void start(Stage stage) throws IOException {

        stage1 = stage;
     //   saveConnectionService(connectionServiceImpl);
        scene = new Scene(loadFXML("login"), 640, 480);
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        //fxmlLoader.setController(loginController);
//        LoginController loginController = fxmlLoader.getController();
//        loginController.setFxmlCommandParserService(fxmlCommandParserService);

      //  scene = new Scene(fxmlLoader.load(),640,480);
       // loginController.setFxmlCommandParserService(fxmlCommandParserService);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
//      LoginController loginController= (LoginController) fxmlLoader.getController();
//       loginController.setFxmlCommandParserService(fxmlCommandParserService);
        return fxmlLoader.load();
    }

    static void setScene(Parent root){
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

