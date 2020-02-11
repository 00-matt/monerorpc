package uk.offtopica.monerorpc.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
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

class TransferSplit {
    static class Request extends MoneroRpcRequest<Request.Params> {
        Request(List<TransferDestination> destinations) {
            super("transfer_split");
            setParams(new Params(destinations));
        }

        @Data
        static class Params implements MoneroRpcRequestParams {
            @NonNull
            private List<TransferDestination> destinations;

            // TODO: Add optional account_index, subaddr_indicies, unlock_time, get_tx_keys, priority, do_not_relay,
            //  get_tx_hex, and get_tx_metadata parameters.
        }
    }

    static class Response extends MoneroRpcResponse<Response.Result> {
        static final TypeReference<MoneroRpcResponse<Result>> TYPE_REFERENCE =
                new TypeReference<>() {
                };

        @Data
        static class Result implements MoneroRpcResponseResult {
            @JsonProperty("tx_hash_list")
            private List<String> hashes;

            @JsonProperty("tx_key_list")
            private List<String> keys;

            @JsonProperty("amount_list")
            private List<Long> amounts;

            @JsonProperty("fee_list")
            private List<Long> fees;

            public List<TransferResult> asTransferResultList() {
                final int size = hashes.size();
                if ((keys != null && keys.size() != size) || amounts.size() != size || fees.size() != size) {
                    throw new RuntimeException("Mis-matched list lengths");
                }

                final List<TransferResult> transferResultList = new ArrayList<>(size);

                try {
                    for (int i = 0; i < size; i++) {
                        transferResultList.add(new TransferResult(
                                HexUtils.hexStringToByteArray(hashes.get(i)),
                                keys == null ? null : HexUtils.hexStringToByteArray(keys.get(i)),
                                amounts.get(i),
                                fees.get(i))
                        );
                    }
                } catch (InvalidHexStringException e) {
                    throw new RuntimeException(e);
                }

                return transferResultList;
            }
        }
    }
}
