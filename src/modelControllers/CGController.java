package modelControllers;

import models.CardGame;
import models.Expansion;
import models.Status;

import java.util.ArrayList;

/**
 * Handles the creation, editing, updating, and deleting of controllers.CardGame entries in the user's collection,
 * including the Expansion entries that a controllers.CardGame may have.
 *
 * @author Matthew Alfonso Beltran
 */
public class CGController {
    // Attributes
    private ArrayList<CardGame> cardGames;

    // Constructors

    /**
     * Creates a new CGController object with an empty cardGames field.
     */
    public CGController(){
        cardGames = new ArrayList<>();
    }

    /**
     * Creates a new CGController object with the given cardGames ArrayList.
     * @param cardGames stores the ArrayList to be fed into the cardGames field.<br>
     * <b>Precondition:</b> cardGames is a valid ArrayList.<br>
     * <b>Postcondition:</b> new CGController with the given cardGames in its field.
     */
    public CGController(ArrayList<CardGame> cardGames){
        this.cardGames = cardGames;
    }

    // Getter and Setter

    /**
     * Gets the cardGames from this controller.
     * @return an ArrayList of cardGames.
     */
    public ArrayList<CardGame> getCardGames(){
        return cardGames;
    }

    /**
     * Sets the cardGames for this controller.
     * @param cardGames stores the ArrayList to replace the one in the class.<br>
     * <b>Precondition:</b> cardGames is a valid ArrayList.<br>
     * <b>Postcondition:</b> cardGames field is replaced with new ArrayList.
     */
    public void setCardGames(ArrayList<CardGame> cardGames){
        this.cardGames = cardGames;
    }

    // REQUIRED Methods

    /**
     * Adds a new anonymous cardGame into the cardGame ArrayList.
     * @param title stores the title of the new entry.
     * @param price stores the price of the new entry.
     * @param publisher stores the publisher of the new entry.
     * @param status stores the status of the new entry.<br>
     * <b>Precondition:</b> params are valid per their data type, and status may never be Status.COMPLETED.<br>
     * <b>Postcondition:</b> a new accessible entry in the cardGame ArrayList.
     */
    public void addEntry(String title, double price, String publisher, Status status){
        cardGames.add(new CardGame(title, price, publisher, status));
    }

    /**
     * Updates the status of the controllers.CardGame at the given index.
     * @param idx stores the index of the controllers.CardGame to be updated.
     * @param status stores the new Status to be given to this controllers.CardGame.<br>
     *               <b>Precondition:</b> params are valid per their data type, and idx is within bounds for the ArrayList.<br>
     *               <b>Postcondition:</b> the controllers.CardGame at that index has an updated status
     */
    public void updateStatus(int idx, Status status){
        cardGames.get(idx).setStatus(status);
    }

    /**
     * Gives a rating to the controllers.CardGame at the specified index.
     * @param idx stores the index of the controllers.CardGame to be updated.
     * @param rating stores the new rating to be given to this controllers.CardGame.
     *               <b>Precondition:</b> idx is within bounds for the ArrayList, and rating is from 0 to 10 only.<br>
     *               <b>Postcondition:</b> the controllers.CardGame at that index has an updated rating
     */
    public void giveRating(int idx, int rating){
        cardGames.get(idx).setRating(rating);
    }
    /**
     * Gives a review to the controllers.CardGame at the given index.
     * @param idx stores the index of the controllers.CardGame to be updated.
     * @param review stores the new review to be given to this controllers.CardGame.
     *               <b>Precondition:</b> params are valid per their data type, and idx is within bounds for the ArrayList.<br>
     *               <b>Postcondition:</b> the controllers.CardGame at that index has an updated review
     */
    public void giveReview(int idx, String review){
        cardGames.get(idx).setReview(review);
    }

    // OTHER Methods (not required or card-game-specific)
    /**
     * Gets the controllers.CardGame at the specified index.
     * @param idx stores the index of the controllers.CardGame to be returned.
     * @return the controllers.CardGame at that index.<br>
     * <b>Preconditions:</b> idx must be within bounds for the ArrayList.<br>
     * <b>Postconditions:</b> a valid controllers.CardGame from the ArrayList.
     */
    public CardGame getEntry(int idx){
        return cardGames.get(idx);
    }

    /**
     * Edits the unique features of a controllers.CardGame object in the ArrayList.
     * @param idx stores the index of the controllers.CardGame to be modified.
     * @param title may be empty to signify no change is desired, or may contain a new String to replace the controllers.CardGame's current title.
     * @param price may be -1 to signify no change, or may contain a new double to replace the controllers.CardGame's current price.
     * @param publisher may be empty to represent no change, or may contain a new String to replace the controllers.CardGame's current publisher.<br>
     * <b>Preconditions:</b>
     * <pre style="tab-size: 4;">
     * nullable params may be null to show no change.<br>
     * non-nullable params will hold an invalid value to show no change.<br>
     * if params are valid, the respective fields will be replaced.<br>
     * </pre>
     * <b>Postconditions:</b> unique fields of the controllers.CardGame will be updated if their parameters are valid.
     * @throws IndexOutOfBoundsException when idx is out-of-bounds or editing an empty ArrayList.
     */
    public void editEntry(int idx, String title, double price, String publisher){
        if (!title.isEmpty()) cardGames.get(idx).setTitle(title);
        if (price != -1) cardGames.get(idx).setPrice(price);
        if (!publisher.isEmpty()) cardGames.get(idx).setPublisher(publisher);
    }

