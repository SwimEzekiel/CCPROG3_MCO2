package fxmlControllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import models.*;
import models.Collection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
    private Collection collection;

    /**
     * Enables or disables media options
     * <b>Precondition:</b> export type is selected<br>
     * <b>Postcondition:</b> media options are updated
     */
    public void selectExportType(){
        cardGames.setDisable(!specificMedia.isSelected());
        tvSeries.setDisable(!specificMedia.isSelected());
        websites.setDisable(!specificMedia.isSelected());
    }

    /**
     * Sets the collection to export
     * @param collection contains the user's collection<br>
     * <b>Precondition:</b> collection is valid<br>
     * <b>Postcondition:</b> collection field is updated
     */
    public void setCollection(Collection collection){
        this.collection = collection;
    }

    /**
     * Exports the selected media to a text file in data folder
     * <b>Precondition:</b> export options are selected<br>
     * <b>Postcondition:</b> export type is updated
     */
    public void export(){
        StringBuilder builder = new StringBuilder();

        if (allMedia.isSelected()) builder.append("a");
        else {
            if (cardGames.isSelected()) builder.append("c");
            if (tvSeries.isSelected()) builder.append("t");
            if (websites.isSelected()) builder.append("w");
        }

        exportType = builder.toString();
        collection.setCurrent(0);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/export.txt"))) {

            if (exportType.contains("a") || exportType.contains("c")) {
                writer.write("CARD GAMES");
                writer.newLine();

                for (CardGame game : collection.getCGCollection()) {
                    writer.write(game.toString());
                    writer.newLine();
                }

                writer.newLine();
            }

            if (exportType.contains("a") || exportType.contains("t")) {
                writer.write("TV SERIES");
                writer.newLine();

                for (TVSeries series : collection.getTVCollection()) {
                    writer.write(series.toString());
                    writer.newLine();
                }

                writer.newLine();
            }

            if (exportType.contains("a") || exportType.contains("w")) {
                writer.write("WEBSITES");
                writer.newLine();

                for (Website site : collection.getWSCollection()) {
                    writer.write(site.toString());
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
