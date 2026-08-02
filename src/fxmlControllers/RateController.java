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

    /**
     * Sets the media entry to be reviewed
     * @param entry contains the MediaEntry to be rated and reviewed<br>
     * <b>Precondition:</b> entry is valid<br>
     * <b>Postcondition:</b> entry field is updated
     */
    public void setEntry(MediaEntry entry){
        this.entry = entry;
    }

    /**
     * Initializes the rating spinner
     * @param url contains the location of the FXML file<br>
     * @param resourceBundle contains the resources for localization<br>
     * <b>Precondition:</b> controller is successfully loaded<br>
     * <b>Postcondition:</b> rating spinner is initialized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
        factory.setValue(0);
        ratingSpinner.setValueFactory(factory);
    }

    /**
     * Submits the user's rating and review for the current media entry
     * <b>Precondition:</b> valid media entry <br>
     * <b>Postcondition:</b> entry is updated with the rating and review if valid
     */
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
