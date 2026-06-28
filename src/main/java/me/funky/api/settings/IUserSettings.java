package me.funky.api.settings;

/**
 * Holds a player's configurable toggle preferences.
 *
 * <p>All settings default to sane values (enabled where beneficial, disabled
 * where it would be intrusive). Staff-specific settings are only meaningful
 * for players holding a staff rank.</p>
 */
public interface IUserSettings {

    // ── Chat ─────────────────────────────────────────────────────────────────

    /** Whether the player receives messages in the global chat channel. Default: {@code true}. */
    boolean isGlobalChat();

    /** Whether the player receives periodic server tip broadcasts. Default: {@code true}. */
    boolean isReceivingTips();

    /**
     * Whether the player accepts incoming private conversation (DM) requests
     * from other players they have not previously messaged. Default: {@code true}.
     */
    boolean isReceivingNewConversations();

    /** Whether a sound effect plays when the player receives a private message. Default: {@code true}. */
    boolean isPlayMessageSounds();

    /**
     * Whether the player receives a notification when their username is mentioned
     * in global chat. Default: {@code true}.
     */
    boolean isChatPings();

    /** Whether the player accepts incoming friend requests. Default: {@code true}. */
    boolean isFriendRequests();

    // ── Staff ─────────────────────────────────────────────────────────────────

    /**
     * Whether staff mode is automatically enabled when the player joins the server.
     * Only meaningful for players with staff rank. Default: {@code false}.
     */
    boolean isAutoStaff();

    /**
     * Whether vanish is automatically enabled when the player joins the server.
     * Only meaningful for players with staff rank. Default: {@code false}.
     */
    boolean isAutoVanish();
}
