package fxmlControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EntryController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private Label titleLabel;
    private HomeController home;

    public void setTitle(String titleLabel){
        this.titleLabel.setText(titleLabel);
    }
    public void setHome(HomeController home){
        this.home = home;
    }
//    public EntryController(){
//        System.out.println("NEW ENTRYCONTROLLER MADE:" + this.hashCode());
//    }

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
            //home.delete(titleLabel.getText()); // DOESNT WORK
            cur.close();
        }
        else System.out.println("Deletion cancelled!");
    }
}
