package by.epam.pharmacy.util;

public class InputValidatorImpl implements InputValidator{
    private static final String EMAIL_REG = "\\w{1,}@\\w{3,}\\.\\w{2,4}";
    private static final String TEXT_REG = "(\\w+\\s+?)+";
    private static final String NUMBER_REG = "\\d+(\\.?\\d+)?";
    private static final String TIMESTAMP_REG = "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}";
    @Override
    public boolean validateEmail(String string) {
        return string.matches(EMAIL_REG);
    }

    @Override
    public boolean validateWord(String string) {
        return string.matches(TEXT_REG);
    }

    @Override
    public boolean validateNumber(String string) {
        return string.matches(NUMBER_REG);
    }

    @Override
    public boolean validateTimeStamp(String string) {
        return string.matches(TIMESTAMP_REG);
    }

    @Override
    public boolean validateLength(int length, String string) {
        return string.length()<=length;
    }
}
