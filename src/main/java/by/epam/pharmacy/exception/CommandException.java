package by.epam.pharmacy.exception;

/**
 *
 */
public class CommandException extends Exception {
    /**
     * @param message
     * @param cause
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public CommandException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public CommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

