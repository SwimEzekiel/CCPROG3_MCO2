package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * controllers.Collection class that contains the database of all collections that users have.
 * ASSUMPTION: Collections here are UNMODIFIABLE until loaded into respective controllers.
 *             Thus, it must be updated after every login and logout!
 *
 * @author Matthew Alfonso Beltran
 */
public class Collection {
    // Attributes
    private int current;
    private ArrayList<ArrayList<CardGame>> CGdatabase;
    private ArrayList<ArrayList<TVSeries>> TVdatabase;
    private ArrayList<ArrayList<Website>> WSdatabase;

    // Constructor

    /**
     * Initializes a new controllers.Collection object with an "empty" placeholder value for the current field.
     * -1 means no user has logged in yet.
     * <b>Postcondition:</b> a new controllers.Collection object with an "empty" current field.
     */
    public Collection(){
        this.current = -1;
        getDatabase();
    }

    /**
     * Initializes a new controllers.Collection object with a valid int in the current field.
     * @param current contains the int of the currently logged-in user's index in the controllers.Collection.<br>
     * <b>Precondition:</b> current is a valid int.<br>
     * <b>Postcondition:</b> a new controllers.Collection object with a user's index in the current field.
     */
    public Collection(int current){
        this.current = current;
        getDatabase();
    }

    // Getters
    /**
     * Helper method that reads text files and loads them into the controllers.Collection.
     * For now, sets the respective media databases to some hardcoded values.
     */
    private void getDatabase(){
        ArrayList<ArrayList<CardGame>> hardcodedCGs = new ArrayList<>();
        ArrayList<ArrayList<TVSeries>> hardcodedTVs = new ArrayList<>();
        ArrayList<ArrayList<Website>> hardcodedWSs = new ArrayList<>();

        // Add CG's per user
        hardcodedCGs.add(new ArrayList<>(List.of(new CardGame("Tic Tac K.O.", 636.36, "Unstable Games", Status.IN_PROGRESS),
                                                       new CardGame("Here to Slay", 650, "Unstable Games", Status.IN_PROGRESS),
                                                       new CardGame("Organ Attack", 248.299, "The Awkward Yeti", Status.PLANNED))));
        hardcodedCGs.add(new ArrayList<>(List.of(new CardGame("Avalon", 349.67, "Indie Boards & Cards", Status.PLANNED))));

        // Add expansions
        hardcodedCGs.getFirst().get(1).getExpansions().add(new Expansion("Warriors and Druids", 225, Status.PLANNED, false));

        // Add TVSeries
        hardcodedTVs.add(new ArrayList<>(List.of(new TVSeries("Naruto", 1, "Periot", Status.PLANNED),
                                                 new TVSeries("Avatar: The Last Airbender", 3, "Nickelodeon", Status.IN_PROGRESS))));
        hardcodedTVs.add(new ArrayList<>(List.of(new TVSeries("Heated Rivalry", 1, "Rachel Reid", Status.IN_PROGRESS))));
        hardcodedTVs.get(1).getFirst().setStatus(Status.COMPLETED);

        // Add episodes
        hardcodedTVs.getFirst().get(1).getEpisodes().getFirst().add(new Episodes("The Boy in The Iceberg", Status.PLANNED, 23));
        hardcodedTVs.getFirst().get(1).getEpisodes().getFirst().add(new Episodes("The Avatar Returns", Status.PLANNED, 22));
        hardcodedTVs.getFirst().get(1).getEpisodes().getFirst().add(new Episodes("The Southern Air Temple", Status.PLANNED, 24));

        // Add websites
        hardcodedWSs.add(new ArrayList<>( List.of(new Website("Bored Button", "boredbutton.com", LocalDate.of(2007, 9, 10), Status.PLANNED),
                                                      new Website("Archer's Hub", "archershub.dlsu.edu.ph", LocalDate.of(2027, 1, 21), Status.IN_PROGRESS))));
        hardcodedWSs.add(new ArrayList<> (List.of(new Website("ESS LMS", "elizalms.ess.edu.ph", LocalDate.of(2026, 7, 4), Status.IN_PROGRESS))));

        CGdatabase = hardcodedCGs;
        TVdatabase = hardcodedTVs;
        WSdatabase = hardcodedWSs;
    }

    /**
     * Gets a user's controllers.CardGame controllers.Collection from the database.
     * @return the ArrayList at the "current" index.
     */
    public ArrayList<CardGame> getCGCollection(){
        return CGdatabase.get(current);
    }

    /**
     * Gets a user's TVSeries controllers.Collection from the database.
     * @return the ArrayList at the "current" index.
     */
    public ArrayList<TVSeries> getTVCollection(){
        return TVdatabase.get(current);
    }

    /**
     * Gets a user's Website controllers.Collection from the database.
     * @return the ArrayList at the "current" index.
     */
    public ArrayList<Website> getWSCollection(){
        return WSdatabase.get(current);
    }

    // Setters

    /**
     * Updates the value in the current field.
     * @param current holds the new int to be put into the current field.<br>
     * <b>Precondition:</b> current is a valid int.<br>
     * <b>Postcondition:</b> current is updated.
     */
    public void setCurrent(int current){
        this.current = current;
    }
    /**
     * Writes the database to text files for file persistence.
     * For now, prints a message of what it should do.
     */
    public void setDatabase(){
        System.out.println("This function updates the respective .txt's for file persistence!");
    }

    @Deprecated
    /**
     * Deprecated function.
     * Would have been called at logout to load changes from Controller ArrayLists to the respective ones in controllers.Collection's 3 2D ArrayLists.
     */
    public void loadFromControllers(ArrayList<CardGame> newCG, ArrayList<TVSeries> newTV, ArrayList<Website> newWS){
        CGdatabase.get(current).clear();
        for (CardGame game : newCG)
            CGdatabase.get(current).add(game);

        TVdatabase.get(current).clear();
        for (TVSeries series : newTV)
            TVdatabase.get(current).add(series);

        WSdatabase.get(current).clear();
        for(Website site : newWS)
            WSdatabase.get(current).add(site);
    }
}