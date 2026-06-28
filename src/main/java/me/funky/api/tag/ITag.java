package me.funky.api.tag;

import java.util.UUID;

/**
 * Represents a cosmetic chat tag that players can equip.
 *
 * <p>Tags are displayed after the player's name in chat. A player must hold
 * the tag's {@link #getPermission()} node to equip it. Tags are sorted by
 * {@link #getWeight()} — lower weight = higher priority in sorted lists.</p>
 */
public interface ITag {

    // ── Identity ─────────────────────────────────────────────────────────────

    UUID getUuid();

    String getName();

    // ── Display ──────────────────────────────────────────────────────────────

    /**
     * The coloured prefix string placed after the player's name in chat,
     * e.g. {@code "&b[STAFF]"}.
     */
    String getPrefix();

    /**
     * The colour code applied to the tag label itself,
     * e.g. {@code "&b"}.
     */
    String getColor();

    /**
     * Convenience: {@link #getColor()} + {@link #getName()},
     * e.g. {@code "&bStaff"}.
     */
    String getFormattedTag();

    // ── Access ───────────────────────────────────────────────────────────────

    /**
     * The Bukkit permission node a player must hold to equip this tag,
     * e.g. {@code "elixir.tags.staff"}.
     */
    String getPermission();

    // ── Ordering ─────────────────────────────────────────────────────────────

    /**
     * Ordering weight used when sorting tag lists.
     * Lower values appear first in ascending sorts.
     */
    int getWeight();

    // ── Category ─────────────────────────────────────────────────────────────

    /**
     * The category this tag belongs to.
     * Raw enum name: {@code "DEFAULT"}, {@code "COUNTRY"}, {@code "SYMBOL"},
     * {@code "PARTNER"}, or {@code "SPECIAL"}.
     */
    String getTagType();

    /** Whether this tag belongs to the given type string (case-insensitive). */
    default boolean isType(String type) {
        return getTagType() != null && getTagType().equalsIgnoreCase(type);
    }
}
