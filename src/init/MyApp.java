package init;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MyApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MyApp.class.getResource("/mco2_time.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("does this work?");
        stage.setScene(scene);
        stage.show();
    }
}
