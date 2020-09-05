package uk.offtopica.monerorpc.daemon;

import org.junit.jupiter.api.Test;
import uk.offtopica.monerorpc.MoneroRpcClientTest;

import java.math.BigInteger;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static uk.offtopica.monerorpc.utils.HexUtils.hexStringToByteArray;

class MoneroDaemonRpcClientTest extends MoneroRpcClientTest<MoneroDaemonRpcClient> {
    @Test
    void testGetBlockCount() throws Exception {
        setupMockEndpointSuccess(
                "get_block_count",
                Map.of(),
                Map.of(
                        "count", 2030003,
                        "status", "OK",
                        "untrusted", false
                ));

        assertEquals(2030003, client.getBlockCount().get());
    }

    @Test
    void testGetBlockHeaderByHeightSuccess() throws Exception {
        // Need to do this because Map.of doesn't have enough parameters to create a block header.
        final var blockHeader = new HashMap<String, Object>();
        blockHeader.put("block_size", 210);
        blockHeader.put("block_weight", 210);
        blockHeader.put("cumulative_difficulty", 754734824984346L);
        blockHeader.put("cumulative_difficulty_top64", 0);
        blockHeader.put("depth", 1267602);
        blockHeader.put("difficulty", 815625611);
        blockHeader.put("difficulty_top64", 0);
        blockHeader.put("hash", "e22cf75f39ae720e8b71b3d120a5ac03f0db50bba6379e2850975b4859190bc6");
        blockHeader.put("height", 912345);
        blockHeader.put("long_term_weight", 210);
        blockHeader.put("major_version", 1);
        blockHeader.put("miner_tx_hash", "c7da3965f25c19b8eb7dd8db48dcd4e7c885e2491db77e289f0609bf8e08ec30");
        blockHeader.put("minor_version", 2);
        blockHeader.put("nonce", 1646);
        blockHeader.put("num_txes", 0);
        blockHeader.put("orphan_status", false);
        blockHeader.put("pow_hash", "");
        blockHeader.put("prev_hash", "b61c58b2e0be53fad5ef9d9731a55e8a81d972b8d90ed07c04fd37ca6403ff78");
        blockHeader.put("reward", 7388968946286L);
        blockHeader.put("timestamp", 1452793716);
        blockHeader.put("wide_cumulative_difficulty", "0x2ae6d65248f1a");
        blockHeader.put("wide_difficulty", "0x309d758b");

        setupMockEndpointSuccess(
                "get_block_header_by_height",
                Map.of("height", 912345),
                Map.of(
                        "block_header",
                        blockHeader
                )
        );

        assertEquals(new BlockHeader(
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
        ), client.getBlockHeader(912345).get());
    }

