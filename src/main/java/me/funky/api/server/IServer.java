package me.funky.api.server;

/**
 * Represents a network server tracked by Elixir's heartbeat system.
 *
 * <p>Servers are discovered automatically when they broadcast a heartbeat packet
 * over Redis. A server transitions to OFFLINE state when its heartbeat goes stale
 * (no packet received within the configured threshold).</p>
 */
public interface IServer {

    // ── Identity ─────────────────────────────────────────────────────────────

    /** The server's configured name (e.g. {@code "Hub-1"}, {@code "Practice-1"}). */
    String getName();

    // ── Status ───────────────────────────────────────────────────────────────

    /**
     * Current status as a raw string: {@code "ONLINE"}, {@code "OFFLINE"},
     * or {@code "WHITELISTED"}.
     */
    String getStatus();

    /** Whether this server is currently accepting players (status == ONLINE). */
    boolean isOnline();

    /** Whether this server is currently whitelisted (status == WHITELISTED). */
    boolean isWhitelisted();

    /** Whether this server is currently offline (status == OFFLINE). */
    default boolean isOffline() {
        return !isOnline() && !isWhitelisted();
    }

    // ── Players ──────────────────────────────────────────────────────────────

    /** Number of players currently online on this server. */
    int getOnlinePlayers();

    /** Maximum player slots configured for this server. */
    int getMaxPlayers();

    /** Whether this server is at or above its maximum player capacity. */
    default boolean isFull() {
        return getMaxPlayers() > 0 && getOnlinePlayers() >= getMaxPlayers();
    }

    /**
     * The player count as a formatted string, e.g. {@code "42/100"}.
     */
    default String getFormattedPlayerCount() {
        return getOnlinePlayers() + "/" + getMaxPlayers();
    }

    // ── Performance ──────────────────────────────────────────────────────────

    /**
     * TPS readings as a 3-element array: [1-min avg, 5-min avg, 15-min avg].
     * May be {@code null} or stale if no heartbeat has been received recently.
     */
    double[] getTps();

    /**
     * Human-readable TPS string, e.g. {@code "20.00, 19.98, 20.00"}.
     * Returns {@code "N/A"} if TPS data is unavailable.
     */
    String getFormattedTps();

    /**
     * The 1-minute average TPS, or {@code -1.0} if unavailable.
     */
    default double getTps1Min() {
        double[] tps = getTps();
        return (tps != null && tps.length > 0) ? tps[0] : -1.0;
    }

    // ── Heartbeat ────────────────────────────────────────────────────────────

    /** Epoch millis of the last received heartbeat packet from this server. */
    long getLastHeartbeat();

    /** Milliseconds elapsed since the last heartbeat was received. */
    default long millisSinceHeartbeat() {
        return System.currentTimeMillis() - getLastHeartbeat();
    }

    /**
     * Whether the heartbeat is considered stale (older than the configured
     * threshold, typically 10 seconds).
     */
    default boolean isHeartbeatStale() {
        return millisSinceHeartbeat() > 10_000L;
    }
}
