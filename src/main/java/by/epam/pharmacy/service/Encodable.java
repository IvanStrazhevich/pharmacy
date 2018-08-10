package by.epam.pharmacy.service;

import by.epam.pharmacy.exception.ServiceException;

public interface Encodable {
    /**
     * @param value
     */
    String encode(String value) throws ServiceException;
}

