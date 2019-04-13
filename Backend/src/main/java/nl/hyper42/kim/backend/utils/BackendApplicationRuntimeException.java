package nl.hyper42.kim.backend.utils;

/**
 * The Exception that is thrown by this Application if a runtime problem occures.
 * 
 * @author Micha Wensveen
 */
public class BackendApplicationRuntimeException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -7533455357356262986L;

    /**
     * Instantiates a new ApplicationRuntimeException.
     */
    public BackendApplicationRuntimeException() {
        // Default Constructor
    }

    /**
     * Instantiates a new ApplicationRuntimeException.
     * 
     * @param message - String
     */
    public BackendApplicationRuntimeException(String message) {
        super(message);
    }

    /**
     * Instantiates a new ApplicationRuntimeException.
     * 
     * @param cause - Throwable
     */
    public BackendApplicationRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new ApplicationRuntimeException.
     * 
     * @param message - String
     * @param cause - Throwable
     */
    public BackendApplicationRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
