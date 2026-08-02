package fxmlControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.User;
import services.UserService;

public class LoginController {
    @FXML private TextField userIDField;
    @FXML private PasswordField pwField;
    private UserService service;
    private HomeController home;

    /**
     * Creates a LoginController
     * <b>Precondition:</b> none<br>
     * <b>Postcondition:</b> UserService is initialized
     */
    public LoginController(){
        service = new UserService();
    }

    /**
     * Sets the HomeController
     * @param home contains the HomeController<br>
     * <b>Precondition:</b> home is valid<br>
     * <b>Postcondition:</b> home field is updated
     */
    public void setHome(HomeController home){
        this.home = home;
    }

    public void login(){
        int id = -1;
        if (!userIDField.getText().isEmpty()) id = Integer.parseInt(userIDField.getText());
        String pw = pwField.getText();
        Stage window = (Stage) pwField.getScene().getWindow();

        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Successful!");
        info.setHeaderText("You have successfully logged in.");
        info.setContentText("You may now access your collection.");

        Alert err = new Alert(Alert.AlertType.ERROR);
        err.setTitle("Unsuccessful.");
        err.setHeaderText("Your ID or password may be incorrect.");
        err.setContentText("Please try again.");

        User cur = service.login(id, pw);
        if (cur == null) err.showAndWait();
        else {
            home.setCurU(cur);
            info.showAndWait();
            window.close();
        }
    }
}
