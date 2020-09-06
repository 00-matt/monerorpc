package uk.offtopica.monerorpc.daemon;

import uk.offtopica.monerorpc.utils.InvalidHexStringException;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static uk.offtopica.monerorpc.utils.HexUtils.hexStringToByteArray;

class DaemonTestConstants {
    static final BlockHeader BLOCK_HEADER_1;
    static final Map<String, Object> BLOCK_HEADER_1_JSON;
    static final byte[] BLOCK_HEADER_1_HASH;
    static final long BLOCK_HEADER_1_HEIGHT = 912345;

    static {
        // Need to do this because Map.of doesn't have enough parameters to create a block header.
        BLOCK_HEADER_1_JSON = new HashMap<>();
        BLOCK_HEADER_1_JSON.put("block_size", 210);
        BLOCK_HEADER_1_JSON.put("block_weight", 210);
        BLOCK_HEADER_1_JSON.put("cumulative_difficulty", 754734824984346L);
        BLOCK_HEADER_1_JSON.put("cumulative_difficulty_top64", 0);
        BLOCK_HEADER_1_JSON.put("depth", 1267602);
        BLOCK_HEADER_1_JSON.put("difficulty", 815625611);
        BLOCK_HEADER_1_JSON.put("difficulty_top64", 0);
        BLOCK_HEADER_1_JSON.put("hash", "e22cf75f39ae720e8b71b3d120a5ac03f0db50bba6379e2850975b4859190bc6");
        BLOCK_HEADER_1_JSON.put("height", 912345);
        BLOCK_HEADER_1_JSON.put("long_term_weight", 210);
        BLOCK_HEADER_1_JSON.put("major_version", 1);
        BLOCK_HEADER_1_JSON.put("miner_tx_hash", "c7da3965f25c19b8eb7dd8db48dcd4e7c885e2491db77e289f0609bf8e08ec30");
        BLOCK_HEADER_1_JSON.put("minor_version", 2);
        BLOCK_HEADER_1_JSON.put("nonce", 1646);
        BLOCK_HEADER_1_JSON.put("num_txes", 0);
        BLOCK_HEADER_1_JSON.put("orphan_status", false);
        BLOCK_HEADER_1_JSON.put("pow_hash", "");
        BLOCK_HEADER_1_JSON.put("prev_hash", "b61c58b2e0be53fad5ef9d9731a55e8a81d972b8d90ed07c04fd37ca6403ff78");
        BLOCK_HEADER_1_JSON.put("reward", 7388968946286L);
        BLOCK_HEADER_1_JSON.put("timestamp", 1452793716);
        BLOCK_HEADER_1_JSON.put("wide_cumulative_difficulty", "0x2ae6d65248f1a");
        BLOCK_HEADER_1_JSON.put("wide_difficulty", "0x309d758b");

        try {
            BLOCK_HEADER_1_HASH = hexStringToByteArray("e22cf75f39ae720e8b71b3d120a5ac03f0db50bba6379e2850975b4859190bc6");

            BLOCK_HEADER_1 = new BlockHeader(
                    210,
                    210,
                    210,
                    1267602,
                    BigInteger.valueOf(815625611),
                    BigInteger.valueOf(754734824984346L),
                    hexStringToByteArray("e22cf75f39ae720e8b71b3d120a5ac03f0db50bba6379e2850975b4859190bc6"),
                    912345,
                    1,
                    2,
                    1646,
                    0,
                    false,
                    hexStringToByteArray("b61c58b2e0be53fad5ef9d9731a55e8a81d972b8d90ed07c04fd37ca6403ff78"),
                    hexStringToByteArray("c7da3965f25c19b8eb7dd8db48dcd4e7c885e2491db77e289f0609bf8e08ec30"),
                    7388968946286L,
                    1452793716
            );
        } catch (InvalidHexStringException e) {
            throw new RuntimeException(e);
        }
    }
}
