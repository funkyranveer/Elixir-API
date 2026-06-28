package me.funky.api.socials;

/**
 * Social media links stored on a profile.
 *
 * <p>Any getter may return {@code null} if the player has not linked that
 * platform. Use {@link #hasAnySocial()} to check whether at least one link
 * is set before rendering a socials menu.</p>
 */
public interface IUserSocials {

    String getYouTube();

    String getTwitter();

    String getTwitch();

    String getDiscord();

    String getInstagram();

    String getTikTok();

    String getSnapchat();

    // ── Convenience ──────────────────────────────────────────────────────────

    /** Returns {@code true} if at least one social link has been set. */
    default boolean hasAnySocial() {
        return getYouTube()   != null
            || getTwitter()   != null
            || getTwitch()    != null
            || getDiscord()   != null
            || getInstagram() != null
            || getTikTok()    != null
            || getSnapchat()  != null;
    }

    /** Returns the number of social platforms the player has linked. */
    default int getLinkedCount() {
        int count = 0;
        if (getYouTube()   != null) count++;
        if (getTwitter()   != null) count++;
        if (getTwitch()    != null) count++;
        if (getDiscord()   != null) count++;
        if (getInstagram() != null) count++;
        if (getTikTok()    != null) count++;
        if (getSnapchat()  != null) count++;
        return count;
    }
}
