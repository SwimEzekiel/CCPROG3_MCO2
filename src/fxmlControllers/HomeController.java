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
    @FXML private ListView<MediaEntry> collectionList;
    @FXML private CheckMenuItem showCards;
    @FXML private CheckMenuItem showSeries;
    @FXML private CheckMenuItem showSites;

    // Attributes
    private User curU;
    private MediaEntry cur;
    private String filters = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Prepare detailed entry screen
        collectionList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MediaEntry>(){
            @Override
            public void changed(ObservableValue<? extends MediaEntry> observableValue, MediaEntry mediaEntry, MediaEntry t1) {
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
                EntryController entry = loader.getController();
                entry.setTitleLabel(cur.getTitle());
                entry.setEntry(cur);
                entry.setHome(HomeController.this);
                viewEntryStage = new Stage();
                viewEntryStage.setTitle("Detailed View");
                viewEntryStage.setScene(viewEntryScene);
                viewEntryStage.initModality(Modality.APPLICATION_MODAL);
                viewEntryStage.showAndWait();
            }
        });
        login();
    }

    public void login(){
        // Prepare login screen
        FXMLLoader loader = new FXMLLoader(MyApp.class.getResource("/login.fxml"));
        Scene loginScene = null;
        try {
            loginScene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LoginController lc = loader.getController();
        lc.setHome(this);

        Stage loginStage = new Stage();
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
        for (MediaEntry entry : curU.getCollection()){
            if (entry instanceof CardGame && filters.contains("c")) collectionList.getItems().add(entry);
            else if (entry instanceof TVSeries && filters.contains("t")) collectionList.getItems().add(entry);
            else if (entry instanceof Website && filters.contains("w")) collectionList.getItems().add(entry);
        }
    }
    public void delete(MediaEntry entry){
        collectionList.getItems().remove(entry);
        curU.getCollection().remove(entry);
    }
    public void setCurU(User curU){
        this.curU = curU;
    }

    public void openAddForCG() throws IOException{
        FXMLLoader loader = new FXMLLoader(MyApp.class.getResource("/addEntry.fxml"));
        Scene sc = new Scene(loader.load());

        AddController add = loader.getController();
        add.setCollection(curU.getCollection());
        add.field2.setPromptText("Input price");
        add.field3.setPromptText("Input publisher");
        add.titleLabel.setText("Add a Card Game");
        add.standaloneCheck.setOpacity(0.00);
        add.standaloneCheck.setManaged(false);
        add.setType('c');

        Stage st = new Stage();
        st.setTitle("Adding an entry...");
        st.initModality(Modality.APPLICATION_MODAL);
        st.setScene(sc);
        st.showAndWait();
        updateListView();
    }

    public void openAddForTV() throws IOException{
        FXMLLoader loader = new FXMLLoader(MyApp.class.getResource("/addEntry.fxml"));
        Scene sc = new Scene(loader.load());

        AddController add = loader.getController();
        add.setCollection(curU.getCollection());
        add.field2.setPromptText("Input author");
        add.field3.setPromptText("Input year released");
        add.titleLabel.setText("Add a TV Series");
        add.standaloneCheck.setOpacity(0.00);
        add.standaloneCheck.setManaged(false);
        add.initializeSpinner();
        add.setType('t');

        Stage st = new Stage();
        st.setTitle("Adding an entry...");
        st.initModality(Modality.APPLICATION_MODAL);
        st.setScene(sc);
        st.showAndWait();
        updateListView();
    }

    public void openAddForWS() throws IOException{
        FXMLLoader loader = new FXMLLoader(MyApp.class.getResource("/addEntry.fxml"));
        Scene sc = new Scene(loader.load());

        AddController add = loader.getController();
        add.setCollection(curU.getCollection());
        add.field2.setPromptText("Input URL");
        add.field3.setPromptText("Input publish date (DD-MM-YYYY)");
        add.titleLabel.setText("Add a Website");
        add.standaloneCheck.setOpacity(0.00);
        add.standaloneCheck.setManaged(false);
        add.setType('w');

        Stage st = new Stage();
        st.setTitle("Adding an entry...");
        st.initModality(Modality.APPLICATION_MODAL);
        st.setScene(sc);
        st.showAndWait();
        updateListView();
    }

    public void logout(){
        showCards.setSelected(false);
        showSeries.setSelected(false);
        showSites.setSelected(false);
        curU = null;
        login();
    }
}

// TODO: implement add episode
// TODO: fix login logout bugs
// TODO: start documentation