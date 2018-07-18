package by.epam.pharmacy.service;

import by.epam.pharmacy.exception.LogicException;

public interface Encodable {
    String encode(String value) throws LogicException;
}