    @Test
    void testGetBlockTemplate() throws Exception {
        // Need to do this because Map.of doesn't have enough parameters to create a blocktemplate.
        final var result = new HashMap<String, Object>();
        result.put(
                "blockhashing_blob",
                "0c0cddab81f205bacdd6327952f2e254aac94686cef0698e17bbfc209c7ac5705c0467a55adcf700000000288946" +
                        "7e48b6599400ae51c718e43464b10e2982c863f4da2b72fecfe63fc02925"
        );
        result.put(
                "blocktemplate_blob",
                "0c0cddab81f205bacdd6327952f2e254aac94686cef0698e17bbfc209c7ac5705c0467a55adcf70000000002f0f3" +
                        "7b01ffb4f37b01abd2c59e8f3a02e4768d7c2fb6b61b60b9fa9a97c495e90337cb8ea1293bc2261e1f61" +
                        "bfd5c1513701e92fbdc28e6c9a20f78cb5aaf5bec1c311e915e5d7ee6aac643adb8d8840b4d902140000" +
                        "000000000000000000000000000000000000002436f51e815827bb26876bcc7c777dcef6077049ba1e07" +
                        "b8f069fa75e0a3f862e7c977e3b678e44e9171d99afd94f159811d07e340b293602a1f0167589157aa47" +
                        "fc561273eae26678ca7fd58f5e2aa21abc64bf6ac01329c2db07244e7b7472cc2052c7b36c4d2268a2f3" +
                        "d3048a2a8b1a7e3449e22ca3ef051891b2cb67f5b48c4ff9755d41a9073ba6ed7bda9e7b315206fb4317" +
                        "79e8fdff610ceff23ba6a4dd7e3f5a15ef60aae0500b703d34c18c456e4b217036bedbab7de34d5c268b" +
                        "6d4f89a8f883ac846e1ea5abf8e0d2aa50cc919fceaf579a9cc6b4ab8f7f71c0d0d480a12cd10ed1c018" +
                        "f833ce20388c9623bb4a4bc59c0f944eebb59301006f339b087065258ca64a506ea9d19238d239fd5595" +
                        "a94ddf1d33166976db9686fb20936c8a23ba3a30c2d8c22412ea2e27c15eb09f2f82fe6ceca22fc6abb7" +
                        "e59797020a9f2ea8a2be44e2d2f74e3ea32433fa2698aed232408dafa1e43f9a7376b2e2e75779319c15" +
                        "ccb9acdd2d1ddb8d86e171072cd9c4f8115653f91d974fd596c9aed215cbdc22eec56e0e88a44a28d14a" +
                        "4f059545b14ade905ee051961ce2a99284897178942bda45cac18eb6e4de10f524949312dfd22a384b63" +
                        "0221f2444a83e175a76133bb83120a020c4389f41d79fd47d48b18546ec6acc3bc065ce8ac91e4d46cf5" +
                        "b427a12847338c5503d6b465141becb97bfd14fd4470419b6c994df65f9258c80b9177e0eb5df44ae485" +
                        "bcf9f3cdb1c7f44cc9e84cef2e76b88831c2d10423eb99c58e6eddef16401e0b521e0334b1c529b494e9" +
                        "a242af4a8563e654490a2f808ead168cc709cbdf36085227353fe12dc0d804502f895bd65f376539ecb2" +
                        "c925d0fa47d619eb686970828fa1c3b3854483583381bae684514747bd633150c85545b49b8cda0144c6" +
                        "5fce813e3472ca90af93bb55fcbe45897ce5c2ac049401f729d70be6c7cd130d866b4582737888221569" +
                        "b3ab358129842a5d68dbe3b80247409bdce698d53003d5978f75663cf68fb5f6eb43e9cfa0384852c45b" +
                        "957db171ecfc88feb21546bb138b106fdaadfa0b0a96307deabfc60cbbbb01414beea5186a86e79a19ca" +
                        "ebdd30ec41c00faef24c94bf91c0272bbe37efd86e25bef8a244f84165a6516d1922d54cc7f8f0e54fc4" +
                        "4f3d139edc47646883ed870c6c52d747feb21998447c547695b021db3fd92e26a3d328cf14e0de5d0ca6" +
                        "d7655910bebfa157dbcf48a460040b4f79e186a3cd0963bdd3dee4143e90116b942405025ed5b99d035d" +
                        "125b6b09cfe2b95446fa4528f61bf28341a0864c84dbed266a3d8bce2f05e2d974c62689aef5d6a3986f" +
                        "3defd1d929eaff0e962ee288b268c3e9e33c3c2f8c1710be1ae84efaabf26c970c39274d7011e2a94066" +
                        "9992b5c7795b337a80d12d1aeca1b5d5a9172aa3b0ea5513d0cd4d2b883f8c31f983adad6dfdcd965c05" +
                        "cc00b38a57332f38693524fd1de44cfeefadde52acefd16be33a61e3d72fc7cc9c9e765e670f7d4a3d4b" +
                        "e4170b13ed4d116e44b215a197cb9410dda5935c5585eed22b7a8bef7f37f29db901e0def96459c0532a" +
                        "2a1f05458d6913afb33de552aeee12a715d219d0cc2148396ef1d818d3cb494eeb60b15b95cf"
        );
        result.put("difficulty", 157657627099L);
        result.put("difficulty_top64", 0);
        result.put("expected_reward", 1996955412779L);
        result.put("height", "2030004");
        result.put("next_seed_hash", "");
        result.put("prev_hash", "bacdd6327952f2e254aac94686cef0698e17bbfc209c7ac5705c0467a55adcf7");
        result.put("reserved_offset", 128);
        result.put("seed_hash", "b3e1f696b10bdcd9f936f46306774de0da2b323d05a578b898d684f3500a685a");
        result.put("seed_height", "2029568");
        result.put("status", "OK");
        result.put("untrusted", false);
        result.put("wide_difficulty", "0x24b52079db");

        setupMockEndpointSuccess(
                "get_block_template",
                Map.of(
                        "wallet_address",
                        "44GBHzv6ZyQdJkjqZje6KLZ3xSyN1hBSFAnLP6EAqJtCRVzMzZmeXTC2AHKDS9aEDTRKmo6a6o9r9j86pYfhCWDkKjbtcns",
                        "reserve_size",
                        20
                ),
                result
        );

        final BlockTemplate blockTemplate = client.getBlockTemplate(
                "44GBHzv6ZyQdJkjqZje6KLZ3xSyN1hBSFAnLP6EAqJtCRVzMzZmeXTC2AHKDS9aEDTRKmo6a6o9r9j86pYfhCWDkKjbtcns",
                20).get();

        assertEquals(76, blockTemplate.getBlockHashingBlob().length);
        assertEquals(1302, blockTemplate.getBlockTemplateBlob().length);
        assertEquals(BigInteger.valueOf(157657627099L), blockTemplate.getDifficulty());
        assertEquals(1996955412779L, blockTemplate.getExpectedReward());
        assertEquals(2030004L, blockTemplate.getHeight());
        assertNull(blockTemplate.getNextSeedHash());
        assertEquals(32, blockTemplate.getPrevHash().length);
        assertEquals(128, blockTemplate.getReservedOffset());
        assertEquals(32, blockTemplate.getSeedHash().length);
        assertEquals(2029568L, blockTemplate.getSeedHeight());
    }

    @Test
    void testSubmitInvalid() {
        setupMockEndpointError(
                "submit_block",
                List.of("deadbeef"),
                -7,
                "Block not accepted");

        assertThrows(ExecutionException.class, () -> client.submitBlock(hexStringToByteArray("deadbeef")).get());
    }

    @Test
    void testSubmitValid() {
        setupMockEndpointSuccess(
                "submit_block",
                List.of("deadbeef"),
                Map.of("status", "OK"));

        // shouldn't throw
        assertDoesNotThrow(() -> client.submitBlock(hexStringToByteArray("deadbeef")).get());
    }

    @Override
    protected MoneroDaemonRpcClient createClient(URI uri) {
        return new MoneroDaemonRpcClient(uri);
    }
}
