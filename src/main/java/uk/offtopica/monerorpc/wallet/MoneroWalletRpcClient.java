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
     * Create an integrated address with this wallet's primary address and a random payment id.
     *
     * @return A future that, when complete, returns the integrated address and payment id.
     * @see #createIntegratedAddress(String, String)
     */
    public CompletableFuture<IntegratedAddressResult> createIntegratedAddress() {
        return createIntegratedAddress(null, null);
    }

    /**
     * Join a wallet address and a short payment id to get an integrated address
     *
     * @param standardAddress The base wallet address to use. If {@code null}, defaults to the current wallet's primary
     *                        address
     * @param paymentId       The payment id to use. Should be {@code null} or 16 hex characters. Defaults to random.
     * @return A future that, when complete, returns the integrated address and payment id.
     */
    public CompletableFuture<IntegratedAddressResult> createIntegratedAddress(String standardAddress,
                                                                              String paymentId) {
        return request(new CreateIntegratedAddress.Request(standardAddress, paymentId),
                CreateIntegratedAddress.Response.TYPE_REFERENCE)
                .thenApply(response -> {
                    var result = response.getResult();
                    return new IntegratedAddressResult(result.getIntegratedAddress(), result.getPaymentId());
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
