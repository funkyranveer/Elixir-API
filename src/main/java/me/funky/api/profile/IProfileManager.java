package me.funky.api.profile;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Central access point for player profiles on the Elixir network.
 *
 * <p>Methods prefixed with {@code get} operate only on the in-memory cache
 * (online players on this server) and return {@code null} immediately if
 * the player is not loaded. Methods prefixed with {@code find} may hit
 * MongoDB and must be called off the main thread.</p>
 */
public interface IProfileManager {

    // ── In-memory (online only) ───────────────────────────────────────────────

    /**
     * Returns the online profile for the given UUID, or {@code null} if the
     * player is not currently loaded on this server.
     */
    IProfile getByUUID(UUID uuid);

    /**
     * Returns the online profile for the given name (case-insensitive), or
     * {@code null} if not loaded.
     */
    IProfile getByName(String name);

    /**
     * All profiles currently loaded in memory.
     * This is effectively the set of online players on this server.
     */
    Collection<IProfile> getOnlineProfiles();

    /** Profiles of players currently in staff mode. */
    List<IProfile> getStaffModeProfiles();

    /** Profiles of players currently vanished. */
    List<IProfile> getVanishedProfiles();

    /** Profiles of players currently frozen. */
    List<IProfile> getFrozenProfiles();

    /** Number of profiles currently loaded in memory. */
    default int getOnlineCount() {
        return getOnlineProfiles().size();
    }

    // ── Database (may be offline) ─────────────────────────────────────────────

    /**
     * Returns the profile for the given UUID. If the player is online the
     * cached profile is returned immediately; otherwise the profile is loaded
     * from MongoDB.
     *
     * <p><b>Warning:</b> May block on a database call — always call off the main thread.</p>
     *
     * @return the profile, or {@code null} if this UUID has never been seen
     */
    IProfile findById(UUID uuid);

    /**
     * Returns the profile for the given username. If the player is online the
     * cached profile is returned; otherwise the profile is loaded from MongoDB.
     *
     * <p><b>Warning:</b> May block on a database call — always call off the main thread.</p>
     *
     * @return the profile, or {@code null} if the name is unknown
     */
    IProfile findByName(String name);

    /**
     * Returns all profiles that share the given IP address.
     * This queries MongoDB directly and should be called off the main thread.
     *
     * @return list of profiles sharing the IP, may be empty but never {@code null}
     */
    List<IProfile> findByIpAddress(String ipAddress);

    // ── Utility ──────────────────────────────────────────────────────────────

    /**
     * Returns {@code true} if a profile with the given UUID is currently
     * loaded in memory (i.e. the player is online on this server).
     */
    boolean isOnline(UUID uuid);

    /**
     * Returns {@code true} if a profile with the given name (case-insensitive)
     * is currently loaded in memory.
     */
    boolean isOnline(String name);
}
