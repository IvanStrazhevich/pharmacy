package by.epam.pharmacy.service;

import by.epam.pharmacy.exception.ServiceException;

public interface Encodable {

    /**
     * Encode incoming string value
     *
     * @param value
     * @return encoded String representation of value
     * @throws ServiceException
     */
    String encode(String value) throws ServiceException;
}

