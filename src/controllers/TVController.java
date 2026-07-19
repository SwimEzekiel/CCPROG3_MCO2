package controllers;

import models.TVSeries;
import models.Episodes;
import models.Status;

import java.util.ArrayList;

/**
 * Is the controller for the TVSeries class. Includes editing, creating, handling, and removing of the TVSeries class.
 *
 * @author Ezekiel Alvarez
 */
public class TVController {
    //Attributes
    private ArrayList<TVSeries> tvSeries;

    //Constructors

    /**
     * Creates a new TVController object with an empty tvSeries field.
     */
    public TVController(){
        tvSeries = new ArrayList<TVSeries>();
    }

    /**
     * Creates a new TVController object with the given tvSeries ArrayList.
     * @param tvSeries stores the ArrayList to be fed into the tvSeries field.<br>
     * <b>Precondition:</b> tvSeries is a valid ArrayList.<br>
     * <b>Postcondition:</b> new TVController with the given tvSeries in its field.
     */
    public TVController(ArrayList<TVSeries> tvSeries) {
        this.tvSeries = tvSeries;
    }

    //Getter and Setters

    /**
     * Adds a new anonymous tvSeries into the tvSeries ArrayList.
     * @param title stores the title of the new entry.
     * @param numOfSeasons stores the number of seasons of the new entry.
     * @param author stores the author of the new entry.
     * @param status stores the status of the new entry.<br>
     * <b>Precondition:</b> params are valid per their data type, and status may never be Status.COMPLETED.<br>
     * <b>Postcondition:</b> a new accessible entry in the tvSeries ArrayList.
     */
    public void addEntry(String title, int numOfSeasons, String author, Status status){
        if (status != Status.COMPLETED) tvSeries.add(new TVSeries(title, numOfSeasons, author, status));
    }

    /**
     * Gets the tvSeries from this controller.
     * @return an ArrayList of tvSeries.
     */
    public ArrayList<TVSeries> getTvSeries(){
        return tvSeries;
    }

    /**
     * Adds a new Episode to the specified season of the target TVSeries
     * @param idx contains the index of the target TVSeries
     * @param seasonNum contains the season of the Episode
     * @param title contains the title of the Episode
     * @param status contains the status of the Episode
     * @param runTime contains the runtime in minutes of the Episode <br>
     * <b>Preconditions:</b> idx and seasonNum is within bounds, and status can never be COMPLETED.<br>
     * <b>Postconditions:</b> a new Episode
     */
    public void addEpisode(int idx, int seasonNum, String title, Status status, int runTime){
        if (status != Status.COMPLETED) tvSeries.get(idx).getSeason(seasonNum - 1).add(new Episodes(title, status, runTime));
    }

    /**
     * Gives a review to the tvSeries at the given index.
     * @param idx stores the index of the tvSeries to be updated.
     * @param rating stores the new review to be given to this tvSeries.<br>
     *               <b>Precondition:</b> params are valid per their data type, and idx is within bounds for the ArrayList.<br>
     *               <b>Postcondition:</b> the tvSeries at that index has an updated review
     */
    public void giveRating(int idx, int rating){
        tvSeries.get(idx).setRating(rating);
    }

    /**
     * Gives a review to the tvSeries at the given index.
     * @param idx stores the index of the tvSeries to be updated.
     * @param seasonIdx stores index of season, seasonNum - 1
     * @param epIdx stores index of episode
     * @param rating stores the new review to be given to this controllers.CardGame.
     *               <b>Precondition:</b> params are valid per their data type, and idx is within bounds for the ArrayList.<br>
     *               <b>Postcondition:</b> the tvSeries at that index has an updated review
     */

    public void giveEpRating(int idx, int seasonIdx, int epIdx, int rating){
        tvSeries.get(idx).getSeason(seasonIdx).get(epIdx).setRating(rating);
    }

