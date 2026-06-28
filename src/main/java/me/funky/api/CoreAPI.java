package me.funky.api;

import me.funky.api.disguise.IDisguiseManager;
import me.funky.api.profile.IProfileManager;
import me.funky.api.rank.IRankManager;
import me.funky.api.server.IServerManager;
import me.funky.api.tag.ITagManager;

/**
 * Central access point for the Elixir API.
 * Obtain via {@link CoreAPI#getInstance()}.
 */
public final class CoreAPI {

    private static CoreAPI instance;

    private IProfileManager profileManager;
    private IRankManager rankManager;
    private ITagManager tagManager;
    private IServerManager serverManager;
    private IDisguiseManager disguiseManager;

    public CoreAPI() {
        if (instance == null) {
            instance = this;
        }
    }

    public static CoreAPI getInstance() {
        return instance;
    }

    // ── Profile ──────────────────────────────────────────────────────────────

    public void setProfileManager(IProfileManager profileManager) {
        if (this.profileManager != null) throw new IllegalStateException("ProfileManager already set.");
        this.profileManager = profileManager;
    }

    public IProfileManager getProfileManager() {
        return profileManager;
    }

    // ── Rank ─────────────────────────────────────────────────────────────────

    public void setRankManager(IRankManager rankManager) {
        if (this.rankManager != null) throw new IllegalStateException("RankManager already set.");
        this.rankManager = rankManager;
    }

    public IRankManager getRankManager() {
        return rankManager;
    }

    // ── Tag ──────────────────────────────────────────────────────────────────

    public void setTagManager(ITagManager tagManager) {
        if (this.tagManager != null) throw new IllegalStateException("TagManager already set.");
        this.tagManager = tagManager;
    }

    public ITagManager getTagManager() {
        return tagManager;
    }

    // ── Server ───────────────────────────────────────────────────────────────

    public void setServerManager(IServerManager serverManager) {
        if (this.serverManager != null) throw new IllegalStateException("ServerManager already set.");
        this.serverManager = serverManager;
    }

    public IServerManager getServerManager() {
        return serverManager;
    }

    // ── Disguise ─────────────────────────────────────────────────────────────

    public void setDisguiseManager(IDisguiseManager disguiseManager) {
        if (this.disguiseManager != null) throw new IllegalStateException("DisguiseManager already set.");
        this.disguiseManager = disguiseManager;
    }

    public IDisguiseManager getDisguiseManager() {
        return disguiseManager;
    }
}
