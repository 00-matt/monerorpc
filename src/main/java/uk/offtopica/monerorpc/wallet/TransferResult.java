package uk.offtopica.monerorpc.wallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Represents the result of a transaction.
 *
 * @see MoneroWalletRpcClient#transfer
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TransferResult {
    @NonNull
    private byte[] hash;

    private byte[] key;

    @NonNull
    private Long amount;

    @NonNull
    private Long fee;
}
