package me.funky.api.grant;

import me.funky.api.rank.IRank;

import java.util.UUID;

/**
 * Represents a rank grant assigned to a profile.
 *
 * <p>A grant ties a player to a rank for a specific duration (or permanently).
 * Multiple grants can coexist on a profile; the active one is the highest-weight
 * grant that is neither expired nor pardoned.</p>
 */
public interface IGrant {

    // ── Identity ─────────────────────────────────────────────────────────────

    /** Unique identifier of this grant record. */
    UUID getUniqueId();

    /** Name of the rank that was granted. */
    String getRankName();

    /**
     * Resolved rank object. If the original rank has since been deleted,
     * this returns the Default rank as a fallback — never {@code null}.
     */
    IRank getRank();

    // ── Issuance ─────────────────────────────────────────────────────────────

    /** UUID of the staff member or system ({@code CONSOLE_UUID}) that issued this grant. */
    UUID getAddedBy();

    /** Epoch millis when the grant was issued. */
    long getAddedOn();

    /**
     * Duration in milliseconds.
     * {@link Integer#MAX_VALUE} (treated as permanent) indicates the grant never expires.
     */
    long getDuration();

    /** Reason provided when the grant was issued. */
    String getAddedReason();

    // ── Status ───────────────────────────────────────────────────────────────

    /**
     * Whether this grant is currently active.
     * A grant is active when it is neither expired nor pardoned.
     */
    boolean isActive();

    /** Whether this grant has passed its expiry time. */
    boolean isExpired();

    /**
     * Epoch millis at which this grant expires.
     * Returns {@link Long#MAX_VALUE} for permanent grants.
     */
    long getExpireDate();

    /**
     * Human-readable time remaining until expiry, e.g. {@code "3d 2h 10m"}.
     * Returns {@code "Never"} for permanent grants and {@code "Expired"} once elapsed.
     */
    String getExpiresIn();

    /**
     * Human-readable total duration of the grant, e.g. {@code "7 days"}.
     * Returns {@code "Permanent"} for permanent grants.
     */
    String getGrantDuration();

    /** Whether this grant is permanent (i.e. duration == {@link Integer#MAX_VALUE}). */
    default boolean isPermanent() {
        return getDuration() == Integer.MAX_VALUE;
    }

    // ── Pardon ───────────────────────────────────────────────────────────────

    /** Whether this grant has been manually pardoned by a staff member. */
    boolean isPardoned();

    /** UUID of the staff member who pardoned this grant. */
    UUID getPardonedBy();

    /** Epoch millis when the pardon was applied. */
    long getPardonedOn();

    /** Reason given when the grant was pardoned. */
    String getPardonedReason();
}
