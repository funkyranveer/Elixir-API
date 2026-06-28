package me.funky.api.tag;

import java.util.List;
import java.util.UUID;

/**
 * Provides access to the tag registry.
 */
public interface ITagManager {

    // ── Lookup ───────────────────────────────────────────────────────────────

    /** Returns the tag with the given UUID, or {@code null}. */
    ITag getByUUID(UUID uuid);

    /** Returns the tag with the given name (case-insensitive), or {@code null}. */
    ITag getByName(String name);

    /** Whether a tag with the given name (case-insensitive) exists. */
    default boolean exists(String name) {
        return getByName(name) != null;
    }

    // ── Collections ──────────────────────────────────────────────────────────

    /** All registered tags, in no particular order. */
    List<ITag> getTags();

    /** Tags sorted ascending by weight (lowest weight first). */
    List<ITag> getSortedTags();

    /** Tags sorted descending by weight (highest weight first). */
    List<ITag> getSortedTagsFromTop();

    /**
     * All tags belonging to the given type string (case-insensitive),
     * e.g. {@code "COUNTRY"}, {@code "SYMBOL"}, {@code "PARTNER"}.
     * Returns an empty list if none match.
     */
    List<ITag> getTagsByType(String tagType);

    // ── Utility ──────────────────────────────────────────────────────────────

    /** Total number of registered tags. */
    default int getTagCount() {
        return getTags().size();
    }
}
