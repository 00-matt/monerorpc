package uk.offtopica.monerorpc.daemon;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;
import uk.offtopica.monerorpc.MoneroRpcRequest;
import uk.offtopica.monerorpc.MoneroRpcRequestParamsEmpty;
import uk.offtopica.monerorpc.MoneroRpcResponse;
import uk.offtopica.monerorpc.MoneroRpcResponseResult;

class GetBlockCount {
    static class Request extends MoneroRpcRequest<Request.Params> {
        Request() {
            super("get_block_count");
        }

        static class Params extends MoneroRpcRequestParamsEmpty {
        }
    }

    static class Response extends MoneroRpcResponse<Response.Result> {
        static final TypeReference<MoneroRpcResponse<Response.Result>> TYPE_REFERENCE =
                new TypeReference<>() {
                };

        @Data
        static class Result implements MoneroRpcResponseResult {
            private Long count;
        }
    }
}
