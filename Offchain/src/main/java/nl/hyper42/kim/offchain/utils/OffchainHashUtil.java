package nl.hyper42.kim.offchain.utils;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * HashUtil class to determine hash value for the given payloads
 *
 * @author rshunmugam
 */
@Component
public final class OffchainHashUtil {
    /**
     * Logging instantiation for this class
     */
    private static final Logger LOG = LoggerFactory.getLogger(OffchainHashUtil.class);

    /**
     * Default Constructor
     */
    private OffchainHashUtil() {
        // private no-args constructor to prevent instantiation
    }

    /**
     * Getting hash which used SHA Algorithm
     * 
     * @param input
     * @return
     */
    public static String getHash(String input) {
        LOG.trace("getting hash for {}", input);
        String hash = Hashing.sha256().hashString(input, StandardCharsets.UTF_8).toString();
        LOG.debug("Hash is{}", hash);
        return hash;
    }
}
