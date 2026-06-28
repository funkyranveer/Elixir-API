package me.funky.api.friend;

/**
 * A pending friend request sent to a profile.
 */
public interface IFriendRequest {

    /** The username of the player who sent this request. */
    String getRequesterName();

    /** Epoch millis when the request was sent. */
    long getRequestedAt();

    /**
     * Whether this request has expired (older than 5 minutes).
     * Expired requests should be ignored and cleaned up.
     */
    boolean isExpired();

    /** Remaining millis until this request expires, or 0 if already expired. */
    default long getRemainingMillis() {
        long remaining = (getRequestedAt() + 5 * 60 * 1000L) - System.currentTimeMillis();
        return Math.max(0L, remaining);
    }
}
