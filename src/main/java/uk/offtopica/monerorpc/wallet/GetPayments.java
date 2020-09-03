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
import uk.offtopica.monerorpc.utils.HexUtils;
import uk.offtopica.monerorpc.utils.InvalidHexStringException;

import java.util.ArrayList;
import java.util.List;

class GetPayments {
    static class Request extends MoneroRpcRequest<Request.Params> {
        Request(String paymentId) {
            super("get_payments");
            setParams(new Params(paymentId));
        }

        @Data
        @AllArgsConstructor
        static class Params implements MoneroRpcRequestParams {
            @NonNull
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
            private List<Inner> payments;

            public List<Payment> asPaymentList() {
                final var list = new ArrayList<Payment>(payments.size());
                for (Inner inner : payments) {
                    list.add(inner.asPayment());
                }
                return list;
            }

            @Data
            static class Inner {
                private String address;

                private Long amount;

                @JsonProperty("block_height")
                private Long blockHeight;

                @JsonProperty("payment_id")
                private String paymentId;

                @JsonProperty("tx_hash")
                private String txHash;

                @JsonProperty("unlock_time")
                private Long unlockTime;

                @JsonProperty("subaddr_index")
                private SubaddrIndex subaddrIndex;

                public Payment asPayment() {
                    final var indexedAddress = new IndexedAddress(subaddrIndex.major, subaddrIndex.minor, address);
                    try {
                        return new Payment(indexedAddress,
                                amount,
                                blockHeight,
                                HexUtils.hexStringToByteArray(txHash),
                                unlockTime);
                    } catch (InvalidHexStringException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Data
                static class SubaddrIndex {
                    private int major;
                    private int minor;
                }
            }
        }
    }
}
