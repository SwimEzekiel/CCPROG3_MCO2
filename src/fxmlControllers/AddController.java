package fxmlControllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.CardGame;
import models.MediaEntry;
import models.Status;
import models.Website;

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
    private char type;

    public void setCollection(ArrayList<MediaEntry> collection){
        this.collection = collection;
    }
    public void setType(char type){
        this.type = type;
    }

    public void addEntry(){
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Success!");
        confirm.setHeaderText("New entry created.");
        confirm.setContentText("You may now close this window.");
        Stage cur = (Stage) titleLabel.getScene().getWindow();

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
                }
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
                }
                break;
        }
    }
}
