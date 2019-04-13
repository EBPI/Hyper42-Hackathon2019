package nl.hyper42.kim.offchain.utils;

/**
 * The Exception that is thrown by this Application if a runtime problem occures.
 * 
 * @author Micha Wensveen
 */
public class OffchainApplicationRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 2687764222713082249L;

    /**
     * Instantiates a new ApplicationRuntimeException.
     */
    public OffchainApplicationRuntimeException() {
        // Default Constructor
    }

    /**
     * Instantiates a new ApplicationRuntimeException.
     * 
     * @param message - String
     */
    public OffchainApplicationRuntimeException(String message) {
        super(message);
    }

    /**
     * Instantiates a new ApplicationRuntimeException.
     * 
     * @param cause - Throwable
     */
    public OffchainApplicationRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new ApplicationRuntimeException.
     * 
     * @param message - String
     * @param cause - Throwable
     */
    public OffchainApplicationRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
