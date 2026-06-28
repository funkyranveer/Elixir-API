package me.funky.api.disguise;

import me.funky.api.rank.IRank;

/**
 * Holds the current disguise state of a player.
 */
public interface IDisguise {

    /** The player's real username. */
    String getRealName();

    /** The fake username the player is currently disguised as. */
    String getFakeName();

    /** The base64 skin texture value of the disguise skin. */
    String getSkinValue();

    /** The base64 skin texture signature of the disguise skin. */
    String getSkinSignature();

    /** The rank displayed while the player is disguised. */
    IRank getDisguiseRank();

    /** Full formatted name with disguise rank prefix and colour, e.g. "&7[MOD] &fNotch". */
    String getFormattedChatName();

    /** Colour code used in the nametag above the disguised player's head. */
    String getNametagColor();
}
