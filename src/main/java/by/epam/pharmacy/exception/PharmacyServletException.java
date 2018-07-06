package by.epam.pharmacy.exception;

public class PharmacyServletException extends Exception {
    public PharmacyServletException() {
    }

    public PharmacyServletException(String message) {
        super(message);
    }

    public PharmacyServletException(String message, Throwable cause) {
        super(message, cause);
    }

    public PharmacyServletException(Throwable cause) {
        super(cause);
    }

    public PharmacyServletException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
