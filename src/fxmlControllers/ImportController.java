package fxmlControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ImportController {
    @FXML
    private Button selectButton;

    public void importFile(){
        System.out.println("Loading goes here!");
    }
    public void selectFile(){
        FileChooser fc = new FileChooser();
        Stage s = new Stage();
        String extractExtension;
        fc.setTitle("File Explorer");

        File selected = fc.showOpenDialog(s);
        if (selected != null){
            extractExtension = selected.getName();
            extractExtension = extractExtension.substring(0, extractExtension.indexOf('.'));
            System.out.println("File selected: " + selected.getAbsolutePath());
            selectButton.setText(extractExtension);
        }
        else System.out.println("File selection cancelled.");
    }
}
