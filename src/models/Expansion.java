package models;

/**
 * Represents an Expansion contained within a controllers.CardGame.
 * Created since expansion decks may not necessarily have the same attributes as controllers.CardGame.
 * Still can be treated as a MediaEntry object with status, rating, and review.
 *
 * @author Matthew Alfonso Beltran
 */
public class Expansion extends MediaEntry{
    // --- Attributes ---
    private double price;
    private boolean isStandalone;

    // --- Constructor ---

    /**
     * Initializes a new Expansion object.
     * Sets review fields to "empty" values.
     *
     * @param title stores the title of the new Expansion object.
     * @param price stores the price of the new Expansion object.
     * @param status stores the status of the new Expansion.
     * @param isStandalone stores the playability of the new Expansion.<br>
     *
     * <b>Precondition:</b>
     * <pre>
     * status may only be PLANNED or IN_PROGRESS.<br>
     * params must be valid per their data type.<br>
     * </pre>
     * <b>Postcondition:</b> a new Expansion object with "empty" review values.
     */
    public Expansion(String title, double price, Status status, boolean isStandalone){
        if (status != Status.COMPLETED){
            this.price = price;
            this.isStandalone = isStandalone;

            this.title = title;
            this.status = status;
            this.review = "";
            this.rating = -1;
        } else {
            System.out.println("Entries cannot be created with a COMPLETED status.");
        }
    }

    // --- Getters ---

    /**
     * Gets the price of the current Expansion.
     * @return the price double of the Expansion.
     */
    public double getPrice(){
        return price;
    }

    /**
     * Gets the playability of the current Expansion.
     * @return the playability boolean of the current Expansion.
     */
    public boolean getStandalone(){
        return isStandalone;
    }

    // --- Setters ---

    /**
     * Sets the price, making sure it is non-negative.
     * @param price stores the new price of the entry. <br>
     * <b>Precondition:</b> price is non-negative.<br>
     * <b>Postcondition:</b> price is updated only if it is non-negative.
     */
    public void setPrice(double price){
        this.price = (price >= 0.00) ? price : this.price;
    }

    /**
     * Sets the playability of the current Expansion.
     * @param isStandalone contains the boolean to be put into the isStandalone field.<br>
     * <b>Precondition:</b> isStandalone is a valid boolean.<br>
     * <b>Postcondition:</b> isStandalone field is updated.
     */
    public void setStandalone(boolean isStandalone){
        this.isStandalone = isStandalone;
    }
}
