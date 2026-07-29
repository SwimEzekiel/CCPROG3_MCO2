package fxmlControllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;

public class ExportController {
    @FXML
    private RadioButton specificMedia;
    @FXML
    private RadioButton allMedia;
    @FXML
    private CheckBox cardGames;
    @FXML
    private CheckBox tvSeries;
    @FXML
    private CheckBox websites;
    private String exportType = "";

    public void selectExportType(){
        cardGames.setDisable(!specificMedia.isSelected());
        tvSeries.setDisable(!specificMedia.isSelected());
        websites.setDisable(!specificMedia.isSelected());
    }

    public void export(){
        StringBuilder builder = new StringBuilder();

        if (allMedia.isSelected()) builder.append("a");
        else {
            if (cardGames.isSelected()) builder.append("c");
            if (tvSeries.isSelected()) builder.append("t");
            if (websites.isSelected()) builder.append("w");
        }

        exportType = builder.toString();
        System.out.println(exportType);
    }
}
