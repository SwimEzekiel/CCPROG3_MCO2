package modelControllers;

import models.Website;
import models.Status;

import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Handles the creation, editing, updating, and deleting of Websites in the user's controllers.Collection.
 *
 * @author Matthew Alfonso Beltran
 */
public class WebController {
    // Attributes
    private ArrayList<Website> websites;

    // Constructor

    /**
     * Creates a new WebController object with an empty websites field.
     */
    public WebController(){
        websites = new ArrayList<>();
    }

    /**
     * Creates a new WebController object with the given websites ArrayList.
     * @param websites stores the ArrayList to be fed into the websites field<br>
     *                 <b>Precondition:</b> websites is a valid ArrayList.<br>
     *                 <b>Postcondition:</b> new WebController with the given websites in its field.
     */
    public WebController(ArrayList<Website> websites){
        this.websites = websites;
    }

    // Getter and Setter

    /**
     * Gets the websites from this controller.
     * @return an ArrayList of websites.
     */
    public ArrayList<Website> getWebsites(){
        return websites;
    }

    /**
     * Sets the websites for this controller.
     * @param websites stores the ArrayList to replace the one in this class.<br>
     * <b>Precondition:</b> websites is a valid ArrayList.<br>
     * <b>Postcondition:</b> websites field is replaced with a new ArrayList.
     */
    public void setWebsites(ArrayList<Website> websites){
        this.websites = websites;
    }

    // REQUIRED Methods

    /**
     * Adds a new anonymous website into the websites ArrayList.
     * @param title stores the title of the new entry.
     * @param URL stores the URL of the new entry.
     * @param publishDate stores the publisher of the new entry.
     * @param status stores the status of the new entry.<br>
     * <b>Precondition:</b> params are valid per their data type, and status may never be Status.COMPLETED.<br>
     * <b>Postcondition:</b> a new accessible entry in the websites ArrayList.
     */
    public void addEntry(String title, String URL, LocalDate publishDate, Status status){
        websites.add(new Website(title, URL, publishDate, status));
    }

    /**
     * Updates the status of the Website at the given index.
     * @param idx stores the index of the Website to be updated.
     * @param status stores the new Status to be given to the Website.<br>
     * <b>Precondition:</b> params are valid per their data type, and idx is within bounds for the ArrayList.<br>
     * <b>Postcondition:</b> the Website at that index has an updated status
     */
    public void updateStatus(int idx, Status status){
        websites.get(idx).setStatus(status);
    }

    /**
     * Gives a rating to the Website at the specified index.
     * @param idx is the index of the Website to be updated.
     * @param rating stores the new rating to be given to the Website.<br>
     * <b>Precondition:</b> idx is within bounds for the ArrayList, and rating is from 0-10.<br>
     * <b>Postcondition:</b> the Website at the given index has an updated rating.
     */
    public void giveRating(int idx, int rating){
        websites.get(idx).setRating(rating);
    }

    /**
     * Gives a review to the Website at the given index.
     * @param idx stores the index of the Website to be updated.
     * @param review stores the new review to be given to the Website.<br>
     * <b>Precondition:</b> params are valid per their data type, and idx is within bounds for the ArrayList.<br>
     * <b>Postcondition:</b> the Website at that index has an updated review.
     */
    public void giveReview(int idx, String review){
        websites.get(idx).setReview(review);
    }

    // OTHER Methods (not required or class specific)

    /**
     * Gets the Website at the specified index.
     * @param idx stores the index of the Website to be returned.
     * @return the Website at that index.<br>
     * <b>Precondition:</b> idx must be within bounds for the ArrayList.<br>
     * <b>Postcondition:</b> a valid Website from the ArrayList.
     */
    public Website getEntry(int idx){
        return websites.get(idx);
    }

    /**
     * Edits the unique features of a Website object in the ArrayList.
     * @param idx stores the index of the Website to be edited.
     * @param title may be empty for no change, or may contain a String to replace the title of the specified Website.
     * @param URL may be empty for no change, or may contain a String to replace the URL of the specified Website.
     * @param publishDate may be null for no change, or may contain a LocalDate to replace the publishDate of the specified Website.<br>
     * <b>Precondition:</b>
     * <pre style="tab-size: 4;">
     * nullable params may be null to show no change. <br>
     * non-nullable params may hold an invalid value to show no change.<br>
     * if params are valid, the respective fields will be replaced.<br>
     * for publishDate to be valid, it must also be on or before today.<br>
     * </pre>
     * <b>Postcondition:</b> unique fields of the specified Website will be updated if their parameters are valid.
     * @throws IndexOutOfBoundsException when idx is out-of-bounds or editing an empty ArrayList.
     */
    public void editEntry(int idx, String title, String URL, LocalDate publishDate){
        if (!title.isEmpty()) websites.get(idx).setTitle(title);
        if (!URL.isEmpty()) websites.get(idx).setURL(URL);
        if (publishDate != null) websites.get(idx).setPublishDate(publishDate);
    }

    /**
     * Deletes the Website at a specified index.
     * @param idx stores the index of the Website to be deleted.<br>
     * <b>Preconditions:</b> idx must be within bounds for the ArrayList.<br>
     * <b>Postconditions:</b> an ArrayList without the specified Website.
     * @throws IndexOutOfBoundsException when idx is out-of-bounds or deleting from empty ArrayList.
     */
    public void deleteEntry(int idx){
        websites.remove(idx);
    }

    /**
     * Calls the visit method of the Website at the specified index.
     * @param idx stores the index of the Website to be visited.<br>
     * <b>Preconditions:</b> idx must be within bounds for the ArrayList.<br>
     */
    public void openInBrowser(int idx){
        websites.get(idx).visit();
    }
}
