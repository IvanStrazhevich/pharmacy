package by.epam.pharmacy.exception;

/**
 *
 */
public class PharmacyServletException extends Exception {
    /**
     *
     */
    public PharmacyServletException() {
    }

    /**
     * @param message
     */
    public PharmacyServletException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public PharmacyServletException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public PharmacyServletException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public PharmacyServletException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

