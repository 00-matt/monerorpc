package uk.offtopica.monerorpc.wallet;

import lombok.Data;
import lombok.NonNull;

/**
 * Represents the result of a transaction.
 *
 * @see MoneroWalletRpcClient#transfer
 */
@Data
public class TransferResult {
    @NonNull
    private byte[] hash;

    @NonNull
    private byte[] key;

    @NonNull
    private Long amount;

    @NonNull
    private Long fee;
}
