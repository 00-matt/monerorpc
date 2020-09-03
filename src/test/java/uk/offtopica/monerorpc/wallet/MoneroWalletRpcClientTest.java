package uk.offtopica.monerorpc.wallet;

import org.junit.jupiter.api.Test;
import uk.offtopica.monerorpc.MoneroRpcClientTest;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static uk.offtopica.monerorpc.utils.HexUtils.hexStringToByteArray;

class MoneroWalletRpcClientTest extends MoneroRpcClientTest<MoneroWalletRpcClient> {
    @Test
    void testCreateAddressSuccess() throws Exception {
        setupMockEndpointSuccess(
                "create_address",
                Map.of(
                        "account_index", 0
                ),
                Map.of(
                        "address_index", 5,
                        "address",
                        "7BG5jr9QS5sGMdpbBrZEwVLZjSKJGJBsXdZLt8wiXyhhLjy7x2LZxsrAnHTgD8oG46ZtLjUGic2pWc96GFkGNPQQDA3Dt7Q"
                )
        );

        assertEquals(new IndexedAddress(
                        0,
                        5,
                        "7BG5jr9QS5sGMdpbBrZEwVLZjSKJGJBsXdZLt8wiXyhhLjy7x2LZxsrAnHTgD8oG46ZtLjUGic2pWc96GFkGNPQQDA3Dt7Q"),
                client.createAddress(0, null).get()
        );
    }

    @Test
    void testCreateIntegratedAddressSuccess() throws Exception {
        setupMockEndpointSuccess(
                "make_integrated_address",
                Map.of(),
                Map.of("integrated_address",
                        "5F38Rw9HKeaLQGJSPtbYDacR7dz8RBFnsfAKMaMuwUNYX6aQbBcovzDPyrQF9KXF9tVU6Xk3K8no1BywnJX6Gv",
                        "payment_id",
                        "420fa29b2d9a49f5")
        );

        assertEquals(new IntegratedAddressResult(
                        "5F38Rw9HKeaLQGJSPtbYDacR7dz8RBFnsfAKMaMuwUNYX6aQbBcovzDPyrQF9KXF9tVU6Xk3K8no1BywnJX6Gv",
                        "420fa29b2d9a49f5"),
                client.createIntegratedAddress().get());
    }

    @Test
    void testGetPaymentsSuccess() throws Exception {
        setupMockEndpointSuccess(
                "get_payments",
                Map.of("payment_id", "60900e5603bf96e3"),
                Map.of("payments",
                        List.of(Map.of(
                                "address",
                                "55LTR8KniP4LQGJSPtbYDacR7dz8RBFnsfAKMaMuwUNYX6aQbBcovzDPyrQF9KXF9tVU6Xk3K8no1BywnJX6GvZX8yJsXvt",
                                "amount",
                                1000000000000L,
                                "block_height",
                                127606L,
                                "payment_id",
                                "60900e5603bf96e3",
                                "tx_hash",
                                "3292e83ad28fc1cc7bc26dbd38862308f4588680fbf93eae3e803cddd1bd614f",
                                "unlock_time",
                                0L,
                                "subaddr_index",
                                Map.of(
                                        "major", 0,
                                        "minor", 0
                                )
                        )))
        );

        assertEquals(List.of(new Payment(
                new IndexedAddress(
                        0,
                        0,
                        "55LTR8KniP4LQGJSPtbYDacR7dz8RBFnsfAKMaMuwUNYX6aQbBcovzDPyrQF9KXF9tVU6Xk3K8no1BywnJX6GvZX8yJsXvt"
                ),
                1000000000000L,
                127606L,
                hexStringToByteArray("3292e83ad28fc1cc7bc26dbd38862308f4588680fbf93eae3e803cddd1bd614f"),
                0L
        )), client.getPayments("60900e5603bf96e3").get());
    }

    @Test
    void testTransferSuccessOneResult() throws Exception {
        setupMockEndpointSuccess(
                "transfer_split",
                Map.of(
                        "destinations",
                        List.of(
                                Map.of(
                                        "address",
                                        "7BnERTpvL5MbCLtj5n9No7J5oE5hHiB3tVCK5cjSvCsYWD2WRJLFuWeKTLiXo5QJqt2ZwUaLy2Vh" +
                                                "1Ad51K7FNgqcHgjW85o",
                                        "amount",
                                        1000000000000L
                                ),
                                Map.of(
                                        "address",
                                        "75sNpRwUtekcJGejMuLSGA71QFuK1qcCVLZnYRTfQLgFU5nJ7xiAHtR5ihioS53KMe8pBhH61mor" +
                                                "aZHyLoG4G7fMER8xkNv",
                                        "amount",
                                        2000000000000L
                                )
                        )
                ),
                Map.of(
                        "amount_list",
                        List.of(3000000000000L),
                        "fee_list",
                        List.of(473710000L),
                        "multisig_txset",
                        "",
                        "tx_hash_list",
                        List.of("4adcdc1af3f665770cdf8fb7a380887cd07ac53c2b771bd18df5ca375d5e7540"),
                        "tx_key_list",
                        List.of("5b455c0f97168be652a2c03c5c68a064bb84cdae4ddef01b5c48d73a0bbb27075fb714f2ca19ea6c8ff5" +
                                "92417e606addea6deb1d6530e2969f75681ffcbfc4075677b94a8c9197963ae38fa6f543ee68f0a4c4bb" +
                                "da4c453f39538f00b28e980ea08509730b51c004960101ba2f3adbc34cbbdff0d5af9dba061b523090debd06"),
                        "unsigned_txset",
                        ""
                )
        );

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
