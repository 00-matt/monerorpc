package uk.offtopica.monerorpc.wallet;

import org.junit.jupiter.api.Test;
import uk.offtopica.monerorpc.MoneroRpcClientTest;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static uk.offtopica.monerorpc.utils.HexUtils.hexStringToByteArray;

class MoneroWalletRpcClientTest extends MoneroRpcClientTest<MoneroWalletRpcClient> {
    @Test
    void testTransferSuccessOneResult() throws Exception {
        http.onPost(uri.toString())
                .doReturnJSON("{\n" +
                        "  \"id\": \"0\",\n" +
                        "  \"jsonrpc\": \"2.0\",\n" +
                        "  \"result\": {\n" +
                        "    \"amount_list\": [3000000000000],\n" +
                        "    \"fee_list\": [473710000],\n" +
                        "    \"multisig_txset\": \"\",\n" +
                        "    \"tx_hash_list\": [\"4adcdc1af3f665770cdf8fb7a380887cd07ac53c2b771bd18df5ca375d5e7540" +
                        "\"],\n" +
                        "    \"tx_key_list\": " +
                        "[\"5b455c0f97168be652a2c03c5c68a064bb84cdae4ddef01b5c48d73a0bbb27075fb714f2ca19ea6c8ff592" +
                        "417e606addea6deb1d6530e2969f75681ffcbfc4075677b94a8c9197963ae38fa6f543ee68f0a4c4bbda4c453" +
                        "f39538f00b28e980ea08509730b51c004960101ba2f3adbc34cbbdff0d5af9dba061b523090debd06\"],\n" +
                        "    \"unsigned_txset\": \"\"\n" +
                        "  }\n" +
                        "}");

        assertEquals(List.of(new TransferResult(
                hexStringToByteArray("4adcdc1af3f665770cdf8fb7a380887cd07ac53c2b771bd18df5ca375d5e7540"),
                hexStringToByteArray("5b455c0f97168be652a2c03c5c68a064bb84cdae4ddef01b5c48d73a0bbb27075fb714f2ca19ea6" +
                        "c8ff592417e606addea6deb1d6530e2969f75681ffcbfc4075677b94a8c9197963ae38fa6f543ee68f0a4c4bbda4" +
                        "c453f39538f00b28e980ea08509730b51c004960101ba2f3adbc34cbbdff0d5af9dba061b523090debd06"),
                3000000000000L,
                473710000L
        )), client.transfer(List.of(
                new TransferDestination(
                        "7BnERTpvL5MbCLtj5n9No7J5oE5hHiB3tVCK5cjSvCsYWD2WRJLFuWeKTLiXo5QJqt2ZwUaLy2Vh1Ad51K7FNgqcHgjW85o",
                        1000000000000L),
                new TransferDestination(
                        "75sNpRwUtekcJGejMuLSGA71QFuK1qcCVLZnYRTfQLgFU5nJ7xiAHtR5ihioS53KMe8pBhH61moraZHyLoG4G7fMER8xkNv",
                        2000000000000L
                ))).get());
    }

    @Override
    protected MoneroWalletRpcClient createClient(URI uri) {
        return new MoneroWalletRpcClient(uri);
    }
}
