package views;

import models.CardGame;
import models.Status;
import models.TVSeries;
import models.Website;
import models.Expansion;

import java.time.format.DateTimeFormatter;

/**
 * Contains all the display functions for all the media types.
 *
 * @author Ezekiel Alvarez
 * @author Matthew Alfonso Beltran
 */
public class UnifiedView {

    // NO ATTRIBUTES

    /**
     * Initializes a new UnifiedView object.
     */
    public UnifiedView(){}

    // Display methods

    /**
     * Displays the details of the passed controllers.CardGame.
     * @param game contains the controllers.CardGame to be printed.<br>
     * <b>Precondition:</b> game is a valid controllers.CardGame.<br>
     */
    public void print(CardGame game) {
        String status = switch (game.getStatus()) {
            case Status.PLANNED -> "Added to cart.";
            case Status.IN_PROGRESS -> "Shipping...";
            case Status.COMPLETED -> "Played!";
        };

        System.out.println("===== CARD GAME =====");
        System.out.println("Title: " + game.getTitle());
        System.out.println("Price: PHP " + game.getPrice());
        System.out.println("Publisher: " + game.getPublisher());

        if (game.getExpansions().isEmpty()) {
            System.out.println("No expansions.");
        } else {
            System.out.println("Expansion decks: ");
            for (Expansion expansion : game.getExpansions())
                System.out.println(" - " + expansion.getTitle());
        }

        System.out.println("\nStatus: " + status);

        if (game.getRating() == -1)
            System.out.println("No rating.");
        else {
            System.out.println("Rating: " + game.getRating() + "/10");
        }

        if (game.getReview() == null)
            System.out.println("No review.");
        else if (game.getReview().isEmpty())
            System.out.println("No review.");
        else
            System.out.println("Review: " + game.getReview());
    }

    /**
     * Displays the details of the passed TVSeries.
     * @param series contains the TVSeries to be printed.<br>
     * <b>Precondition:</b> series is a valid TVSeries.<br>
     */
    public void print(TVSeries series) {
        System.out.println("===== TV SERIES =====");
        String status = switch (series.getStatus()) {
            case Status.PLANNED -> "Plan to watch.";
            case Status.IN_PROGRESS -> "Currently watching...";
            case Status.COMPLETED -> "Watched!";
        };

        System.out.println("Title: " + series.getTitle());
        System.out.println("Number of Seasons: " + series.getNumOfSeasons());
        System.out.println("Author: " + series.getAuthor());
        System.out.println("Year realeased: " + series.getYearReleased());
        if (series.getRating() == -1)
            System.out.println("No rating.");
        else {
            System.out.println("Rating: " + series.getRating() + "/10");
        }

        if (series.getReview() == null)
            System.out.println("No review.");
        else if (series.getReview().isEmpty())
            System.out.println("No review.");
        else
            System.out.println("Review: " + series.getReview());
    }

    /**
     * Displays the details of the passed Website.
     * @param site contains the Website to be printed.<br>
     * <b>Precondition:</b> site is a valid Website.<br>
     */
    public void print(Website site) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "d MMMM yyyy"
        );
        String status = switch (site.getStatus()) {
            case Status.PLANNED -> "Scouted.";
            case Status.IN_PROGRESS -> "Unvisited.";
            case Status.COMPLETED -> "Visited!";
        };

        System.out.println("===== WEBSITE =====");
        System.out.println("Title: " + site.getTitle());
        System.out.println(
            "Published: " + formatter.format(site.getPublishDate())
        );
        System.out.println("URL: " + site.getURL());
        System.out.println("\nStatus: " + status);

        if (site.getRating() == -1)
            System.out.println("No rating.");
        else {
            System.out.println("Rating: " + site.getRating() + "/10");
        }

        if (site.getReview() == null)
            System.out.println("No review.");
        else if (site.getReview().isEmpty())
            System.out.println("No review.");
        else
            System.out.println("Review: " + site.getReview());
    }

    /**
     * Displays the details of the passed Expansion.
     * @param deck contains the Expansion to be printed.<br>
     * <b>Precondition:</b> deck is a valid Expansion.<br>
     */
    public void print(Expansion deck){
        String status = switch (deck.getStatus()){
            case Status.PLANNED -> "Added to cart.";
            case Status.IN_PROGRESS -> "Shipping...";
            case Status.COMPLETED -> "Played!";
        };


        System.out.println("===== EXPANSION =====");
        System.out.println("Title: " + deck.getTitle());
        System.out.println("Price: PHP " + deck.getPrice());
        System.out.println("Playability: " + ((deck.getStandalone()) ? "Can be played on its own" : "Needs main deck to be played"));

        System.out.println("\nStatus: " + status);

        if (deck.getRating() == -1)
            System.out.println("No rating.");
        else {
            System.out.println("Rating: " + deck.getRating() + "/10");
        }

        if (deck.getReview() == null)
            System.out.println("No review.");
        else if (deck.getReview().isEmpty())
            System.out.println("No review.");
        else
            System.out.println("Review: " + deck.getReview());
    }
}
