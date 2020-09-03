package uk.offtopica.monerorpc.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import uk.offtopica.monerorpc.MoneroRpcRequest;
import uk.offtopica.monerorpc.MoneroRpcRequestParams;
import uk.offtopica.monerorpc.MoneroRpcResponse;
import uk.offtopica.monerorpc.MoneroRpcResponseResult;

class CreateIntegratedAddress {
    static class Request extends MoneroRpcRequest<Request.Params> {
        Request(String standardAddress, String paymentId) {
            super("make_integrated_address");
            setParams(new Params(standardAddress, paymentId));
        }

        @Data
        @AllArgsConstructor
        static class Params implements MoneroRpcRequestParams {
            @JsonProperty("standard_ddress")
            private String standardAddress;

            @JsonProperty("payment_id")
            private String paymentId;
        }
    }

    static class Response extends MoneroRpcResponse<Response.Result> {
        static final TypeReference<MoneroRpcResponse<Result>> TYPE_REFERENCE =
                new TypeReference<>() {
                };

        @Data
        static class Result implements MoneroRpcResponseResult {
            @JsonProperty("integrated_address")
            private String integratedAddress;

            @JsonProperty("payment_id")
            private String paymentId;
        }
    }
}
