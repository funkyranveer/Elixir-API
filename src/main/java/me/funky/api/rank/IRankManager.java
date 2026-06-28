package me.funky.api.rank;

import java.util.List;
import java.util.UUID;

/**
 * Provides access to the rank registry.
 */
public interface IRankManager {

    // ── Lookup ───────────────────────────────────────────────────────────────

    /** Returns the rank with the given UUID, or {@code null}. */
    IRank getByUUID(UUID uuid);

    /** Returns the rank with the given name (case-insensitive), or {@code null}. */
    IRank getByName(String name);

    /** Returns the default rank ({@code RankType.DEFAULT}), or {@code null} if misconfigured. */
    IRank getDefault();

    /** Whether a rank with the given name (case-insensitive) exists. */
    default boolean exists(String name) {
        return getByName(name) != null;
    }

    // ── Collections ──────────────────────────────────────────────────────────

    /** All registered ranks, in no particular order. */
    List<IRank> getRanks();

    /** Ranks sorted ascending by weight (lowest first). */
    List<IRank> getSortedRanks();

    /** Ranks sorted descending by weight (highest first). */
    List<IRank> getSortedRanksFromTop();

    /** Only ranks of type {@code STAFF}. */
    List<IRank> getStaffRanks();

    /** Only ranks that are marked as purchasable. */
    List<IRank> getPurchasableRanks();

    /** Only ranks that are not hidden (type != {@code HIDDEN}). */
    List<IRank> getPublicRanks();

    // ── Permissions ──────────────────────────────────────────────────────────

    /**
     * Resolves all effective permissions for a given rank name,
     * including permissions inherited from every parent rank.
     * Returns an empty list if the rank does not exist.
     */
    List<String> getEffectivePermissions(String rankName);

    // ── Utility ──────────────────────────────────────────────────────────────

    /** Total number of registered ranks. */
    default int getRankCount() {
        return getRanks().size();
    }
}
