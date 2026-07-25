package modelControllers;

import models.Display;
import models.User;
import views.CreditsView;
import views.MenuView;

public class MenuController {
    private MenuView view;
    private CreditsView creditsView;

    public MenuController(MenuView view, CreditsView creditsView){
        this.view = view;
        this.creditsView = creditsView;
    }

    public Display display(User user){
        view.display();
        int choice = view.getChoice();

        switch(choice){
            case 1:
                System.out.println("===== COLLECTION =====");
                return Display.MAIN_MENU;
            case 2:
                creditsView.credits();
                return Display.MAIN_MENU;
            case 3:
                System.out.println("===== SETTINGS =====");
                return Display.MAIN_MENU;
            case 0:
                System.out.println("Logging out...\n");
            default:
                System.out.println("Invalid choice. Please try again!");
                return Display.MAIN_MENU;
        }
    }
}
