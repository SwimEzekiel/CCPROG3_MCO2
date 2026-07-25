package init;

import fxmlControllers.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MyApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MyApp.class.getResource("/homePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Media Vault");
        stage.setScene(scene);
        stage.show();
    }
}
