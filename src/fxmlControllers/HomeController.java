package fxmlControllers;

import init.MyApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    // FXML Injections
    @FXML private ListView<String> collectionList;
    @FXML private CheckMenuItem showCards;
    @FXML private CheckMenuItem showSeries;
    @FXML private CheckMenuItem showSites;

    // Other windows
    private Scene loginScene;
    private Stage loginStage;

    // Attributes
    private User curU;
    private String cur;
    private String filters;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    public void login() throws IOException{
        // Prepare login screen
        FXMLLoader loader = new FXMLLoader(MyApp.class.getResource("/login.fxml"));
        loginScene = new Scene(loader.load());

        LoginController lc = loader.getController();
        lc.setHome(this);

        loginStage = new Stage();
        loginStage.setTitle("Login");
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.setScene(loginScene);
        loginStage.showAndWait();
        collectionList.getItems().clear();
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

    public void updateView(){
        StringBuilder sb = new StringBuilder();

        if (showCards.isSelected()) sb.append('c');
        if (showSeries.isSelected()) sb.append('t');
        if (showSites.isSelected()) sb.append('w');

        filters = sb.toString();
        updateListView();
    }
    private void updateListView(){
        collectionList.getItems().clear();
        if (filters.contains("c")){
            for (CardGame entry : curU.getCollection().getCGCollection()){
                collectionList.getItems().add(entry.getTitle());
            }
        }
        if (filters.contains("t")){
            for (TVSeries entry : curU.getCollection().getTVCollection()){
                collectionList.getItems().add(entry.getTitle());
            }
        }
        if (filters.contains("w")){
            for (Website entry : curU.getCollection().getWSCollection()){
                collectionList.getItems().add(entry.getTitle());
            }
        }
    }
    public void delete(String placeholder){
        collectionList.getItems().remove(placeholder);
    }
    public void setCurU(User curU){
        this.curU = curU;
    }
}
