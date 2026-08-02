package fxmlControllers;

import init.MyApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class EntryController implements Initializable {

    @FXML private AnchorPane anchor;
    @FXML private Label titleLabel;
    @FXML private Label ratingLabel;
    @FXML private TextArea reviewArea;
    @FXML private ListView<String> detailsList;
    @FXML private ListView<MediaEntry> containedList;
    @FXML private Label relatedMediaLabel;
    @FXML private Button ratingButton;
    @FXML private Line relatedMediaLine;
    @FXML private Button addSub;
    private MediaEntry entry;
    private MediaEntry selSub;
    private HomeController home;
    private EntryController container;

    public void setTitleLabel(String titleLabel){
        this.titleLabel.setText(titleLabel);
    }
    public void setHome(HomeController home){
        this.home = home;
    }
    public void setEntry(MediaEntry entry){
        this.entry = entry;
        updateDetails();
    }
    public void setSelSub(MediaEntry selSub){
        this.selSub = selSub;
    }
    public void setContainer(EntryController container){
        this.container = container;
    }

    private void updateDetails(){
        if (entry instanceof CardGame){
            detailsList.getItems().add("Price: " + ((CardGame) entry).getPrice());
            detailsList.getItems().add("Publisher: " + ((CardGame) entry).getPublisher());

            relatedMediaLabel.setText("Expansion Decks");
            addSub.setOnAction(this::openAddForEX);
            addSub.setText("Add an Expansion");
            for (Expansion e : ((CardGame) entry).getExpansions()){
                containedList.getItems().add(e);
            }
        } else if (entry instanceof TVSeries){
            detailsList.getItems().add("Author: " + ((TVSeries) entry).getAuthor());
            detailsList.getItems().add("Year Released: " + ((TVSeries) entry).getYearReleased());

            relatedMediaLabel.setText("Episodes");
            addSub.setOnAction(this::openAddForEP);
            addSub.setText("Add an Episode");
            for (ArrayList<Episodes> season : ((TVSeries) entry).getEpisodes()){
                for (Episodes e : season){
                    containedList.getItems().add(e);
                }
            }
        } else if (entry instanceof Website){
            relatedMediaLabel.setVisible(false);
            relatedMediaLine.setVisible(false);
            addSub.setVisible(false);
            addSub.setDisable(true);
            containedList.setVisible(false);
            containedList.setEditable(false);

            detailsList.getItems().add("URL: " + ((Website) entry).getURL());
            detailsList.getItems().add("Publish Date: " + ((Website) entry).getPublishDate());
        } else if (entry instanceof Expansion){
            relatedMediaLabel.setVisible(false);
            relatedMediaLine.setVisible(false);
            addSub.setVisible(false);
            addSub.setDisable(true);
            containedList.setVisible(false);
            containedList.setEditable(false);

            detailsList.getItems().add("Price: " + ((Expansion) entry).getPrice());
        } else if (entry instanceof Episodes){
            relatedMediaLabel.setVisible(false);
            relatedMediaLine.setVisible(false);
            addSub.setVisible(false);
            addSub.setDisable(true);
            containedList.setVisible(false);
            containedList.setEditable(false);

            detailsList.getItems().add("Runtime (in mins): " + ((Episodes) entry).getRunTime());
        }

        int rating = entry.getRating();
        String review = entry.getReview();

        Status s = entry.getStatus();
        detailsList.getItems().add(switch (s){
            case Status.PLANNED -> "Status: Planned.";
            case Status.IN_PROGRESS -> "Status: In Progress.";
            case Status.COMPLETED -> "Status: Completed.";
            default -> "ERR";
        });

        if (rating != -1) ratingLabel.setText(String.valueOf(rating));
        if (review != null && !review.isEmpty()) {
            ratingButton.setOpacity(0.00);
            ratingButton.setDisable(true);
            reviewArea.setText(review);
            reviewArea.setOpacity(1.00);
        }

        if (entry instanceof CardGame && !((CardGame) entry).getExpansions().isEmpty()){
            containedList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MediaEntry>(){
                @Override
                public void changed(ObservableValue<? extends MediaEntry> observableValue, MediaEntry mediaEntry, MediaEntry t1) {
                    Expansion sel = (Expansion) containedList.getSelectionModel().getSelectedItem();
                    FXMLLoader loader = new FXMLLoader(MyApp.class.getResource("/viewEntry.fxml"));
                    Scene sc;
                    try {
                        sc = new Scene(loader.load());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Stage st = new Stage();

                    EntryController ec = loader.getController();
                    ec.setSelSub(sel);
                    ec.setContainer(EntryController.this);
                    ec.setTitleLabel(sel.getTitle());
                    ec.setEntry(sel);

                    st.initModality(Modality.APPLICATION_MODAL);
                    st.setScene(sc);
                    st.showAndWait();
                }
            });
        } else if (entry instanceof TVSeries && !((TVSeries) entry).getEpisodes().isEmpty()){
            containedList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MediaEntry>(){
                @Override
                public void changed(ObservableValue<? extends MediaEntry> observableValue, MediaEntry mediaEntry, MediaEntry t1) {
                    Episodes sel = (Episodes) containedList.getSelectionModel().getSelectedItem();
                    FXMLLoader loader = new FXMLLoader(MyApp.class.getResource("/viewEntry.fxml"));
                    Scene sc;
                    try {
                        sc = new Scene(loader.load());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Stage st = new Stage();

                    EntryController ec = loader.getController();
                    ec.setSelSub(sel);
                    ec.setContainer(EntryController.this);
                    ec.setTitleLabel(sel.getTitle());
                    ec.setEntry(sel);

                    st.initModality(Modality.APPLICATION_MODAL);
                    st.setScene(sc);
                    st.showAndWait();
                }
            });
        }
    }
    public void updateContained(){
        containedList.getItems().clear();
        if (entry instanceof CardGame){
            for (Expansion e : ((CardGame) entry).getExpansions()){
                containedList.getItems().add(e);
            }
        } else if (entry instanceof TVSeries){
            for (ArrayList<Episodes> season : ((TVSeries) entry).getEpisodes()){
                for (Episodes e : season){
                    containedList.getItems().add(e);
                }
            }
        }
    }

    public void deleteEntry(){
        Stage cur = (Stage) anchor.getScene().getWindow();
        Alert warnDel = new Alert(Alert.AlertType.WARNING);
        warnDel.setTitle("Confirm deletion of entry!");
        warnDel.setHeaderText("Are you sure?");
        warnDel.setContentText("You are deleting this entry forever. This cannot be undone!");
        Optional<ButtonType> choice = warnDel.showAndWait();

        if (choice.isPresent() && choice.get() == ButtonType.OK) {
            if (home != null) {
                home.delete(entry);
            }
            else if (container != null) {
                container.delete(selSub);
                container.updateContained();
            }
            cur.close();
        }
    }
    public void delete(MediaEntry sub){
        if (sub instanceof Expansion) ((CardGame) entry).getExpansions().remove(sub);
        else if (sub instanceof Episodes) {
            for (ArrayList<Episodes> season : ((TVSeries) entry).getEpisodes())
                season.remove(sub);
        }
    }

    public void openRate() throws IOException {
        FXMLLoader loader = new FXMLLoader(MyApp.class.getResource("/rateEntry.fxml"));
        Scene sc = new Scene(loader.load());
        Stage st = new Stage();
        RateController rate = loader.getController();

        rate.setEntry(entry);
        st.setScene(sc);
        st.initModality(Modality.APPLICATION_MODAL);
        st.showAndWait();
        if (entry.getRating() != -1) updateRating();
    }
    private void updateRating(){
        ratingLabel.setText(String.valueOf(entry.getRating()));
        reviewArea.setText(entry.getReview());
        reviewArea.setOpacity(1.00);
        ratingButton.setDisable(true);
        ratingButton.setOpacity(0.00);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        detailsList.setCellFactory(param -> new ListCell<>(){
            private ChoiceBox<Status> statusBox = new ChoiceBox<>(FXCollections.observableArrayList(Status.PLANNED, Status.IN_PROGRESS, Status.COMPLETED));
            private TextField field = new TextField();
            private String type;

            {
                setOnMouseClicked(e -> {
                    if (!isEmpty()){
                        getListView().edit(getIndex());
                    }
                });

                statusBox.setOnAction(e -> {
                    if (isEditing()) commitEdit(type + ": " + statusBox.getValue());
                });

                field.setOnAction(e -> {
                    if (isEditing()) commitEdit(type + ": " + field.getText());
                });
            }

            @Override
            protected void updateItem(String item, boolean empty){
                super.updateItem(item, empty);

                if (empty || item == null){
                    setText(null);
                    setGraphic(null);
                    return;
                }

                type = item.substring(0, item.indexOf(':'));

                if (isEditing()){
                    switch(type){
                        case "Status":
                            statusBox.setValue(entry.getStatus());
                            setGraphic(statusBox);
                            break;
                        case "Price":
                            field.setPromptText("Input price: " + String.valueOf(((CardGame) entry).getPrice()));
                            setGraphic(field);
                            break;
                        case "Publisher":
                            field.setPromptText("Input publisher: " + String.valueOf(((CardGame) entry).getPublisher()));
                            setGraphic(field);
                            break;
                        default:
                            setText(item);
                            setGraphic(null);
                            break;
                    }
                } else {
                    setText(item);
                    setGraphic(null);
                }
            }

            @Override
            public void startEdit() {
                if (isEmpty()) return;
                super.startEdit();
                updateItem(getItem(), isEmpty()); // re-render into edit mode
            }

            @Override
            public void cancelEdit() {
                super.cancelEdit();
                updateItem(getItem(), isEmpty()); // re-render back to label mode
            }

            @Override
            public void commitEdit(String newValue) {
                if (entry instanceof CardGame){
                    if (type.equals("Price")) {
                        try {
                            ((CardGame) entry).setPrice(Double.parseDouble(newValue.substring(newValue.indexOf(':')+2)));
                        } catch (NumberFormatException e){
                            throw new IllegalArgumentException(e);
                        }
                    } // TODO: Implement other fields and types
                }

                if (type.equals("Status")) entry.setStatus(statusBox.getValue());

                super.commitEdit(newValue);
                setText(newValue);
                setGraphic(null);
            }
        });
    }

    public void openAddForEX(ActionEvent action){
        FXMLLoader loader = new FXMLLoader(MyApp.class.getResource("/addEntry.fxml"));
        Scene sc = null;
        try {
            sc = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        AddController add = loader.getController();
        add.setExCol(((CardGame) entry).getExpansions());
        add.field2.setPromptText("Input price");
        add.field3.setOpacity(0.00);
        add.field3.setManaged(false);
        add.titleLabel.setText("Add an Expansion");
        add.setType('x');

        Stage st = new Stage();
        st.setTitle("Adding an entry...");
        st.initModality(Modality.APPLICATION_MODAL);
        st.setScene(sc);
        st.showAndWait();
        updateContained();
    }

    public void openAddForEP(ActionEvent action){
        FXMLLoader loader = new FXMLLoader(MyApp.class.getResource("/addEntry.fxml"));
        Scene sc = null;
        try {
            sc = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        AddController add = loader.getController();
        add.setEpCol(((TVSeries) entry).getEpisodes());
        add.field2.setPromptText("Input runtime");
        add.field3.setOpacity(0.00);
        add.field3.setManaged(false);
        add.titleLabel.setText("Add an Episode");
        add.initializeSpinner();
        add.setType('p');

        Stage st = new Stage();
        st.setTitle("Adding an entry...");
        st.initModality(Modality.APPLICATION_MODAL);
        st.setScene(sc);
        st.showAndWait();
        updateContained();
    }
}
