package uk.offtopica.monerorpc;

import lombok.Data;
import lombok.NonNull;

@Data
public class MoneroRpcRequest<T extends MoneroRpcRequestParams> {
    // Also contains "id" and "jsonrpc" fields but we can be cheeky and leave
    // these out.

    @NonNull
    private String method;
    private T params;
}
