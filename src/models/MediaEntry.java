package models;

public abstract class MediaEntry {
    // ---- Attributes ----
    protected String title;
    protected Status status;
    protected int rating;
    protected String review;

    //Getters
    /**
     * Gets the title of the current object.
     * @return the title String of the current object.
     */
    public String getTitle(){
        return title;
    }

    /**
     * Gets the status of the current object.
     * @return the status Status of the current object.
     */
    public Status getStatus(){
        return status;
    }

    /**
     * Gets the rating of the current object.
     * @return the rating int of the current object.
     */
    public int getRating(){
        return rating;
    }

    /**
     * Gets the review of the current object.
     * @return the review String of the current object.
     */
    public String getReview(){
        return review;
    }

    //Setters
    /**
     * Sets the title of the current object.
     * @param title contains the String to be put into the title field.<br>
     * <b>Precondition:</b> title is a valid String.<br>
     * <b>Postcondition:</b> title field is updated.
     */
    public void setTitle(String title){
        this.title = title;
    }
    /**
     * Sets the status of the current object.
     * @param status contains the Status to be put into the status field.<br>
     * <b>Precondition:</b> status is a valid Status<br>
     * <b>Postcondition:</b> status field is updated.
     */
    public void setStatus(Status status){
        this.status = status;
    }
    /**
     Sets the rating with additional logic to block ratings when the status is not yet completed.
     @param rating stores the score out of ten to be entered into the entry.<br>
     <b>Precondition:</b> rating is from zero to ten only<br>
     <b>Postcondition:</b> rating is updated only if status of entry is completed
     */
    public void setRating(int rating){
        if (this.status == Status.COMPLETED)
            if (rating >= 0 && rating <= 10)
                this.rating = rating;
            else
                System.out.println("Please input a valid rating out of 10.");
        else
            System.out.println("Please mark the entry as completed first.");
    }
    /**
     * Sets the review only if the entry is marked completed.
     * @param review stores the user's comment on the entry. <br>
     * <b>Precondition:</b> review is a valid String.<br>
     * <b>Postcondition:</b> review is updated only if entry is completed.
     */
    public void setReview(String review){
        if (this.status == Status.COMPLETED)
            this.review = review;
        else
            System.out.println("Please mark the entry as completed first.");
    }
}
