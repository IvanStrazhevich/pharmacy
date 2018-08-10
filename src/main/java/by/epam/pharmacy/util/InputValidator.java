package by.epam.pharmacy.util;

public interface InputValidator {
    /**
     * @param string
     * @return
     */
    boolean validatePassword(String string);

    /**
     * @param string
     * @return
     */
    boolean validateWord(String string);

    /**
     * @param string
     * @return
     */
    boolean validateEmail(String string);

    /**
     * @param string
     * @return
     */
    boolean validatePostcode(String string);

    /**
     * @param string
     * @return
     */
    boolean validateText(String string);

    /**
     * @param string
     * @return
     */
    boolean validateInteger(String string);

    /**
     * @param string
     * @return
     */
    boolean validateDecimal(String string);

    /**
     * @param string
     * @return
     */
    boolean validateTimeStamp(String string);

    /**
     * @param string
     * @return
     */
    boolean validatePhone(String string);

    /**
     * @param length
     * @param string
     * @return
     */
    boolean validateLength(int length, String string);

    /**
     * @param string
     * @return
     */
    boolean validateBoolean(String string);
}
