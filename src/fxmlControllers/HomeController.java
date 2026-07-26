package fxmlControllers;

import init.MyApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private ListView<String> collectionList;

    private FXMLLoader fxmlLoader;
    private Scene loginScene;
    private Stage loginStage;

    private String cur;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Prepare login screen
        fxmlLoader = new FXMLLoader(MyApp.class.getResource("/login.fxml"));
        try {
            loginScene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loginStage = new Stage();
        loginStage.setTitle("Login");
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.setScene(loginScene);


        // Initialize list view
        for (int i = 0; i < 10; i++) {
            collectionList.getItems().add("Placeholder " + i);
        }
        // Prepare detailed entry screen
        collectionList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                cur = collectionList.getSelectionModel().getSelectedItem();

                Scene viewEntryScene;
                Stage viewEntryStage;
                fxmlLoader.setRoot(null);
                fxmlLoader.setController(null);
                fxmlLoader.setLocation(MyApp.class.getResource("/viewEntry.fxml"));
                try {
                    viewEntryScene = new Scene(fxmlLoader.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                viewEntryStage = new Stage();
                EntryController entry = fxmlLoader.getController();
                entry.setTitle(cur);
                viewEntryStage.setTitle("Detailed View");
                viewEntryStage.setScene(viewEntryScene);
                viewEntryStage.show();
            }
        });
    }

    public void login(){
        loginStage.showAndWait();
    }
}
