package app;

/**
 * Represents the user in the object space.
 * Serves as driver for program until branching logic for user actions are coded.
 *
 * @author Ezekiel Alvarez
 * @author Matthew Alfonso Beltran
 */

import modelControllers.CGController;
import modelControllers.TVController;
import modelControllers.WebController;
import models.Collection;
import models.Expansion;
import models.TVSeries;
import models.Episodes;
import models.Status;
import views.UnifiedView;

import java.time.LocalDate;
import java.util.Scanner;

public class MediaVault {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        // Initialize stuffs
        Collection coll = new Collection(0);
        CGController cards = new CGController();
        cards.setCardGames(coll.getCGCollection());
        TVController tv = new TVController();
        tv.setTVSeries(coll.getTVCollection());
        WebController sites = new WebController();
        sites.setWebsites(coll.getWSCollection());
        UnifiedView view = new UnifiedView();

        //HARDCODED ACCOUNT DETAILS:
        int correct_id = 125;
        String correct_password = "123";

        System.out.println("===========================");
        System.out.println("||WELCOME TO MEDIA VAULT!||");
        System.out.println("===========================");

        int login_option = -1;
        while(login_option != 0){
            System.out.println();
            System.out.println("[1] Login");
            System.out.println("[2] Credits");
            System.out.println("[0] Exit");
            System.out.println();
            System.out.print("Input above option: ");
            login_option = Integer.parseInt(scan.nextLine());
            if (login_option == 1){
                int id_login = -1;
                String password = "wow";

                while(id_login != correct_id && password != correct_password) {
                    System.out.print("Enter ID: ");
                    id_login = Integer.parseInt(scan.nextLine());
                    System.out.print("Enter Password: ");
                    password = scan.nextLine();
                }

                System.out.println("Login Successful!");

                int media_option= -1;
                while(media_option != 0){
                    System.out.println();
                    System.out.println("=====MEDIA VAULT=====");
                    System.out.println("Options:");
                    System.out.println("[1] View Card Games");
                    System.out.println("[2] View TV Series");
                    System.out.println("[3] View Websites");
                    System.out.println("[0] Log Out");
                    System.out.println();
                    System.out.print("Input above option: ");
                    media_option = Integer.parseInt(scan.nextLine());

                    // ----------- CARD GAMES ----------- !!!!!!!!!!!!!!!!!!!!!
                    if (media_option == 1){
                        int cardgame_option = -1;
                        while(cardgame_option != 0) {
                            System.out.println();
                            System.out.println("=====CARD GAMES=====");
                            for (int i = 1; i <= cards.getCardGames().size(); i++){
                                System.out.printf("[%d] %s\n", i, cards.getCardGames().get(i - 1).getTitle());
                            }
                            System.out.printf("[%d] Add Media\n", cards.getCardGames().size() + 1);
                            System.out.println("[0] Return to Media Vault");
                            System.out.println();
                            System.out.print("Input above option: ");
                            cardgame_option = Integer.parseInt(scan.nextLine());
                            if (cardgame_option > 0 && cardgame_option <= cards.getCardGames().size()){
                                int card_view_edit = -1;
                                String cg_confirmation = "";
                                while (card_view_edit != 0) {
                                    if (!cg_confirmation.equalsIgnoreCase("Y")) {
                                        System.out.println("[1] View Detailed Entry");
                                        System.out.println("[2] Edit Entry");
                                        System.out.println("[3] Remove Entry");
                                        System.out.println("[0] Go Back");
                                        System.out.println();
                                        System.out.print("Input above option: ");
                                    }
                                    card_view_edit = Integer.parseInt(scan.nextLine());
                                    if (card_view_edit == 1) {
                                        view.print(cards.getEntry(cardgame_option - 1));
                                        if (!cards.getEntry(cardgame_option - 1).getExpansions().isEmpty()) {
                                            System.out.println("\n===== EXPANSIONS =====");
                                            for (int i = 0; i < cards.getEntry(cardgame_option - 1).getExpansions().size(); i++) {
                                                Expansion exp = cards.getEntry(cardgame_option - 1).getExpansions().get(i);
                                                System.out.printf("%s%n", exp.getTitle());
                                                System.out.printf("Price: %.2f%n", exp.getPrice());
                                                System.out.printf("Standalone: %s%n", exp.getStandalone() ? "Yes" : "No");
                                                System.out.printf("Status: %s%n", exp.getStatus());
                                                if (exp.getStatus() == Status.COMPLETED) {
                                                    System.out.printf("Rating: %d/10%n", exp.getRating());
                                                    System.out.printf("Review: %s%n", exp.getReview());
                                                }
                                                System.out.println();
                                            }
                                        } else {
                                            System.out.println("\nNo expansions.");
                                        }
                                    } else if (card_view_edit == 2) {;
                                        System.out.println("What would you like to edit?");
                                        System.out.println("[1] Card Game");
                                        if (!cards.getEntry(cardgame_option - 1).getExpansions().isEmpty())
                                            System.out.println("[2] Expansion");
                                        System.out.print("Input above option: ");
                                        int editChoice = Integer.parseInt(scan.nextLine());
                                        if (editChoice == 1) {
                                            System.out.println("Leave blank to keep current value.");
                                            System.out.print("New Title: ");
                                            String newTitle = scan.nextLine();
                                            System.out.print("New Price: ");
                                            String priceInput = scan.nextLine();
                                            double newPrice = -1;
                                            if (!priceInput.isEmpty())
                                                newPrice = Double.parseDouble(priceInput);
                                            System.out.print("New Publisher: ");
                                            String newPublisher = scan.nextLine();

                                            System.out.println("Status:");
                                            System.out.println("[1] Planned");
                                            System.out.println("[2] In Progress");
                                            System.out.println("[3] Completed");
                                            System.out.print("Input above option: ");

                                            int statusChoice = Integer.parseInt(scan.nextLine());
                                            Status status = null;
                                            if (statusChoice == 1)
                                                status = Status.PLANNED;
                                            else if (statusChoice == 2)
                                                status = Status.IN_PROGRESS;
                                            else if (statusChoice == 3)
                                                status = Status.COMPLETED;

                                            cards.getEntry(cardgame_option - 1).setStatus(status);

                                            if (status == Status.COMPLETED) {
                                                System.out.print("New Rating: ");
                                                int newRating = Integer.parseInt(scan.nextLine());
                                                System.out.print("New Review: ");
                                                String newReview = scan.nextLine();

                                                cards.getEntry(cardgame_option - 1).setRating(newRating);
                                                cards.getEntry(cardgame_option - 1).setReview(newReview);
                                            }

                                            cards.editEntry(cardgame_option - 1, newTitle, newPrice, newPublisher);

                                            System.out.print("Add an expansion? (Y/N): ");
                                            String addExpansion = scan.nextLine();

                                            while (addExpansion.equalsIgnoreCase("Y")) {
                                                int gameIdx = cardgame_option - 1;
                                                System.out.print("Expansion Title: ");
                                                String expTitle = scan.nextLine();
                                                System.out.print("Expansion Price: ");
                                                double expPrice = Double.parseDouble(scan.nextLine());
                                                System.out.print("Standalone? (Y/N): ");
                                                boolean standalone = scan.nextLine().equalsIgnoreCase("Y");
                                                System.out.println("Expansion Status:");
                                                System.out.println("[1] Added to Cart");
                                                System.out.println("[2] Shipping..");
                                                System.out.print("Input Above Option: ");
                                                int expStatusChoice = Integer.parseInt(scan.nextLine());

                                                Status expStatus = null;
                                                if (expStatusChoice == 1)
                                                    expStatus = Status.PLANNED;
                                                else if (expStatusChoice == 2)
                                                    expStatus = Status.IN_PROGRESS;

                                                cards.addPack(gameIdx, expTitle, expPrice, standalone, expStatus);

                                                System.out.print("Add another expansion? (Y/N): ");
                                                addExpansion = scan.nextLine();
                                            }
                                        }
                                        else if (editChoice == 2 && !cards.getEntry(cardgame_option - 1).getExpansions().isEmpty()) {
                                            System.out.println("Choose an expansion:");
                                            for (int i = 0; i < cards.getEntry(cardgame_option - 1).getExpansions().size(); i++) {
                                                System.out.printf("[%d] %s%n", i + 1, cards.getEntry(cardgame_option - 1).getExpansions().get(i).getTitle());}
                                            System.out.print("Input above option: ");
                                            int packChoice = Integer.parseInt(scan.nextLine());

                                            System.out.println("Leave blank to keep current value.");
                                            System.out.print("New Title: ");
                                            String newTitle = scan.nextLine();
                                            System.out.print("New Price: ");
                                            String priceInput = scan.nextLine();
                                            double newPrice = -1;
                                            if (!priceInput.isEmpty())
                                                newPrice = Double.parseDouble(priceInput);
                                            System.out.print("Change Standalone? (Y/N or blank to keep): ");
                                            String standaloneInput = scan.nextLine();
                                            if (standaloneInput.isEmpty()) {
                                                cards.editPack(cardgame_option - 1, packChoice - 1, newTitle, newPrice);
                                            } else {
                                                boolean standalone = standaloneInput.equalsIgnoreCase("Y");
                                                cards.editPack(cardgame_option - 1, packChoice - 1, newTitle, newPrice, standalone);
                                            }

                                            System.out.println("Status:");
                                            System.out.println("[1] Planned");
                                            System.out.println("[2] In Progress");
                                            System.out.println("[3] Completed");
                                            System.out.print("Input above option: ");

                                            int statusChoice = Integer.parseInt(scan.nextLine());
                                            Status status = null;
                                            if (statusChoice == 1)
                                                status = Status.PLANNED;
                                            else if (statusChoice == 2)
                                                status = Status.IN_PROGRESS;
                                            else if (statusChoice == 3)
                                                status = Status.COMPLETED;

                                            cards.getExpansion(cardgame_option - 1, packChoice - 1).setStatus(status);


                                            if (status == Status.COMPLETED) {
                                                System.out.print("New Rating: ");
                                                int newRating = Integer.parseInt(scan.nextLine());
                                                System.out.print("New Review: ");
                                                String newReview = scan.nextLine();

                                                cards.getExpansion(cardgame_option - 1, packChoice - 1).setRating(newRating);
                                                cards.getExpansion(cardgame_option - 1, packChoice - 1).setReview(newReview);
                                            }

                                            System.out.print("Add an expansion? (Y/N): ");
                                            String addExpansion = scan.nextLine();

                                            while (addExpansion.equalsIgnoreCase("Y")) {
                                                int gameIdx = cardgame_option - 1;
                                                System.out.print("Expansion Title: ");
                                                String expTitle = scan.nextLine();
                                                System.out.print("Expansion Price: ");
                                                double expPrice = Double.parseDouble(scan.nextLine());
                                                System.out.print("Standalone? (Y/N): ");
                                                boolean standalone = scan.nextLine().equalsIgnoreCase("Y");
                                                System.out.println("Expansion Status:");
                                                System.out.println("[1] Added to Cart");
                                                System.out.println("[2] Shipping..");
                                                System.out.print("Input Above Option: ");
                                                int expStatusChoice = Integer.parseInt(scan.nextLine());

                                                Status expStatus = null;
                                                if (expStatusChoice == 1)
                                                    expStatus = Status.PLANNED;
                                                else if (expStatusChoice == 2)
                                                    expStatus = Status.IN_PROGRESS;

                                                cards.addPack(gameIdx, expTitle, expPrice, standalone, expStatus);

                                                System.out.print("Add another expansion? (Y/N): ");
                                                addExpansion = scan.nextLine();
                                            }
                                        }
                                    } else if (card_view_edit == 3) {
                                        System.out.print("Deleting this entry is permanent. Are you sure? (Y/N) : ");
                                        cg_confirmation = scan.nextLine();
                                        if (cg_confirmation.equalsIgnoreCase("Y")) {
                                            cards.deleteEntry(cardgame_option - 1);
                                            card_view_edit = 0;
                                            cg_confirmation = "";
                                        }
                                        System.out.println("Entry successfully deleted!");
                                    } else {
                                        System.out.println("Invalid Input. Please try again.");
                                    }
                                }
                            } else if (cardgame_option == cards.getCardGames().size() + 1){
                                System.out.println("===== ADD CARD GAME =====");
                                System.out.print("Title: ");
                                String title = scan.nextLine();
                                System.out.print("Price: ");
                                double price = Double.parseDouble(scan.nextLine());
                                System.out.print("Publisher: ");
                                String publisher = scan.nextLine();
                                System.out.println("Status:");
                                System.out.println("[1] Added to Cart");
                                System.out.println("[2] Shipping..");
                                System.out.print("Input Above Option: ");

                                int statusChoice = Integer.parseInt(scan.nextLine());
                                Status status = null;
                                if (statusChoice == 1)
                                    status = Status.PLANNED;
                                else if (statusChoice == 2)
                                    status = Status.IN_PROGRESS;

                                cards.addEntry(title, price, publisher, status);

                                System.out.println("Card game added successfully!");
                                System.out.print("Add an expansion? (Y/N): ");
                                String addExpansion = scan.nextLine();

                                while (addExpansion.equalsIgnoreCase("Y")) {
                                    int gameIdx = cards.getCardGames().size() - 1;
                                    System.out.print("Expansion Title: ");
                                    String expTitle = scan.nextLine();
                                    System.out.print("Expansion Price: ");
                                    double expPrice = Double.parseDouble(scan.nextLine());
                                    System.out.print("Standalone? (Y/N): ");
                                    boolean standalone = scan.nextLine().equalsIgnoreCase("Y");
                                    System.out.println("Expansion Status:");
                                    System.out.println("[1] Added to Cart");
                                    System.out.println("[2] Shipping..");
                                    System.out.print("Input Above Option: ");
                                    int expStatusChoice = Integer.parseInt(scan.nextLine());

                                    Status expStatus = null;
                                    if (expStatusChoice == 1)
                                        expStatus = Status.PLANNED;
                                    else if (expStatusChoice == 2)
                                        expStatus = Status.IN_PROGRESS;

                                    cards.addPack(gameIdx, expTitle, expPrice, standalone, expStatus);

                                    System.out.print("Add another expansion? (Y/N): ");
                                    addExpansion = scan.nextLine();
                                }
                            }
                        }
                    }
                    // ------- TV SERIES ----------- !!!!!!!!!!!!!!!!!!
                    else if (media_option == 2){
                        int tv_option = -1;
                        while(tv_option != 0) {
                            System.out.println();
                            System.out.println("=====TV SERIES=====");
                            for (int i = 1; i <= tv.getTvSeries().size(); i++) {
                                System.out.printf("[%d] %s\n", i, tv.getTvSeries().get(i - 1).getTitle());
                            }
                            System.out.printf("[%d] Add TV Series%n", tv.getTvSeries().size() + 1);
                            System.out.println("[0] Return to Media Vault");
                            System.out.println();
                            System.out.print("Input above option: ");
                            tv_option = Integer.parseInt(scan.nextLine());
                            if (tv_option > 0 && tv_option <= tv.getTvSeries().size()) {
                                int tv_edit_view = -1;
                                String tv_confirmation = "";
                                while (tv_edit_view != 0) {
                                    if (!tv_confirmation.equalsIgnoreCase("Y")) {
                                        System.out.println("[1] View Detailed Entry");
                                        System.out.println("[2] Edit Entry");
                                        System.out.println("[0] Go Back");
                                        System.out.println();
                                        System.out.print("Input above option: ");
                                    }
                                    tv_edit_view = Integer.parseInt(scan.nextLine());
                                    if (tv_edit_view == 1) {
                                        view.print(tv.getTvSeries().get(tv_option - 1));
                                        TVSeries series = tv.getTvSeries().get(tv_option - 1);

                                        System.out.println("\n===== Episodes =====");
                                        for (int season = 0; season < series.getNumOfSeasons(); season++) {
                                            System.out.printf("Season %d%n", season + 1);
                                            for (int ep = 0; ep < series.getNumOfEps(season); ep++) {
                                                Episodes episode = series.getSeason(season).get(ep);
                                                System.out.printf("Episode %d: %s%n", ep + 1, episode.getTitle());
                                                System.out.printf("Runtime: %d mins%n", episode.getRunTime());
                                                System.out.printf("Status: %s%n", episode.getStatus());
                                                if (episode.getStatus() == Status.COMPLETED) {
                                                    System.out.printf("Rating: %d%n", episode.getRating());
                                                    System.out.printf("Review: %s%n", episode.getReview());
                                                }

                                                System.out.println();
                                            }
                                        }

                                    } else if (tv_edit_view == 2) {
                                        TVSeries series = tv.getTvSeries().get(tv_option - 1);
                                        System.out.println("What would you like to edit?");
                                        System.out.println("[1] TV Series");
                                        System.out.println("[2] Episode");
                                        System.out.print("Input above option: ");
                                        int editChoice = Integer.parseInt(scan.nextLine());

                                        if (editChoice == 1) {
                                            System.out.println("Leave blank to keep current value.");
                                            System.out.print("New Title: ");
                                            String newTitle = scan.nextLine();
                                            System.out.print("New Author: ");
                                            String newAuthor = scan.nextLine();
                                            System.out.println("Status:");
                                            System.out.println("[1] Plan to Watch");
                                            System.out.println("[2] Currently Watching..");
                                            System.out.println("[3] Watched!");
                                            System.out.print("Input Above Option: ");
                                            int StatusChoice = Integer.parseInt(scan.nextLine());

                                            Status Status = null;
                                            if (StatusChoice == 1)
                                                Status = Status.PLANNED;
                                            else if (StatusChoice == 2)
                                                Status = Status.IN_PROGRESS;
                                            else if (StatusChoice == 3) {
                                                Status = Status.COMPLETED;
                                            }
                                            tv.editEntry(tv_option - 1, newTitle, newAuthor);
                                            tv.getTvSeries().get(tv_option - 1).setStatus(Status);

                                            if (Status == Status.COMPLETED) {
                                                System.out.print("New Rating: ");
                                                int newRating = Integer.parseInt(scan.nextLine());
                                                System.out.print("New Review: ");
                                                String newReview = scan.nextLine();

                                                tv.giveRating(tv_option - 1, newRating);
                                                tv.giveReview(tv_option - 1, newReview);
                                            }

                                        } else if (editChoice == 2) {
                                            System.out.println("Select Season:");
                                            for (int i = 0; i < series.getNumOfSeasons(); i++) {
                                                System.out.printf("[%d] Season %d%n", i + 1, i + 1);
                                            }
                                            System.out.print("Input season: ");
                                            int seasonChoice = Integer.parseInt(scan.nextLine());
                                            System.out.println("Select Episode:");
                                            for (int i = 0; i < series.getNumOfEps(seasonChoice - 1); i++) {
                                                Episodes ep = series.getSeason(seasonChoice - 1).get(i);
                                                System.out.printf("[%d] %s%n", i + 1, ep.getTitle());
                                            }
                                            System.out.print("Input episode: ");
                                            int epChoice = Integer.parseInt(scan.nextLine());
                                            String newTitle;
                                            String runtimeInput;
                                            System.out.println("Leave blank to keep current value.");
                                            System.out.print("New Episode Title: ");
                                            newTitle = scan.nextLine();
                                            System.out.print("New Runtime: ");
                                            runtimeInput = scan.nextLine();
                                            int newRuntime = -1;
                                            if (!runtimeInput.isEmpty()) {
                                                newRuntime = Integer.parseInt(runtimeInput);
                                            }
                                            tv.editEpisode(tv_option - 1, seasonChoice - 1, epChoice - 1, newTitle, newRuntime);

                                            int StatusChoice = Integer.parseInt(scan.nextLine());
                                            Status Status = null;
                                            if (StatusChoice == 1)
                                                Status = Status.PLANNED;
                                            else if (StatusChoice == 2)
                                                Status = Status.IN_PROGRESS;
                                            else if (StatusChoice == 3) {
                                                Status = Status.COMPLETED;
                                            }
                                            tv.getTvSeries().get(tv_option - 1).getSeason(seasonChoice - 1).get(epChoice - 1);

                                            if (Status == Status.COMPLETED) {
                                                System.out.print("New Rating: ");
                                                int newRating = Integer.parseInt(scan.nextLine());
                                                System.out.print("New Review: ");
                                                String newReview = scan.nextLine();

                                                tv.giveEpRating(tv_option - 1, seasonChoice - 1, epChoice - 1, newRating);
                                                tv.giveEpReview(tv_option - 1, seasonChoice - 1, epChoice - 1, newReview);
                                            }
                                        } else {
                                            System.out.println("Invalid input.");
                                        }

                                    } else if (tv_edit_view == 3) {
                                        System.out.print("Deleting this entry is permanent. Are you sure? (Y/N) : ");
                                        tv_confirmation = scan.nextLine();
                                        if (tv_confirmation.equalsIgnoreCase("Y")) {
                                            tv.deleteEntry(tv_option - 1);
                                            tv_edit_view = 0;
                                            tv_confirmation = "";
                                            System.out.println("Entry successfully deleted!");
                                        } else
                                            System.out.println("Deletion cancelled!");

                                    }
                                }
                            } else if (tv_option == tv.getTvSeries().size() + 1) {
                                System.out.println("===== ADD TV SERIES =====");

                                System.out.print("Title: ");
                                String title = scan.nextLine();
                                System.out.print("Author: ");
                                String author = scan.nextLine();
                                System.out.print("Year Released: ");
                                int year_released = Integer.parseInt(scan.nextLine());
                                System.out.println("Status:");
                                System.out.println("[1] Plan to Watch");
                                System.out.println("[2] Currently Watching");
                                System.out.print("Input option: ");
                                int statusChoice = Integer.parseInt(scan.nextLine());

                                Status status = null;
                                if (statusChoice == 1)
                                    status = Status.PLANNED;
                                else if (statusChoice == 2)
                                    status = Status.IN_PROGRESS;

                                System.out.print("Number of seasons: ");
                                int numSeasons = Integer.parseInt(scan.nextLine());

                                tv.addEntry(title, numSeasons, author, status);
                                int seriesIndex = tv.getTvSeries().size() - 1;
                                tv.getTvSeries().get(seriesIndex).setYearReleased(year_released);

                                for (int season = 1; season <= numSeasons; season++) {
                                    System.out.printf("%n===== Season %d =====%n", season);
                                    System.out.print("Number of Episodes: ");
                                    int numEpisodes = Integer.parseInt(scan.nextLine());

                                    for (int ep = 1; ep <= numEpisodes; ep++) {
                                        System.out.printf("%nEpisode %d%n", ep);
                                        System.out.print("Episode Title: ");
                                        String epTitle = scan.nextLine();
                                        System.out.print("Runtime (minutes): ");
                                        int runtime = Integer.parseInt(scan.nextLine());

                                        System.out.println("Episode Status:");
                                        System.out.println("[1] Plan to Watch");
                                        System.out.println("[2] Currently Watching...");
                                        System.out.print("Input option: ");

                                        int epStatusChoice = Integer.parseInt(scan.nextLine());

                                        Status epStatus = null;
                                        if (epStatusChoice == 1)
                                            epStatus = Status.PLANNED;
                                        else if (epStatusChoice == 2)
                                            epStatus = Status.IN_PROGRESS;

                                        tv.addEpisode(seriesIndex, season, epTitle, epStatus, runtime);
                                    }
                                }

                                System.out.println("\nTV Series added successfully!");
                            }
                        }
                    }
                    // ------- WEBSITES -------- !!!
                    else if (media_option == 3){
                        int website_option = -1;
                        while(website_option != 0) {
                            System.out.println();
                            System.out.println("=====WEBSITES=====");
                            for (int i = 1; i <= sites.getWebsites().size(); i++){
                                System.out.printf("[%d] %s\n", i, sites.getWebsites().get(i - 1).getTitle());
                            }
                            System.out.printf("[%d] Add Media\n", sites.getWebsites().size() + 1);
                            System.out.println("[0] Return to Media Vault");
                            System.out.println();
                            System.out.print("Input Above Option: ");
                            website_option = Integer.parseInt(scan.nextLine());
                            //Going into one of the website entries
                            if (website_option > 0 && website_option <= sites.getWebsites().size()){
                                int web_edit_view = -1;
                                String confirmation = "";
                                while (web_edit_view != 0){
                                    if (!confirmation.equalsIgnoreCase("Y")) {
                                        System.out.println("[1] View Detailed Entry");
                                        System.out.println("[2] Edit Entry");
                                        System.out.println("[3] Remove Entry");
                                        System.out.println("[0] Go Back");
                                    }

                                    System.out.println();
                                    System.out.print("Input above option: ");
                                    web_edit_view = Integer.parseInt(scan.nextLine());
                                    if (web_edit_view == 1){
                                        view.print(sites.getWebsites().get(website_option - 1));
                                    } else if (web_edit_view == 2){
                                        String new_title = "";
                                        String new_URL = "";
                                        LocalDate new_publish_date = null;
                                        int new_rating = -1;
                                        String new_review = "";
                                        System.out.println("Edit Entry! - Leave field blank to not edit it.");
                                        System.out.print("Enter new Title: ");
                                        new_title = scan.nextLine();
                                        System.out.print("Enter new URL: ");
                                        new_URL = scan.nextLine();
                                        System.out.print("Enter new Publish Date (YYYY-MM-DD): ");
                                        String temp_date = scan.nextLine();
                                        LocalDate date = null;
                                        if (!temp_date.isEmpty())
                                            date = LocalDate.parse(temp_date);
                                        System.out.println("New Status:");
                                        System.out.println("[1] Scouted");
                                        System.out.println("[2] Unvisited");
                                        System.out.println("[3] Visited!");
                                        System.out.print("Input above option: ");

                                        int statusChoice = Integer.parseInt(scan.nextLine());
                                        Status status = null;
                                        if (statusChoice == 1)
                                            status = Status.PLANNED;
                                        else if (statusChoice == 2)
                                            status = Status.IN_PROGRESS;
                                        else if (statusChoice == 3)
                                            status = Status.COMPLETED;
                                        sites.getEntry(website_option - 1).setStatus(status);

                                        sites.editEntry(website_option - 1, new_title, new_URL, date);

                                        if (status == status.COMPLETED) {
                                            System.out.print("Enter new Rating:  ");
                                            new_rating = Integer.parseInt(scan.nextLine());
                                            System.out.print("Enter new Review: ");
                                            new_review = scan.nextLine();

                                            sites.getWebsites().get(website_option - 1).setRating(new_rating);
                                            sites.getWebsites().get(website_option - 1).setReview(new_review);
                                        } else {
                                            sites.getWebsites().get(website_option - 1).setRating(-1);
                                            sites.getWebsites().get(website_option - 1).setReview("");
                                        }
                                    } else if (web_edit_view == 3){
                                        System.out.print("Deleting this entry is permanent. Are you sure? (Y/N) : ");
                                        confirmation = scan.nextLine();
                                        if (confirmation.equalsIgnoreCase("Y")){
                                            sites.deleteEntry(website_option - 1);
                                            web_edit_view = 0;
                                            confirmation = "";
                                            System.out.println("Entry successfully deleted!");
                                        } else
                                            System.out.println("Entry Deletion cancelled!");


                                    }
                                }
                                //Adding website entry
                            } else if (website_option == sites.getWebsites().size() + 1){
                                System.out.println("===== ADD WEBSITE =====");
                                System.out.print("Title: ");
                                String title = scan.nextLine();
                                System.out.print("URL: ");
                                String url = scan.nextLine();
                                System.out.print("Publish Date (YYYY-MM-DD): ");
                                String temp_date = scan.nextLine();
                                LocalDate date = null;
                                if (!temp_date.isEmpty())
                                    date = LocalDate.parse(temp_date);
                                System.out.println("Status:");
                                System.out.println("[1] Scouted");
                                System.out.println("[2] Unvisited");

                                int statusChoice = Integer.parseInt(scan.nextLine());
                                Status status = null;
                                if (statusChoice == 1)
                                    status = Status.PLANNED;
                                else if (statusChoice == 2)
                                    status = Status.IN_PROGRESS;

                                sites.addEntry(title, url, date, status);

                            }
                            else if (website_option != 0){
                                System.out.println("Invalid Input. Please try again.");
                            }
                        }
                    }
                }
            } else if (login_option == 2){
                credits();
            } else if (login_option == 0){
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid Input. Please try again.");
            }

        }

    }

    public static void credits() {
        System.out.println();
        System.out.println("=====CREDITS======");
        System.out.println();
        System.out.println("THIS PROGRAM WAS PROUDLY MADE BY:");
        System.out.println("- Ezekiel Alvarez");
        System.out.println("- Matthew Alfonso Beltran");
        System.out.println("HELLO WORLD!!!!");
        System.out.println();
        System.out.println("==================");

    }
}