    /**
     * Gives a review to the tvSeries at the given index.
     * @param idx stores the index of the tvSeries to be updated.
     * @param review stores the new review to be given to this tvSeries.
     *               <b>Precondition:</b> params are valid per their data type, and idx is within bounds for the ArrayList.<br>
     *               <b>Postcondition:</b> the tvSeries at that index has an updated review
     */
    public void giveReview(int idx, String review){
        tvSeries.get(idx).setReview(review);
    }

    /**
     * Gives a review to the tvSeries at the given index.
     * @param idx stores the index of the tvSeries to be updated.
     * @param seasonIdx stores index of season, seasonNum - 1
     * @param epIdx stores index of episode
     * @param review stores the new review to be given to this controllers.CardGame.
     *               <b>Precondition:</b> params are valid per their data type, and idx is within bounds for the ArrayList.<br>
     *               <b>Postcondition:</b> the tvSeries at that index has an updated review
     */
    public void giveEpReview(int idx, int seasonIdx, int epIdx, String review){
        tvSeries.get(idx).getSeason(seasonIdx).get(epIdx).setReview(review);
    }

    /**
     * Edits an episode inside a tvSeries' season
     * @param idx contains the index of the TVSeries
     * @param seasonIdx contains the index of the season, seasonnum - 1
     * @param epIdx index of the episode
     * @param title title of the episode
     * @param runTime (in minutes) of the episode
     * <b>Preconditions:</b> idx is within bounds, and all values input are valid<br>
     * <b>Postcondition:</b> the episode is edited to reflect new changes
     */
    public void editEpisode(int idx, int seasonIdx, int epIdx, String title, int runTime) {
        if (!title.isEmpty())
            tvSeries.get(idx).getSeason(seasonIdx).get(epIdx).setTitle(title);
        tvSeries.get(idx).getSeason(seasonIdx).get(epIdx).setRunTime(runTime);
    }

    /**
     * Gets an entry of the tvSeries array
     * @param idx contains the index of the TVSeries
     * <b>Preconditions:</b> idx is within bounds.<br>
     * <b>Postcondition:</b> the targeted TVSeries is returned
     * @return target TVSeries is returned
     */
    public TVSeries getEntry(int idx){
        return tvSeries.get(idx);
    }

    /**
     * Deletes the specified TVSeries from the specified TVSeries array.
     * @param idx contains the index of the controllers.CardGame to have an Expansion deleted.
     * <b>Preconditions:</b> idx is within bounds.<br>
     * <b>Postcondition:</b> the targeted TVSeries is removed
     */
    public void deleteEntry(int idx){
        tvSeries.remove(idx);
    }

    /**
     * Edits the title and author of the tvSeries
     * @param idx stores the index of the controllers.CardGame to be modified.
     * @param title may be empty to signify no change is desired, or may contain a new String to replace the tvSeries' current title.
     * @param author can be empty or not
     * <b>Preconditions:</b>
     * <pre style="tab-size: 4;">
     * all values in the parameter are valid
     * </pre>
     * <b>Postconditions:</b> The respective variables are updated
     * @throws IndexOutOfBoundsException when idx is out-of-bounds or editing an empty ArrayList.
     */
    public void editEntry(int idx, String title, String author){
        if (!title.isEmpty()) tvSeries.get(idx).setTitle(title);
        if (!author.isEmpty()) tvSeries.get(idx).setAuthor(author);
    }

    /**
     * Gets the cardGames from this controller.
     * @return an ArrayList of cardGames.
     */
    public ArrayList<TVSeries> getTVSeries(){
        return tvSeries;
    }

    /**
     * Sets the cardGames for this controller.
     * @param tvSeries stores the ArrayList to replace the one in the class.<br>
     * <b>Precondition:</b> cardGames is a valid ArrayList.<br>
     * <b>Postcondition:</b> cardGames field is replaced with new ArrayList.
     */
    public void setTVSeries(ArrayList<TVSeries> tvSeries){
        this.tvSeries = tvSeries;
    }
}
