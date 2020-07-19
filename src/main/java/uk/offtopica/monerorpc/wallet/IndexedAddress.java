package uk.offtopica.monerorpc.wallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

/**
 * Represents an address at a certain index in the wallet.
 *
 * @see MoneroWalletRpcClient#createAddress(int, String)
 */
@Data
@AllArgsConstructor
public class IndexedAddress {
    /**
     * The index of the account in the wallet. 0 is the first account.
     */
    private int accountIdx;

    /**
     * The index of the address in the account.
     */
    private int addressIdx;

    /**
     * base58 representation of the wallet address.
     */
    @NonNull
    private String address;
}
