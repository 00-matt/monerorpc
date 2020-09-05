package uk.offtopica.monerorpc.daemon;

import lombok.Data;
import lombok.NonNull;

import java.math.BigInteger;

/**
 * @see MoneroDaemonRpcClient#getBlockHeader(long)
 */
@Data
public class BlockHeader {
    /**
     * Size of the block, in bytes.
     */
    private final int size;

    private final int weight;

    private final int longTermWeight;

    /**
     * Number of blocks succeeding this block in the blockchain.
     */
    private final int depth;

    /**
     * The mining difficulty of this block.
     */
    @NonNull
    private final BigInteger difficulty;

    /**
     * Sum of all difficulties of this block and predecessors.
     */
    @NonNull
    private final BigInteger cumulativeDifficulty;

    /**
     * The hash of this block.
     */
    @NonNull
    private final byte[] hash;

    /**
     * The number of blocks preceding this block in the blockchain.
     */
    private final long height;

    /**
     * Protocol version major.
     */
    private final int majorVersion;

    /**
     * Protocol version minor.
     */
    private final int minorVersion;

    /**
     * Mining nonce.
     */
    private final long nonce;

    /**
     * Number of transactions contained in this block.
     */
    private final int numTxes;

    /**
     * {@code true} if this block is not part of the longest chain known to the daemon, and {@code false} otherwise.
     */
    private final boolean orphan;

    /**
     * Hash of the block preceding this block.
     */
    @NonNull
    private final byte[] prevHash;

    /**
     * Hash of the miner reward transaction.
     */
    @NonNull
    private final byte[] minerTxHash;

    /**
     * The reward of this block in atomic units.
     * <p>
     * 1 XMR = 1e12 atomic units.
     */
    private final long reward;

    /**
     * UNIX timestamp of this block.
     */
    private final long timestamp;
}
