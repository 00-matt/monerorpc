package uk.offtopica.monerorpc.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import uk.offtopica.monerorpc.MoneroRpcRequest;
import uk.offtopica.monerorpc.MoneroRpcRequestParams;
import uk.offtopica.monerorpc.MoneroRpcResponse;
import uk.offtopica.monerorpc.MoneroRpcResponseResult;

class CreateAddress {
    static class Request extends MoneroRpcRequest<Request.Params> {
        Request(int accountIndex, String label) {
            super("create_address");
            setParams(new Params(accountIndex, label));
        }

        @Data
        @AllArgsConstructor
        static class Params implements MoneroRpcRequestParams {
            @NonNull
            @JsonProperty("account_index")
            private Integer accountIndex;

            private String label;

            // TODO: Count parameter.
        }
    }

    static class Response extends MoneroRpcResponse<Response.Result> {
        static final TypeReference<MoneroRpcResponse<Result>> TYPE_REFERENCE =
                new TypeReference<>() {
                };

        @Data
        static class Result implements MoneroRpcResponseResult {
            private String address;

            @JsonProperty("address_index")
            private Integer addressIndex;

            // TODO: addresses and address_indices
        }
    }
}
