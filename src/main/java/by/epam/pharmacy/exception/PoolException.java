package by.epam.pharmacy.exception;

/**
 * 
 */
public class PoolException extends Exception{
    /**
     * 
     * @param message 
     */
    public PoolException(String message) {
        super(message);
    }

    /**
     * 
     * @param message 
     * @param cause 
     */
    public PoolException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 
     * @param cause 
     */
    public PoolException(Throwable cause) {
        super(cause);
    }

    /**
     * 
     * @param message 
     * @param cause 
     * @param enableSuppression 
     * @param writableStackTrace 
     */
    protected PoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

