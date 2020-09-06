package uk.offtopica.monerorpc.daemon;

import lombok.Data;
import uk.offtopica.monerorpc.MoneroRpcRequest;
import uk.offtopica.monerorpc.MoneroRpcRequestParams;

class GetBlockHeaderByHash {
    static class Request extends MoneroRpcRequest<Request.Params> {
        Request(String hash) {
            super("get_block_header_by_hash");
            setParams(new Params(hash));
        }

        @Data
        static class Params implements MoneroRpcRequestParams {
            private final String hash;
        }
    }

    // Use GetBlockHeaderByHeight's response.
}
