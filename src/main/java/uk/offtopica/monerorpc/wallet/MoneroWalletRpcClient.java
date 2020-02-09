package uk.offtopica.monerorpc.wallet;

import lombok.NonNull;
import uk.offtopica.monerorpc.MoneroRpcClient;

import java.net.URI;

public class MoneroWalletRpcClient extends MoneroRpcClient {
    public MoneroWalletRpcClient(@NonNull URI address) {
        super(address);
    }
}
