package fxmlControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.CardGame;
import models.Expansion;
import models.MediaEntry;
import models.TVSeries;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EntryController implements Initializable {

    @FXML private AnchorPane anchor;
    @FXML private Label titleLabel;
    @FXML private Label ratingLabel;
    @FXML private TextArea reviewArea;
    @FXML private ListView<String> detailsList;
    @FXML private ListView<String> containedList;
    @FXML private Label relatedMediaLabel;
    @FXML private Button ratingButton;
    private MediaEntry entry;
    private HomeController home;

    public void setTitle(String titleLabel){
        this.titleLabel.setText(titleLabel);
    }
    public void setHome(HomeController home){
        this.home = home;
    }
    public void setEntry(MediaEntry entry){
        this.entry = entry;
        updateView();
    }

    private void updateView(){
        if (entry instanceof CardGame){
            detailsList.getItems().add("Price: " + ((CardGame) entry).getPrice());
            detailsList.getItems().add("Publisher: " + ((CardGame) entry).getPublisher());

            int rating = entry.getRating();
            if (rating != -1) ratingLabel.setText(String.valueOf(rating));
            String review = entry.getReview();
            if (review != null) {
                ratingButton.setOpacity(0.00);
                ratingButton.setDisable(true);
                reviewArea.setText(review);
            }

            relatedMediaLabel.setText("Expansion Decks");
            for (Expansion e : ((CardGame) entry).getExpansions()){
                containedList.getItems().add(e.getTitle());
            }
        } else if (entry instanceof TVSeries){

        } else {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void deleteEntry(){
        Stage cur = (Stage) anchor.getScene().getWindow();
        Alert warnDel = new Alert(Alert.AlertType.WARNING);
        warnDel.setTitle("Confirm deletion of entry!");
        warnDel.setHeaderText("Are you sure?");
        warnDel.setContentText("You are deleting this entry forever. This cannot be undone!");
        Optional<ButtonType> choice = warnDel.showAndWait();

        if (choice.isPresent() && choice.get() == ButtonType.OK) {
            System.out.println("Entry deleted!");
            home.delete(entry);
            cur.close();
        }
        else System.out.println("Deletion cancelled!");
    }
}
