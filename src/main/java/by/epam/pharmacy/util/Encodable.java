package by.epam.pharmacy.util;

import by.epam.pharmacy.exception.EncriptingException;

public interface Encodable {
    String encode(String value) throws EncriptingException;
}
