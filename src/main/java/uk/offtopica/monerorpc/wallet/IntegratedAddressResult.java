package uk.offtopica.monerorpc.wallet;

import lombok.Data;
import lombok.NonNull;

/**
 * Result of a {@link MoneroWalletRpcClient#createIntegratedAddress(String, String)} call.
 */
@Data
public class IntegratedAddressResult {
    /**
     * An integrated address.
     */
    @NonNull
    private String integratedAddress;

    /**
     * The payment id attached to the integrated address. Should not be shown to users.
     */
    @NonNull
    private String paymentId;
}
