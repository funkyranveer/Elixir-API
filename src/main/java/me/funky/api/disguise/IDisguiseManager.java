package me.funky.api.disguise;

import java.util.Collection;
import java.util.UUID;

/**
 * Provides access to the disguise registry.
 */
public interface IDisguiseManager {

    /**
     * Returns the active disguise for the given player UUID,
     * or {@code null} if the player is not currently disguised.
     */
    IDisguise getDisguise(UUID playerUuid);

    /** Whether the player with the given UUID is currently disguised. */
    boolean isDisguised(UUID playerUuid);

    /** All currently active disguises across every online player. */
    Collection<IDisguise> getActiveDisguises();

    /** Number of players currently disguised. */
    default int getDisguiseCount() {
        return getActiveDisguises().size();
    }

    /**
     * Returns the disguise whose fake name matches the given string
     * (case-insensitive), or {@code null} if no player is disguised as that name.
     *
     * <p>Useful for resolving a fake username back to the real player.</p>
     */
    default IDisguise getByFakeName(String fakeName) {
        for (IDisguise d : getActiveDisguises()) {
            if (d.getFakeName().equalsIgnoreCase(fakeName)) return d;
        }
        return null;
    }

    /**
     * Whether any online player is currently disguised under the given fake name
     * (case-insensitive).
     */
    default boolean isFakeNameTaken(String fakeName) {
        return getByFakeName(fakeName) != null;
    }
}
