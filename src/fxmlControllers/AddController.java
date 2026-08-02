package fxmlControllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class AddController {
    @FXML public Label titleLabel;
    @FXML private TextField titleField;
    @FXML public TextField field2;
    @FXML public TextField field3;
    @FXML public CheckBox standaloneCheck;
    @FXML private Button addButton;
    private ArrayList<MediaEntry> collection;
    private ArrayList<Expansion> exCol;
    private char type;

    public void setCollection(ArrayList<MediaEntry> collection){
        this.collection = collection;
    }
    public void setExCol(ArrayList<Expansion> exCol){
        this.exCol = exCol;
    }
    public void setType(char type){
        this.type = type;
    }

    public void addEntry(){
        Stage cur = (Stage) titleLabel.getScene().getWindow();
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Success!");
        confirm.setHeaderText("New entry created.");
        confirm.setContentText("You may now close this window.");

        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Unsuccessful.");
        error.setHeaderText("Please fill out all fields.");
        error.setContentText("Entries cannot be made with empty fields.");

        switch (type){
            case 'c':
                if (!titleField.getText().isEmpty() && !field2.getText().isEmpty() && !field3.getText().isEmpty()) {
                    String title = titleField.getText();
                    double price;
                    try {
                        price = Double.parseDouble(field2.getText());
                    } catch (NumberFormatException e){
                        throw new IllegalArgumentException(e);
                    }
                    String publisher = field3.getText();

                    CardGame cg = new CardGame(title, price, publisher, Status.PLANNED);
                    collection.add(cg);
                    confirm.showAndWait();
                    cur.close();
                } else error.show();
                break;
            case 'w':
                if (!titleField.getText().isEmpty() && !field2.getText().isEmpty() && !field3.getText().isEmpty()) {
                    String title = titleField.getText();
                    String url = field2.getText();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate publishDate;
                    try {
                        publishDate = LocalDate.parse(field3.getText(), dtf);
                    } catch (DateTimeParseException e){
                        throw new IllegalArgumentException(e);
                    }

                    Website ws = new Website(title, url, publishDate, Status.PLANNED);
                    collection.add(ws);
                    confirm.showAndWait();
                    cur.close();
                } else error.show();
                break;
            case 'x':
                if (!titleField.getText().isEmpty() && !field2.getText().isEmpty()) {
                    String title = titleField.getText();
                    double price;
                    try {
                        price = Double.parseDouble(field2.getText());
                    } catch (NumberFormatException e){
                        throw new IllegalArgumentException(e);
                    }
                    boolean standalone = standaloneCheck.isSelected();

                    Expansion ex = new Expansion(title, price, Status.PLANNED, standalone);
                    exCol.add(ex);
                    confirm.showAndWait();
                    cur.close();
                } else error.show();
                break;
        }
    }
}
