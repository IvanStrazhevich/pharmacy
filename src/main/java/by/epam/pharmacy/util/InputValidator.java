package by.epam.pharmacy.util;

public interface InputValidator {
    boolean validateWord(String string);
    boolean validateEmail(String string);
    boolean validateText(String string);
    boolean validateNumber(String string);
    boolean validateTimeStamp(String string);
    boolean validateLength(int length, String string);
}
