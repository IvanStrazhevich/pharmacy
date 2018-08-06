package by.epam.pharmacy.util;

public interface InputValidator {
    boolean validateEmail(String string);
    boolean validateWord(String string);
    boolean validateNumber(String string);
    boolean validateTimeStamp(String string);
    boolean validateLength(int length, String string);
}
