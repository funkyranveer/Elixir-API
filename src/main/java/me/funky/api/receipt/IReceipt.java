package me.funky.api.receipt;

import java.util.UUID;

/**
 * Represents a shop purchase receipt stored on a profile.
 */
public interface IReceipt {

    /** Unique identifier of this receipt. */
    UUID getUuid();

    /** Epoch millis when the purchase was made. */
    long getPurchasedAt();

    /** The product name (e.g. the rank name that was purchased). */
    String getProduct();

    /** The coin price paid at time of purchase. */
    int getPrice();
}
