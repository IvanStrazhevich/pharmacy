package by.epam.pharmacy.logic;

import by.epam.pharmacy.exception.EncriptingException;

public interface Encodable {
    String encode(String value) throws EncriptingException;
}
