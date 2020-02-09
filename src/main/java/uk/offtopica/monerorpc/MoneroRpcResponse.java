package uk.offtopica.monerorpc;

import lombok.Data;

@Data
public class MoneroRpcResponse<T extends MoneroRpcResponseResult> {
    // Also contains "id" and "jsonrpc" fields. These do not matter, the id
    // always matches whatever was sent and "jsonrpc" is always "2.0".

    private T result;
    private MoneroRpcError error;
}
