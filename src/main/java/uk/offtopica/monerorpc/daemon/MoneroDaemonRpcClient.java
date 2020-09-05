package uk.offtopica.monerorpc.daemon;

import uk.offtopica.monerorpc.MoneroRpcClient;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

/**
 * Represents an RPC connection to a Monero daemon.
 */
public class MoneroDaemonRpcClient extends MoneroRpcClient {
    /**
     * Create a new RPC connection to a Monero daemon.
     *
     * @param address The address of the json_rpc endpoint, for example:
     *                <code>http://localhost:18081/json_rpc</code>
     */
    public MoneroDaemonRpcClient(URI address) {
        super(address);
    }

    /**
     * Look up how many blocks are in the longest chain known to the daemon.
     *
     * @return A future, that when complete, returns the number of blocks in the longest chain known to the daemon.
     * May fail exceptionally if the call fails, for example if the daemon is too busy to handle the request.
     */
    public CompletableFuture<Long> getBlockCount() {
        return request(new GetBlockCount.Request(), GetBlockCount.Response.TYPE_REFERENCE)
                .thenApply(response -> response.getResult().getCount());
    }

    /**
     * Get a block header by its height.
     *
     * @param height Height of block.
     * @return A future, that when complete, returns some basic information about a block.
     */
    public CompletableFuture<BlockHeader> getBlockHeader(long height) {
        return request(new GetBlockHeaderByHeight.Request(height), GetBlockHeaderByHeight.Response.TYPE_REFERENCE)
                .thenApply(response -> response.getResult().asBlockHeader());
    }

    /**
     * Create a new block template for mining.
     *
     * @param walletAddress The address to receive the coinbase reward.
     * @param reserveSize   Number of bytes to reserve in the block for e.g. a pool and miner specific nonce.
     * @return A future, that when complete, returns a block template. May fail exceptionally if the call fails, for
     * example if the daemon is too busy or if the wallet address is invalid.
     * @see BlockTemplate
     */
    public CompletableFuture<BlockTemplate> getBlockTemplate(String walletAddress, int reserveSize) {
        return request(new GetBlockTemplate.Request(walletAddress, reserveSize),
                GetBlockTemplate.Response.TYPE_REFERENCE).thenApply(response -> response.getResult().asBlockTemplate());
    }

    /**
     * Submit a new block to be broadcasted to the network.
     *
     * @param blob A filled in block template blob.
     * @return A future, that when complete, returns nothing. May fail exceptionally if the daemon is too busy or if
     * the blob was invalid due to it being low difficulty or malformed.
     */
    public CompletableFuture<Void> submitBlock(byte[] blob) {
        return request(new SubmitBlock.Request(blob), SubmitBlock.Response.TYPE_REFERENCE)
                .thenApply(response -> null);
    }
}
