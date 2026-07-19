package controllers;

import models.User;
import services.UserService;
import views.LoginView;

public class LoginController {
    private LoginView loginView;
    private UserService userService;

    public LoginController(LoginView loginView, UserService userService){
        this.loginView = loginView;
        this.userService = userService;
    }

    public User login(){
        int userID = loginView.askUserID();
        String password = loginView.askPassword();

        User user = userService.login(userID, password);

        if (user == null){
            System.out.println("Invalid login. Please try again.");
        }

        return user;
    }
}
