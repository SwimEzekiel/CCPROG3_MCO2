package models;

/**
 * Represents an episode inside a TV Series
 * Since TV Series are episodic, we'll use Episode to represent each episode
 * Can still be treated like a media entry wherein it has its own status, rating, and other stuff.
 *
 * @author Ezekiel Alvarez
 */

public class Episodes extends MediaEntry {
    // Attributes
    private int runTime;

    //Constructor

    /**
     * Initializes a new Episode object.
     *
     * @param title stores the title of the new Episodes object.
     * @param status stores the status of the new Episodes object.
     * @param runTime stores the run time of the new Episodes.<br>
     *
     * <b>Precondition:</b>
     * <pre>
     * status may only be PLANNED or IN_PROGRESS.<br>
     * params must be valid per their data type.<br>
     * </pre>
     * <b>Postcondition:</b> a new Episode object is made.
     */
    public Episodes(String title, Status status, int runTime){
        if (status != Status.COMPLETED){
            this.title = title;
            this.status = status;
            this.runTime = runTime;
            review = "";
            rating = -1;
        } else
            System.out.println("Entries cannot be created with COMPLETED status.");
    }
    //Getter Functions
    public int getRunTime() {
        return runTime;
    }

    //Setter Functions
    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }
}
