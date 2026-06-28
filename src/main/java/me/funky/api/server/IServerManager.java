package me.funky.api.server;

import java.util.Collection;

/**
 * Provides access to all servers tracked on the Elixir network.
 */
public interface IServerManager {

    // ── Lookup ───────────────────────────────────────────────────────────────

    /**
     * Returns the server with the given name (case-insensitive), or {@code null}
     * if no server with that name is registered.
     */
    IServer getServer(String name);

    /**
     * Returns the local server instance that this plugin is currently running on.
     * Never {@code null}.
     */
    IServer getLocalServer();

    // ── Collections ──────────────────────────────────────────────────────────

    /** All known servers, including offline ones. */
    Collection<IServer> getAllServers();

    /** Only servers whose status is ONLINE. */
    Collection<IServer> getOnlineServers();

    /** Only servers whose status is WHITELISTED. */
    Collection<IServer> getWhitelistedServers();

    /** Only servers whose status is OFFLINE. */
    Collection<IServer> getOfflineServers();

    // ── Aggregates ───────────────────────────────────────────────────────────

    /** Total player count across all ONLINE servers. */
    int getTotalOnlinePlayers();

    /** Total number of known servers (any status). */
    default int getServerCount() {
        return getAllServers().size();
    }

    /** Number of servers with ONLINE status. */
    default int getOnlineServerCount() {
        return getOnlineServers().size();
    }

    // ── Utility ──────────────────────────────────────────────────────────────

    /** Whether the local server is currently whitelisted. */
    boolean isLocalWhitelisted();

    /** Whether a server with the given name (case-insensitive) is known. */
    default boolean exists(String name) {
        return getServer(name) != null;
    }
}
