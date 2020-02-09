package uk.offtopica.monerorpc.daemon;

import com.fasterxml.jackson.core.type.TypeReference;
import uk.offtopica.monerorpc.MoneroRpcRequest;
import uk.offtopica.monerorpc.MoneroRpcRequestParamsArray;
import uk.offtopica.monerorpc.MoneroRpcResponse;
import uk.offtopica.monerorpc.MoneroRpcResponseResultEmpty;
import uk.offtopica.monerorpc.utils.HexUtils;

class SubmitBlock {
    static class Request extends MoneroRpcRequest<MoneroRpcRequestParamsArray<String>> {
        Request(byte[] blob) {
            super("submit_block");
            setParams(MoneroRpcRequestParamsArray.of(HexUtils.byteArrayToHexString(blob)));
        }
    }

    static class Response extends MoneroRpcResponse<MoneroRpcResponseResultEmpty> {
        static final TypeReference<MoneroRpcResponse<MoneroRpcResponseResultEmpty>> TYPE_REFERENCE =
                new TypeReference<>() {
                };
    }
}
