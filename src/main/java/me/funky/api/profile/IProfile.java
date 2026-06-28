package me.funky.api.profile;

import me.funky.api.friend.IFriendRequest;
import me.funky.api.grant.IGrant;
import me.funky.api.note.INote;
import me.funky.api.punishment.IPunishment;
import me.funky.api.punishment.IPunishmentType;
import me.funky.api.rank.IRank;
import me.funky.api.receipt.IReceipt;
import me.funky.api.report.IReport;
import me.funky.api.settings.IUserSettings;
import me.funky.api.socials.IUserSocials;
import me.funky.api.tag.ITag;

import java.util.List;
import java.util.UUID;

/**
 * A complete view of a player's stored profile on the Elixir network.
 *
 * <p>Obtain a profile via {@link IProfileManager#getByUUID(UUID)} for online
 * players, or {@link IProfileManager#findById(UUID)} / {@link IProfileManager#findByName(String)}
 * for offline lookups (those methods may hit MongoDB — call them off the main thread).</p>
 */
public interface IProfile {

    // ── Identity ─────────────────────────────────────────────────────────────

    /** The player's Minecraft UUID. */
    UUID getUuid();

    /** The player's current username. */
    String getName();

    /** Coloured name using the active rank's colour code, e.g. {@code "&cRanveer"}. */
    String getFormattedName();

    /**
     * Full chat-prefix form: rank prefix + rank colour + name + active tag prefix.
     * E.g. {@code "&c[OWNER] &cRanveer &b[MVP]"}.
     */
    String getChatFormattedName();

    // ── Rank & Grants ────────────────────────────────────────────────────────

    /**
     * The rank object of the player's highest active, non-expired grant.
     * Falls back to the Default rank — never {@code null} for a properly
     * initialised profile.
     */
    IRank getActiveRank();

    /**
     * The full grant entry that is currently active (highest weight,
     * not expired, not pardoned).
     */
    IGrant getActiveGrant();

    /** All grant records on this profile (active, expired, and pardoned). */
    List<IGrant> getGrants();

    /** Only grants that are currently active (not expired, not pardoned). */
    List<IGrant> getActiveGrants();

    /**
     * Whether the player holds any active grant for the given rank name
     * (case-insensitive).
     */
    boolean hasActiveGrant(String rankName);

    // ── Punishments ──────────────────────────────────────────────────────────

    /** All punishment records (active, expired, and pardoned). */
    List<IPunishment> getPunishments();

    /**
     * The active punishment of the given type, or {@code null} if none.
     * A punishment is active when it is neither expired nor pardoned.
     */
    IPunishment getActivePunishment(IPunishmentType type);

    /** Whether the player is currently banned (perm or temp ban, not blacklist). */
    boolean isBanned();

    /** Whether the player is currently muted (perm or temp mute). */
    boolean isMuted();

    /** Whether the player is currently blacklisted. */
    boolean isBlacklisted();

    /** Whether the player is currently warned. */
    boolean isWarned();

    // ── Permissions ──────────────────────────────────────────────────────────

    /** Personal permissions attached directly to this profile (not from rank). */
    List<String> getPermissions();

    /**
     * Whether this profile has the given permission node.
     * Checks both personal permissions and the active rank's effective permissions
     * (including inherited permissions).
     */
    boolean hasPermission(String permission);

    // ── Friends ──────────────────────────────────────────────────────────────

    /** Usernames of players this profile has added as friends. */
    List<String> getFriends();

    /** Whether this profile has the given player as a friend (case-insensitive). */
    boolean hasFriend(String name);

    /** Total number of friends. */
    default int getFriendCount() {
        return getFriends().size();
    }

    /** Pending incoming friend requests (may include expired ones — check {@link IFriendRequest#isExpired()}). */
    List<IFriendRequest> getFriendRequests();

    /** Pending friend requests that have not yet expired. */
    List<IFriendRequest> getActiveFriendRequests();

    // ── Alt & IP Tracking ────────────────────────────────────────────────────

    /** Usernames of other accounts detected sharing an IP with this profile. */
    List<String> getKnownAlts();

    /** All IP addresses this profile has connected from. */
    List<String> getIpAddresses();

    /** The most recently recorded IP address, or {@code null} if unavailable. */
    String getCurrentAddress();

    // ── Notes & Reports ──────────────────────────────────────────────────────

    /** All staff notes attached to this profile. */
    List<INote> getNotes();

    /** All player reports filed against this profile. */
    List<IReport> getReports();

    /** Only open (unsolved) reports filed against this profile. */
    List<IReport> getOpenReports();

    // ── Tag ──────────────────────────────────────────────────────────────────

    /**
     * The cosmetic tag the player currently has equipped, or {@code null}
     * if no tag is active.
     */
    ITag getActiveTag();

    // ── Settings & Socials ───────────────────────────────────────────────────

    /** The player's configurable toggle preferences. */
    IUserSettings getSettings();

    /** The player's linked social media accounts. */
    IUserSocials getSocials();

    // ── Economy ──────────────────────────────────────────────────────────────

    /** The player's current coin balance. */
    int getCoins();

    /** All shop purchase receipts for this profile. */
    List<IReceipt> getReceipts();

    /**
     * Whether this profile has previously purchased the given product name
     * (case-insensitive match against receipt product strings).
     */
    boolean hasPurchased(String product);

    // ── Timestamps ───────────────────────────────────────────────────────────

    /** Epoch millis of the player's first login, or {@code null} if not recorded. */
    Long getFirstSeen();

    /** Epoch millis of the player's most recent session start. */
    Long getLastSeen();

    /** Name of the server the player was last seen on. */
    String getLastSeenServer();

    // ── State Flags ──────────────────────────────────────────────────────────

    /** Whether the player is currently in staff mode on this server. */
    boolean isStaffMode();

    /** Whether the player is currently vanished. */
    boolean isVanished();

    /** Whether the player is currently frozen by staff. */
    boolean isFrozen();

    /** Whether the player has verified their NameMC account. */
    boolean isVerified();

    /** Whether this profile's data has finished loading from MongoDB. */
    boolean isLoaded();

    /** Whether the player is currently online on this server. */
    boolean isOnline();
}
