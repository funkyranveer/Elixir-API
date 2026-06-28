package me.funky.api.punishment;

import java.util.UUID;

/**
 * Represents a punishment entry on a profile.
 */
public interface IPunishment {

    // ── Identity ─────────────────────────────────────────────────────────────

    /** Unique identifier of this punishment record. */
    UUID getUniqueId();

    /** The type of punishment (ban, mute, kick, warn, etc.). */
    IPunishmentType getType();

    // ── Issuance ─────────────────────────────────────────────────────────────

    /** UUID of the staff member or system that issued this punishment. */
    UUID getAddedBy();

    /** Epoch millis when the punishment was issued. */
    long getAddedOn();

    /**
     * Duration in milliseconds.
     * {@link Integer#MAX_VALUE} indicates a permanent punishment.
     */
    long getDuration();

    /** Reason provided when the punishment was issued. */
    String getAddedReason();

    // ── Status ───────────────────────────────────────────────────────────────

    /**
     * Whether this punishment is currently active.
     * A punishment is active when it is neither expired nor pardoned.
     */
    boolean isActive();

    /** Whether this punishment has passed its expiry time. */
    boolean isExpired();

    /**
     * Epoch millis at which this punishment expires.
     * Returns {@link Long#MAX_VALUE} for permanent punishments.
     */
    long getExpireDate();

    /**
     * Human-readable time remaining until expiry, e.g. {@code "1d 4h 30m"}.
     * Returns {@code "Never"} for permanent punishments and {@code "Expired"} once elapsed.
     */
    String getExpiresIn();

    /**
     * Human-readable total duration, e.g. {@code "7 days"}.
     * Returns {@code "Permanent"} for permanent punishments.
     */
    String getPunishmentDuration();

    /** Whether this punishment is permanent (duration == {@link Integer#MAX_VALUE}). */
    default boolean isPermanent() {
        return getDuration() == Integer.MAX_VALUE;
    }

    // ── Pardon ───────────────────────────────────────────────────────────────

    /** Whether this punishment has been manually pardoned by a staff member. */
    boolean isPardoned();

    /** UUID of the staff member who pardoned this punishment. */
    UUID getPardonedBy();

    /** Epoch millis when the pardon was applied. */
    long getPardonedOn();

    /** Reason given at pardon time. */
    String getPardonedReason();
}
