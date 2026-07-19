package models;

/**
 * Defines status constants as outlined in MCO specifications for
 * consistency even if different media types may interpret the
 * same status differently.
 * (Example: PLANNED in controllers.CardGame is "Added to cart", while the
 *  same is "Scouted" in Website.)
 *
 * @author Matthew Alfonso Beltran
 */

public enum Status {
    /**
     * The user plans to consume this media in the future.
     */
    PLANNED,

    /**
     * The user is currently consuming this media.
     */
    IN_PROGRESS,

    /**
     * The user has consumed this media and can give a review to the entry.
     */
    COMPLETED
}
