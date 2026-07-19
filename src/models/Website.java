package models;
import java.time.LocalDate;

/**
 * Represents a Website entry in MediaVault.
 * Automatically handles the status-rating relationship through a logical check
 * in the latter's setter.
 *
 * @author Matthew Alfonso Beltran
 */
public class Website extends MediaEntry {
    // --- Attributes ---

    // Unique
    private String URL;
    private LocalDate publishDate;

    // --- Constructor ---

    /**
     * Initializes a new Website object.
     * Sets review fields to "empty" values.
     *
     * @param title stores the title of the new Website.
     * @param URL stores the URL of the new Website.
     * @param publishDate stores when the new Website was posted.
     * @param status stores the status of the new Website.<br>
     *
     * <b>Precondition:</b>
     * <pre style="tab-size:4;">
     * status may only be PLANNED or IN_PROGRESS.<br>
     * params must be valid per their data type.<br>
     * </pre>
     *
     * <b>Postcondition:</b> a new Website object with "empty" review values.
     */
    public Website(String title, String URL, LocalDate publishDate, Status status){
        if (status != Status.COMPLETED){
            this.title = title;
            this.URL = URL;
            this.publishDate = publishDate;
            this.status = status;
            this.rating = -1;
            review = "";
        } else
            System.out.println("Entries cannot be created with COMPLETED status.");
    }

    // --- Getters ---
    /**
     * Gets the URL of the current Website.
     * @return the URL String of the Website.
     */
    public String getURL(){
        return URL;
    }

    /**
     * Gets the publishDate of the current Website.
     * @return the publishDate LocalDate of the Website.
     */
    public LocalDate getPublishDate(){
        return publishDate;
    }

    // --- Setters ---
    /**
     * Sets the URL of the current Website.
     * @param URL contains the String to be put into the URL field.<br>
     * <b>Precondition:</b> URL is a valid String.<br>
     * <b>Postcondition:</b> URL field is updated.
     */
    public void setURL(String URL){
        this.URL = URL;
    }

    /**
     * Updates publishDate only if the argument LocalDate is not in the future.
     * @param publishDate stores the new published date of the website. <br>
     * <b>Preconditions:</b> publishDate is on or before today. <br>
     * <b>Postconditions: </b> publishDate is updated only if the new date has passed/is today.
     */
    public void setPublishDate(LocalDate publishDate){
        if (publishDate.isBefore(LocalDate.now()) || publishDate.isEqual(LocalDate.now()))
            this.publishDate = publishDate;
        else {
            System.out.println("New date is invalid.");
        }
    }

    // --- Other ---

    /**
     * [WORK IN PROGRESS]
     * Opens the URL field in the user's default browser
     */
    public void visit(){
        System.out.println("This opens " + URL + " in a browser window!");
    }
}
