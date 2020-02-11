package uk.offtopica.monerorpc.wallet;

import lombok.Data;
import lombok.NonNull;

/**
 * An address-amount pair.
 */
@Data
public class TransferDestination {
    /**
     * The destination's public wallet address.
     */
    @NonNull
    private String address;

    /**
     * The amount to send to this wallet address, in atomic units.
     * <p>
     * 1e12 atomic units = 1 XMR.
     */
    @NonNull
    private Long amount;
}
