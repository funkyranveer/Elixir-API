package me.funky.api.note;

import java.util.UUID;

/**
 * A staff note attached to a profile.
 */
public interface INote {

    /** Unique identifier of this note. */
    UUID getUniqueId();

    /** UUID of the staff member who wrote the note. */
    UUID getAddedBy();

    /** The note text. */
    String getNote();

    /** Epoch millis when the note was created. */
    long getAddedAt();
}
