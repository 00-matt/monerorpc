package uk.offtopica.monerorpc.daemon;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.math.BigInteger;

/**
 * A block template for mining a new blob.
 *
 * @see MoneroDaemonRpcClient#getBlockTemplate(String, int)
 */
@Data
@JsonDeserialize(builder = BlockTemplate.BlockTemplateBuilder.class)
@Builder(builderClassName = "BlockTemplateBuilder", toBuilder = true)
public class BlockTemplate {
    /**
     * The
     */
    @NonNull
    private final byte[] blockTemplateBlob;

    /**
     * The block header on which a nonce should be found.
     */
    @NonNull
    private final byte[] blockHashingBlob;

    /**
     * The difficulty of this block.
     */
    @NonNull
    private final BigInteger difficulty;

    /**
     * The reward of this block, in atomic units.
     * <p>
     * 1e12 atomic units = 1 XMR
     */
    @NonNull
    private final Long expectedReward;

    /**
     * The height of the block that will be created with this block template.
     */
    @NonNull
    private final Long height;

    /**
     * The next seed hash used to initialise a RandomX dataset.
     * <p>
     * May be null if unknown.
     */
    private final byte[] nextSeedHash;

    /**
     * The hash of the previous block.
     */
    @NonNull
    private final byte[] prevHash;

    /**
     * Index of the section created by the reservedOffset parameter.
     */
    @NonNull
    private final Integer reservedOffset;

    /**
     * The seed hash of this block, used to initialise the RandomX VM.
     */
    @NonNull
    private final byte[] seedHash;

    /**
     * The block height that the seed hash came from.
     */
    @NonNull
    private final Long seedHeight;

    @JsonPOJOBuilder(withPrefix = "")
    public static class BlockTemplateBuilder {
    }
}
