package uk.offtopica.monerorpc.daemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;
import lombok.NonNull;
import uk.offtopica.monerorpc.MoneroRpcRequest;
import uk.offtopica.monerorpc.MoneroRpcRequestParams;
import uk.offtopica.monerorpc.MoneroRpcResponse;
import uk.offtopica.monerorpc.MoneroRpcResponseResult;
import uk.offtopica.monerorpc.utils.InvalidHexStringException;

import java.math.BigInteger;

import static uk.offtopica.monerorpc.utils.HexUtils.hexStringToByteArray;

class GetBlockTemplate {
    static class Request extends MoneroRpcRequest<Request.Params> {
        Request(String walletAddress, int reserveSize) {
            super("get_block_template");
            setParams(new Params(walletAddress, reserveSize));
        }

        @Data
        static class Params implements MoneroRpcRequestParams {
            @JsonProperty("wallet_address")
            @NonNull
            private String walletAddress;

            @JsonProperty("reserve_size")
            @NonNull
            private Integer reserveSize;
        }
    }

    static class Response extends MoneroRpcResponse<Response.Result> {
        static final TypeReference<MoneroRpcResponse<Result>> TYPE_REFERENCE =
                new TypeReference<>() {
                };

        @Data
        static class Result implements MoneroRpcResponseResult {
            @JsonProperty("blocktemplate_blob")
            private String blockTemplate;

            @JsonProperty("blockhashing_blob")
            private String blockHashing;

            private Long difficulty;

            @JsonProperty("difficulty_top64")
            private Long difficultyTop64;

            @JsonProperty("expected_reward")
            private Long expectedReward;

            private Long height;

            @JsonProperty("prev_hash")
            private String prevHash;

            @JsonProperty("reserved_offset")
            private Integer reservedOffset;

            @JsonProperty("next_seed_hash")
            private String nextSeedHash;

            @JsonProperty("seed_hash")
            private String seedHash;

            @JsonProperty("seed_height")
            private Long seedHeight;

            @JsonProperty("wide_difficulty")
            private String wideDifficulty;

            public BlockTemplate asBlockTemplate() {
                try {
                    return new BlockTemplate(
                            hexStringToByteArray(blockTemplate),
                            hexStringToByteArray(blockHashing),
                            // TODO: Use difficultyTop64 or wide_difficulty.
                            BigInteger.valueOf(difficulty),
                            expectedReward,
                            height,
                            nextSeedHash.length() == 0 ? null : hexStringToByteArray(nextSeedHash),
                            hexStringToByteArray(prevHash),
                            reservedOffset,
                            hexStringToByteArray(seedHash),
                            seedHeight
                    );
                } catch (InvalidHexStringException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
