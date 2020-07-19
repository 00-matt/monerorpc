package uk.offtopica.monerorpc.wallet;

import lombok.NonNull;
import uk.offtopica.monerorpc.MoneroRpcClient;

import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Represents an RPC connection to a Monero wallet server.
 */
public class MoneroWalletRpcClient extends MoneroRpcClient {
    /**
     * Create a new RPC connection to a Monero wallet server.
     *
     * @param address The address of the json_rpc endpoint, for example:
     *                <code>http://localhost:18082/json_rpc</code>
     */
    public MoneroWalletRpcClient(@NonNull URI address) {
        super(address);
    }

    /**
     * Create a new wallet address.
     *
     * @param accountIdx The account to create the address in. Use 0 if you are unsure
     * @param label      Optional label for new address. May be null
     * @return A future that, when complete, returns the wallet address and its indices.
     */
    public CompletableFuture<IndexedAddress> createAddress(int accountIdx, String label) {
        if (accountIdx < 0) {
            throw new IllegalArgumentException("accountIdx must be positive");
        }
        return request(new CreateAddress.Request(accountIdx, label), CreateAddress.Response.TYPE_REFERENCE)
                .thenApply(response -> {
                    var result = response.getResult();
                    return new IndexedAddress(accountIdx, result.getAddressIndex(), result.getAddress());
                });
    }

    /**
     * Send XMR to one or more recipients.
     * <p>
     * Note that this calls the server's <code>transfer_split</code> method instead of just <code>transfer</code>.
     *
     * @param destinations A list of recipient-amount pairs
     * @return A future, that when complete, returns a list of transfer results. If there are few recipients, there
     * will probably only be one item in this list. May fail exceptionally if the call fails, for example if the
     * wallet is not ready, if a wallet address is invalid, or if the wallet's unlocked balance is not high enough.
     */
    public CompletableFuture<List<TransferResult>> transfer(List<TransferDestination> destinations) {
        return request(new TransferSplit.Request(destinations), TransferSplit.Response.TYPE_REFERENCE)
                .thenApply(response -> response.getResult().asTransferResultList());
    }
}
