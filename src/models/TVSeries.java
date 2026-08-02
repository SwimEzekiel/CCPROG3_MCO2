/**
 * A TVSeries entry for MediaVault.
 * Houses a 2d array of episodes, number of elements per array changes depending on amount of episode entries
 *
 * @author Ezekiel Alvarez
 */
package models;
import java.util.ArrayList;


public class TVSeries extends MediaEntry{
    // Attributes
    private int yearReleased;
    private String author;
    private int numOfSeasons;
    private ArrayList<ArrayList<Episodes>> episodes;


    // Constructors

    /**
     * Initializes a new TVSeries object.
     * Sets review fields to "empty" values.
     *
     * @param title stores the title of the new TVSeries.
     * @param numOfSeasons stores the number of season of the new TVSeries.
     * @param author stores the author of the new TVSeries
     * @param status stores the status of the new Website.
     *
     * <b>Precondition:</b>
     * <pre style="tab-size:4";>
     * status may only be PLANNED or IN_PROGRESS.<br>
     * params must be valid per their data type.<br>
     * </pre>
     *
     * <b>Postcondition:</b> a new TVSeries object with "empty" review and episode values.
     */

    public TVSeries(String title, int numOfSeasons, String author, Status status) {
        if (status != Status.COMPLETED) {
            this.numOfSeasons = numOfSeasons;
            this.title = title;
            this.author = author;
            this.rating = -1;
            this.status = status;
            episodes = new ArrayList<>();

            for (int i = 0; i < numOfSeasons; i++) {
                episodes.add(new ArrayList<Episodes>());
            }
        } else
            System.out.println("Entries cannot be created with COMPLETED status.");
    }

    //Getter Functions
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the number of episodes in indicated index (seasonNum - 1) of the current TVSeries.
     * @return the number int  of the TVSeries season.
     */
    public int getNumOfEps(int idx) {
        return episodes.get(idx).size();
    }

    public ArrayList<Episodes> getSeason(int idx){
        return episodes.get(idx);
    }

    /**
     * Gets the number of seasons
     * @return int number of seasons
     */
    public int getNumOfSeasons() {
        return numOfSeasons;
    }

    /**
     *
     * Gets the year of release of the series
     * @return int value of year released
     */
    public int getYearReleased() {
        return yearReleased;
    }

    /**
     * Gets the 2D ArrayList of Episodes.
     * @return ArrayList of ArrayList of Episodes.
     */
    public ArrayList<ArrayList<Episodes>> getEpisodes(){
        return episodes;
    }

    //Setter Functions

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Sets the Number of seasons of the current object.
     * @param numOfSeasons contains the int to be put into the numOfSeasons field.<br>
     * <b>Precondition:</b> numOfSeasons is a valid int.<br>
     * <b>Postcondition:</b> numOfSeasons field is updated.
     */
    public void setNumOfSeasons(int numOfSeasons) {
        this.numOfSeasons = numOfSeasons;
    }

    /**
     * Sets the release date of the current object.
     * @param yearReleased contains the int to be put into the yearReleased field.<br>
     * <b>Precondition:</b> yearReleased is a valid int.<br>
     * <b>Postcondition:</b> yearReleased field is updated.
     */
    public void setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
    }

}
