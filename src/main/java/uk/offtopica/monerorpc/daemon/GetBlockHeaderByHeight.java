package uk.offtopica.monerorpc.daemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;
import uk.offtopica.monerorpc.MoneroRpcRequest;
import uk.offtopica.monerorpc.MoneroRpcRequestParams;
import uk.offtopica.monerorpc.MoneroRpcResponse;
import uk.offtopica.monerorpc.MoneroRpcResponseResult;
import uk.offtopica.monerorpc.utils.InvalidHexStringException;

import java.math.BigInteger;

import static uk.offtopica.monerorpc.utils.HexUtils.hexStringToByteArray;

class GetBlockHeaderByHeight {
    static class Request extends MoneroRpcRequest<Request.Params> {
        Request(long height) {
            super("get_block_header_by_height");
            setParams(new Params(height));
        }

        @Data
        static class Params implements MoneroRpcRequestParams {
            private final long height;
        }
    }

    static class Response extends MoneroRpcResponse<Response.Result> {
        static final TypeReference<MoneroRpcResponse<Result>> TYPE_REFERENCE =
                new TypeReference<>() {
                };

        @Data
        static class Result implements MoneroRpcResponseResult {
            @JsonProperty("block_header")
            private Inner inner;

            public BlockHeader asBlockHeader() {
                try {
                    return new BlockHeader(
                            inner.blockSize,
                            inner.blockWeight,
                            inner.longTermWeight,
                            inner.depth,
                            new BigInteger(inner.wideDifficulty.substring(2), 16),
                            new BigInteger(inner.wideCumDifficulty.substring(2), 16),
                            hexStringToByteArray(inner.hash),
                            inner.height,
                            inner.majorVersion,
                            inner.minorVersion,
                            inner.nonce,
                            inner.numTxes,
                            inner.orphan,
                            hexStringToByteArray(inner.prevHash),
                            hexStringToByteArray(inner.minerTxHash),
                            inner.reward,
                            inner.timestamp
                    );
                } catch (InvalidHexStringException e) {
                    throw new RuntimeException(e);
                }
            }

            @Data
            static class Inner {
                @JsonProperty("block_size")
                private int blockSize;

                @JsonProperty("block_weight")
                private int blockWeight;

                @JsonProperty("long_term_weight")
                private int longTermWeight;

                private int depth;

                @JsonProperty("wide_cumulative_difficulty")
                private String wideCumDifficulty;

                @JsonProperty("wide_difficulty")
                private String wideDifficulty;

                private String hash;

                private long height;

                @JsonProperty("major_version")
                private int majorVersion;

                @JsonProperty("minor_version")
                private int minorVersion;

                private long nonce;

                @JsonProperty("num_txes")
                private int numTxes;

                private boolean orphan;

                @JsonProperty("prev_hash")
                private String prevHash;

                @JsonProperty("miner_tx_hash")
                private String minerTxHash;

                private long reward;

                private long timestamp;
            }
        }
    }
}
