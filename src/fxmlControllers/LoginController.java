package fxmlControllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML private TextField userIDField;
    @FXML private PasswordField pwField;

    //idk anymore hahbsdubuhc - searching for standard login implementtion, don't wanna chatgpt yet
    @FXML public void login(){
        int id = Integer.parseInt(userIDField.getText());
        String pw = pwField.getText();


    }
}