    /**
     * Deletes the controllers.CardGame at the index.
     * @param idx stores the index of the controllers.CardGame to be deleted. <br>
     * <b>Preconditions:</b> idx must be within bounds for the ArrayList.<br>
     * <b>Postconditions:</b> an ArrayList without the specified controllers.CardGame.
     * @throws IndexOutOfBoundsException when idx is out-of-bounds or deleting from empty ArrayList.
     */
    public void deleteEntry(int idx){
        cardGames.remove(idx);
    }


    // Expansion methods
    /**
     * Adds a new anonymous Expansion to a controllers.CardGame in the ArrayList.
     * @param idx stores the index of the controllers.CardGame to receive the new Expansion.
     * @param title stores the title of the new entry.
     * @param price stores the price of the new entry.
     * @param isStandalone stores the playability of the new entry, if it can be played without its super or not.
     * @param status stores the status of the new entry.<br>
     * <b>Precondition:</b> params are valid per their data type, and idx is within bounds for the ArrayList.<br>
     * <b>Postcondition:</b> a new Expansion in a controllers.CardGame in the ArrayList.
     * @throws IndexOutOfBoundsException when idx is out-of-bounds.
     */
    public void addPack(int idx, String title, double price, boolean isStandalone, Status status){
        cardGames.get(idx).getExpansions().add(new Expansion(title, price, status, isStandalone));
    }

    /**
     * Returns the specified Expansion from the specified controllers.CardGame.
     * @param idx contains the index of the controllers.CardGame that contains the targeted Expansion.
     * @param packNum contains the index of the targeted Expansion.<br>
     * <b>Preconditions:</b> idx and packNum are within bounds, and idx should not point to a controllers.CardGame without Expansions.<br>
     * @return the specified Expansion.<br>
     * @throws IndexOutOfBoundsException when idx points to a controllers.CardGame with an empty Expansions array.
     */
    public Expansion getExpansion(int idx, int packNum){
        return cardGames.get(idx).getExpansions().get(packNum);
    }

    /**
     * Updates the unique attributes of a controllers.CardGame's Expansion.
     * Overloaded. This variant handles the interaction when an isStandalone value is not provided, and thus is not to be changed.
     * @param idx contains the index of the controllers.CardGame that has the Expansion to be updated.
     * @param packNum contains the index of the Expansion within that controllers.CardGame's expansions ArrayList.
     * @param title may be null to indicate no change, or may contain the String to be put into the Expansion's title field.
     * @param price may be -1 to indicate no change, or may contain the double to be put into the Expansion's price field.<br>
     * <b>Preconditions: </b>
     * <pre>
     * fields not to be changed should be null or have an invalid placeholder value to still be valid.<br>
     * fields to be changed should be valid as per their data type.<br>
     * idx and packNum are in-bounds, and idx should refer to a controllers.CardGame that has a non-empty expansions ArrayList.<br>
     * </pre>
     * <b>Postconditions: </b>
     * <pre>
     * fields to be left unchanged are unaltered.<br>
     * fields to be changed are updated<br>
     * </pre>
     * @throws IndexOutOfBoundsException when idx points to a controllers.CardGame with an empty Expansions array.
     */
    public void editPack(int idx, int packNum, String title, double price){
        Expansion current = getExpansion(idx, packNum);

        if (!title.isEmpty()) current.setTitle(title);
        if (price != -1) current.setPrice(price);
    }

    /**
     * Updates the unique attributes of a controllers.CardGame's Expansion.
     * Overloaded. This variant handles the interaction when an isStandalone value IS provided.
     * @param idx contains the index of the controllers.CardGame that has the Expansion to be updated.
     * @param packNum contains the index of the Expansion within that controllers.CardGame's expansions ArrayList.
     * @param title may be null to indicate no change, or may contain the String to be put into the Expansion's title field.
     * @param price may be -1 to indicate no change, or may contain the double to be put into the Expansion's price field.
     * @param isStandalone contains the boolean to be entered into the Expansion's isStandalone field.<br>
     * <b>Preconditions: </b>
     * <pre>
     * fields not to be changed should be null or have an invalid placeholder value to still be valid.<br>
     * fields to be changed should be valid as per their data type.<br>
     * idx and packNum are in-bounds, and idx should refer to a controllers.CardGame that has a non-empty expansions ArrayList.<br>
     * </pre>
     * <b>Postconditions: </b>
     * <pre>
     * fields to be left unchanged are unaltered.<br>
     * fields to be changed are updated.<br>
     * isStandalone field is always changed.<br>
     * </pre>
     * @throws IndexOutOfBoundsException when idx points to a controllers.CardGame with an empty Expansions array.
     */
    public void editPack(int idx, int packNum, String title, double price, boolean isStandalone){
        Expansion current = getExpansion(idx, packNum);

        if (!title.isEmpty()) current.setTitle(title);
        if (price != -1) current.setPrice(price);
        current.setStandalone(isStandalone);
    }

    /**
     * Deletes the specified Expansion from the specified controllers.CardGame.
     * @param idx contains the index of the controllers.CardGame to have an Expansion deleted.
     * @param packNum contains the index of the Expansion to be deleted.<br>
     * <b>Preconditions:</b> idx and packNum are within bounds, and idx should not point to a controllers.CardGame without Expansions.<br>
     * <b>Postcondition:</b> the targeted Expansion is removed or an exception is thrown.
     */
    public void deletePack(int idx, int packNum){
        cardGames.get(idx).getExpansions().remove(packNum);
    }
}
