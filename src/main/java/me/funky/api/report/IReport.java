package me.funky.api.report;

import java.util.UUID;

/**
 * A player report submitted against a profile.
 */
public interface IReport {

    /** Unique identifier of this report. */
    UUID getUniqueId();

    /** UUID of the player who submitted the report. */
    UUID getAddedBy();

    /** Reason provided in the report. */
    String getAddedReason();

    /** Name of the server the report was filed from. */
    String getAddedServer();

    /** Epoch millis when the report was submitted. */
    long getAddedAt();

    /** Whether a staff member has marked this report as resolved. */
    boolean isSolved();

    /** UUID of the staff member who resolved the report, or the console UUID if auto-closed. */
    UUID getSolvedBy();

    /** Epoch millis when the report was resolved. */
    long getSolvedAt();

    /** Name of the server on which the report was resolved. */
    String getSolvedServer();
}
