package fxmlControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class EntryController implements Initializable {

    @FXML
    private Label titleLabel;

    public void setTitle(String titleLabel){
        this.titleLabel.setText(titleLabel);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
