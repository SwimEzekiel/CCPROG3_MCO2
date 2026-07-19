package models;
import java.util.ArrayList;

/**
 * Represents a Card Game entry in MediaVault.
 * Automatically handles the status-rating relationship through a logical check
 * in the latter's setter.
 * Has a container attribute for expansion packs/decks.
 *
 * @author Matthew Alfonso Beltran
 */
public class CardGame extends MediaEntry {
    // --- Attributes ---
    private double price;
    private String publisher;
    private ArrayList<Expansion> expansions;

    // --- Constructors ---

    /**
     * Initializes a new controllers.CardGame object.
     * Sets review fields to "empty" values.
     *
     * @param title stores the title of the new controllers.CardGame object.
     * @param price stores the price of the new controllers.CardGame object.
     * @param publisher stores the publisher of the new controllers.CardGame object.
     * @param status stores the status of the new controllers.CardGame object.<br>
     *
     * <b>Precondition:</b> status may only be PLANNED or IN_PROGRESS. <br>
     * <b>Postcondition: </b> a new controllers.CardGame object with "empty" review values.
     */
    public CardGame(String title, double price, String publisher, Status status){
        if (status != Status.COMPLETED){
            this.title = title;
            this.price = price;
            this.publisher = publisher;
            this.status = status;
            expansions = new ArrayList<>();
            this.rating = -1;
        } else {
            System.out.println("Entries cannot be made with a COMPLETED status.");
        }
    }

    // --- Getters ---

    /**
     * Gets the price of the current controllers.CardGame.
     * @return the price double of the controllers.CardGame object.
     */
    public double getPrice(){
        return price;
    }
    /**
     * Gets the publisher of the current object.
     * @return the publisher String of the controllers.CardGame object.
     */
    public String getPublisher(){
        return publisher;
    }
    /**
     * Gets the expansions of the current object.
     * @return the expansions ArrayList of the controllers.CardGame object.
     */
    public ArrayList<Expansion> getExpansions(){
        return expansions;
    }

    // --- Setters ---

    /**
     * Sets the price, making sure it is non-negative.
     * @param price stores the new price of the entry.<br>
     * <b>Precondition:</b> price is non-negative. <br>
     * <b>Postcondition: </b> price is updated only if it is non-negative.
     */
    public void setPrice(double price){
        this.price = (price >= 0.00) ? price : this.price;
    }

    /**
     * Sets the publisher of the current object.
     * @param publisher contains the String to be put into the publisher field.<br>
     * <b>Precondition:</b> publisher is a valid String<br>
     * <b>Postcondition:</b> publisher field is updated.
     */
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }
}

