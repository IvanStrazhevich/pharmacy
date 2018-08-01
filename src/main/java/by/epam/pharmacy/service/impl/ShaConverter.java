package by.epam.pharmacy.service.impl;

import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.Encodable;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 */
public class ShaConverter implements Encodable {
    private static final String ENCODE="SHA-1";

    /**
     * 
     */
    public ShaConverter() {
    }

    /**
     * 
     * @param string 
     */
    @Override
    public String encode(String string) throws ServiceException {
        byte[] digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance(ENCODE);
            md.reset();
            md.update(string.getBytes());
            digest = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException(e);
        }
        BigInteger bigInt = new BigInteger(1, digest);
        String SHA1Hex = bigInt.toString(16);
        while (SHA1Hex.length() < 32) {
            SHA1Hex = "0" + SHA1Hex;
        }
        return SHA1Hex;
    }
}


