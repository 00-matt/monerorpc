package uk.offtopica.monerorpc.daemon;

import uk.offtopica.monerorpc.MoneroRpcClient;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

public class MoneroDaemonRpcClient extends MoneroRpcClient {
    public MoneroDaemonRpcClient(URI address) {
        super(address);
    }

    public CompletableFuture<Long> getBlockCount() {
        return request(new GetBlockCount.Request(), GetBlockCount.Response.TYPE_REFERENCE)
                .thenApply(response -> response.getResult().getCount());
    }

    public CompletableFuture<BlockTemplate> getBlockTemplate(String walletAddress, int reserveSize) {
        return request(new GetBlockTemplate.Request(walletAddress, reserveSize),
                GetBlockTemplate.Response.TYPE_REFERENCE).thenApply(response -> response.getResult().asBlockTemplate());
    }

    public CompletableFuture<Void> submitBlock(byte[] blob)  {
        return request(new SubmitBlock.Request(blob), SubmitBlock.Response.TYPE_REFERENCE)
                .thenApply(response -> null);
    }
}
