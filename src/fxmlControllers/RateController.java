package fxmlControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.MediaEntry;

import java.net.URL;
import java.util.ResourceBundle;

public class RateController implements Initializable {
    @FXML private Spinner<Integer> ratingSpinner;
    @FXML private TextArea reviewArea;
    @FXML private Button reviewButton;
    private MediaEntry entry;

    public void setEntry(MediaEntry entry){
        this.entry = entry;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
        factory.setValue(0);
        ratingSpinner.setValueFactory(factory);
    }

    public void giveReview(){
        Stage window = (Stage) reviewArea.getScene().getWindow();
        Alert err = new Alert(Alert.AlertType.ERROR);
        Alert info = new Alert(Alert.AlertType.INFORMATION);

        info.setTitle("Successful review.");
        info.setHeaderText("Review successfully given!");
        info.setContentText("You may now close this window.");

        err.setTitle("Empty review body.");
        err.setHeaderText("Review not given.");
        err.setContentText("Please give a few words about the media you consumed!");

        if (reviewArea.getText().isEmpty()){
            err.showAndWait();
        } else {
            entry.setRating(ratingSpinner.getValue());
            entry.setReview(reviewArea.getText());
            info.showAndWait();
            window.close();
        }
    }
}
