package views;

import java.util.Scanner;

public class LoginView {
    Scanner scan = new Scanner(System.in);

    public int askUserID(){
        System.out.print("Enter User ID: ");
        return Integer.parseInt(scan.nextLine());
    }

    public String askPassword(){
        System.out.print("Enter Password: ");
        return scan.nextLine();
    }
}
