package me.funky.api.rank;

import java.util.List;
import java.util.UUID;

/**
 * Represents a rank in the Elixir rank hierarchy.
 *
 * <p>Ranks are ordered by {@link #getWeight()} — higher weight means a
 * higher-priority rank. The active rank for any player is the highest-weight
 * grant that is neither expired nor pardoned.</p>
 */
public interface IRank {

    // ── Identity ─────────────────────────────────────────────────────────────

    UUID getUuid();

    String getName();

    // ── Display ──────────────────────────────────────────────────────────────

    /**
     * The coloured chat prefix placed before the player's name,
     * e.g. {@code "&c[OWNER] "}.  May be empty for the default rank.
     */
    String getPrefix();

    /**
     * The colour code applied to the player's name in chat and the tab list,
     * e.g. {@code "&c"}.
     */
    String getColor();

    /**
     * Short convenience: {@link #getColor()} + {@link #getName()},
     * e.g. {@code "&cOwner"}.
     */
    String getFormattedRank();

    /**
     * Full display string including prefix and coloured name,
     * e.g. {@code "&c[OWNER] &cOwner"}.  Useful for menus.
     */
    default String getFullDisplay() {
        return getPrefix() + getColor() + getName();
    }

    // ── Hierarchy ────────────────────────────────────────────────────────────

    /**
     * Ordering weight. Higher weight = higher rank. The active rank for a
     * player is the highest-weight non-expired, non-pardoned grant they hold.
     */
    int getWeight();

    /** Names of ranks whose permissions this rank inherits. */
    List<String> getInheritance();

    // ── Permissions ──────────────────────────────────────────────────────────

    /** Permissions granted directly by this rank (excludes inherited permissions). */
    List<String> getPermissions();

    /**
     * All effective permissions for this rank, including those inherited
     * from every parent rank in the inheritance chain.
     */
    List<String> getEffectivePermissions();

    /**
     * Whether this rank directly grants the given permission node.
     * Does not check inherited permissions — use {@link #hasEffectivePermission(String)}
     * for that.
     */
    default boolean hasDirectPermission(String permission) {
        return getPermissions().contains(permission);
    }

    /**
     * Whether this rank grants the given permission node either directly
     * or through inheritance.
     */
    default boolean hasEffectivePermission(String permission) {
        return getEffectivePermissions().contains(permission);
    }

    // ── Type flags ───────────────────────────────────────────────────────────

    /** The raw type name: {@code "DEFAULT"}, {@code "NORMAL"}, {@code "HIDDEN"}, or {@code "STAFF"}. */
    String getRankType();

    /** Whether this rank is the network default (assigned when no other grant is active). */
    boolean isDefault();

    /** Whether this is a staff rank (type == STAFF). Staff ranks grant moderation privileges. */
    boolean isStaff();

    /**
     * Whether this rank is hidden from player-facing lists such as the shop
     * and public rank roster. Hidden ranks are still functional.
     */
    boolean isHidden();

    // ── Shop ─────────────────────────────────────────────────────────────────

    /** Whether this rank can be purchased by players via the in-game shop. */
    boolean isPurchasable();

    /** The coin price to purchase this rank, or {@code 0} if not purchasable. */
    int getPrice();

    // ── Backward compatibility ────────────────────────────────────────────────

    /** Alias for {@link #getColor()} — kept for backward compatibility. */
    default String getDisplayColor() {
        return getColor();
    }
}
