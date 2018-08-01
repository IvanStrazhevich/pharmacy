package by.epam.pharmacy.exception;

import java.sql.SQLException;

/**
 * 
 */
public class DaoException extends SQLException {
    /**
     * 
     * @param reason 
     * @param SQLState 
     * @param vendorCode 
     */
    public DaoException(String reason, String SQLState, int vendorCode) {
        super(reason, SQLState, vendorCode);
    }

    /**
     * 
     * @param reason 
     * @param SQLState 
     */
    public DaoException(String reason, String SQLState) {
        super(reason, SQLState);
    }

    /**
     * 
     * @param reason 
     */
    public DaoException(String reason) {
        super(reason);
    }

    /**
     * 
     */
    public DaoException() {
    }

    /**
     * 
     * @param cause 
     */
    public DaoException(Throwable cause) {
        super(cause);
    }

    /**
     * 
     * @param reason 
     * @param cause 
     */
    public DaoException(String reason, Throwable cause) {
        super(reason, cause);
    }

    /**
     * 
     * @param reason 
     * @param sqlState 
     * @param cause 
     */
    public DaoException(String reason, String sqlState, Throwable cause) {
        super(reason, sqlState, cause);
    }

    /**
     * 
     * @param reason 
     * @param sqlState 
     * @param vendorCode 
     * @param cause 
     */
    public DaoException(String reason, String sqlState, int vendorCode, Throwable cause) {
        super(reason, sqlState, vendorCode, cause);
    }
}


