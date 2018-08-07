package by.epam.pharmacy.util;

public interface InputValidator {
    boolean validateWord(String string);
    boolean validateEmail(String string);
    boolean validateText(String string);
    boolean validateInteger(String string);
    boolean validateDecimal(String string);
    boolean validateTimeStamp(String string);
    boolean validatePhone(String string);
    boolean validateLength(int length, String string);
}
