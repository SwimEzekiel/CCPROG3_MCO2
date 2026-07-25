package fxmlControllers;

import init.MyApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private MenuBar menu;

    private FXMLLoader fxmlLoader;
    private Scene scene;
    private Stage loginStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fxmlLoader = new FXMLLoader(MyApp.class.getResource("/login.fxml"));
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loginStage = new Stage();
        loginStage.setTitle("Login");
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.setScene(scene);
    }

    public void login(){
        loginStage.showAndWait();
    }
}
