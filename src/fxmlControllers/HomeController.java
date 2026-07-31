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

    // FXML Injections
    @FXML
    private ListView<String> collectionList;

    // Other windows
    private Scene loginScene;
    private Stage loginStage;

    // Attributes
    private String cur;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize list view
        for (int i = 0; i < 10; i++) {
            collectionList.getItems().add("Placeholder " + i);
        }
        // Prepare detailed entry screen
        collectionList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                FXMLLoader loader = new FXMLLoader();
                cur = collectionList.getSelectionModel().getSelectedItem();

                Scene viewEntryScene;
                Stage viewEntryStage;
                loader.setLocation(MyApp.class.getResource("/viewEntry.fxml"));
                try {
                    viewEntryScene = new Scene(loader.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                viewEntryStage = new Stage();
                EntryController entry = loader.getController();
                entry.setTitle(cur);
                viewEntryStage.setTitle("Detailed View");
                viewEntryStage.setScene(viewEntryScene);
                viewEntryStage.show();
            }
        });
    }

    public void login(){
        // Prepare login screen
        FXMLLoader loader = new FXMLLoader(MyApp.class.getResource("/login.fxml"));
        try {
            loginScene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loginStage = new Stage();
        loginStage.setTitle("Login");
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.setScene(loginScene);
        loginStage.showAndWait();
    }
    public void openExport() throws IOException{
        Scene exportScene;
        Stage exportStage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(MyApp.class.getResource("/export.fxml"));
        exportScene = new Scene(loader.load());
        exportStage.initModality(Modality.APPLICATION_MODAL);
        exportStage.setScene(exportScene);
        exportStage.setTitle("Exporting...");
        exportStage.showAndWait();
    }
    public void openImport() throws IOException{
        Scene importScene;
        Stage importStage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(MyApp.class.getResource("/import.fxml"));
        importScene = new Scene(loader.load());

        EntryController entry = loader.getController();
        entry.setHome(this);

        importStage.initModality(Modality.APPLICATION_MODAL);
        importStage.setScene(importScene);
        importStage.setTitle("Importing...");
        importStage.showAndWait();
    }

    public void delete(String placeholder){
        collectionList.getItems().remove(placeholder);
    }
}
