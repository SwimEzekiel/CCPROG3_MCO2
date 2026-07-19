package views;

import com.sun.security.jgss.GSSUtil;

import java.util.Scanner;

public class MenuView {
    private Scanner scan = new Scanner(System.in);

    public void display(){
        System.out.println("===== MEDIA VAULT =====");
        System.out.println("[1] View Collection");
        System.out.println("[2] Credits");
        System.out.println("[3] Settings");
        System.out.println("[0] Log Out");
        System.out.print("Input above option: ");
    }

    public int getChoice(){
        return Integer.parseInt(scan.nextLine());
    }
}
