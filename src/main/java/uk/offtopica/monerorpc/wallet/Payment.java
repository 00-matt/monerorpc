package uk.offtopica.monerorpc.wallet;

import lombok.Data;
import lombok.NonNull;

/**
 * Represents an incoming payment.
 *
 * @see MoneroWalletRpcClient#getPayments(String)
 */
@Data
public class Payment {
    @NonNull
    private IndexedAddress recipient;

    @NonNull
    private Long amount;

    @NonNull
    private Long blockHeight;

    @NonNull
    private byte[] txHash;

    @NonNull
    private Long unlockTime;
}
