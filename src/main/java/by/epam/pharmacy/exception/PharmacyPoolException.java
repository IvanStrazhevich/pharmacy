package by.epam.pharmacy.exception;

public class PharmacyPoolException extends Exception{
    public PharmacyPoolException(String message) {
        super(message);
    }

    public PharmacyPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public PharmacyPoolException(Throwable cause) {
        super(cause);
    }

    protected PharmacyPoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
