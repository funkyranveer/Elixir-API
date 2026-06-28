package me.funky.api.punishment;

/**
 * Describes the category of a punishment.
 *
 * <p>The concrete values are defined by the Bukkit module's {@code PunishmentType}
 * enum.  External plugins should compare via {@link #name()} rather than an
 * instanceof check since the enum lives in the implementation module.</p>
 */
public interface IPunishmentType {

    /** Short display label, e.g. {@code "Perm Ban"}, {@code "Warn"}. */
    String getContext();

    /** Past-tense description used in issued-punishment messages, e.g. {@code "permanently banned"}. */
    String getPunished();

    /** Past-tense description used in pardon messages, e.g. {@code "unbanned"}. */
    String getPardoned();

    /**
     * Raw enum constant name, e.g. {@code "PERM_BAN"}, {@code "TEMP_MUTE"}.
     * Use this for identity comparisons from external plugins.
     */
    String name();

    // ── Behaviour flags ───────────────────────────────────────────────────────

    /**
     * Whether an active punishment of this type blocks the player from
     * connecting to the server (BLACKLIST, PERM_BAN, TEMP_BAN).
     */
    boolean preventsLogin();

    /**
     * Whether an active punishment of this type blocks the player from
     * sending messages in chat (PERM_MUTE, TEMP_MUTE).
     */
    boolean preventsChatting();

    /**
     * Whether punishments of this type can have a finite expiry duration
     * (TEMP_BAN, TEMP_MUTE).  Punishments without expiry support are either
     * permanent or one-shot (KICK, WARN).
     */
    boolean supportsExpiry();

    /**
     * Whether punishments of this type are one-shot and require no pardon
     * (KICK, WARN).  These are never "active" after the initial action.
     */
    default boolean isInstant() {
        return !preventsLogin() && !preventsChatting();
    }

    /**
     * Whether punishments of this type can be pardoned by staff
     * (all types except KICK and WARN).
     */
    default boolean isPardonable() {
        return !isInstant();
    }
}
