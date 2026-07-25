package app;

import modelControllers.LoginController;
import modelControllers.MenuController;
import models.Display;
import models.User;
import services.UserService;
import views.*;

public class NewUserRefactor {
    public static void main(String[] args){
        Display currentDisplay = Display.LOGIN;
        User currentUser = null;

        LoginView loginView = new LoginView();
        MenuView menuView = new MenuView();
        UserService userService = new UserService();
        CreditsView creditsView = new CreditsView();

        LoginController loginController = new LoginController(loginView, userService);
        MenuController menuController = new MenuController(menuView, creditsView);

        while (currentDisplay != Display.EXIT){
            switch(currentDisplay){
                case LOGIN:
                    currentUser = loginController.login();
                    if (currentUser != null){
                        currentDisplay = Display.MAIN_MENU;
                    }
                    break;
                case MAIN_MENU:
                    currentDisplay = menuController.display(currentUser);
                    break;
                case CREDITS:
                    creditsView.credits();
                case TV_SERIES:

                default:
                    break;

            }
        }
    }
}
