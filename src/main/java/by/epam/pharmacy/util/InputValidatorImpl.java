package by.epam.pharmacy.util;

public class InputValidatorImpl implements InputValidator{
    private static final String WORD_REG = "\\w{6,45}";
    private static final String PHONE_REG = "\\w{13,15}";
    private static final String EMAIL_REG = "\\w{1,}@\\w{3,}\\.\\w{2,4}";
    private static final String TEXT_REG = "(\\w\\s?){1,65535}";
    private static final String DECIMAL_REG = "\\d{1,6}(\\.?\\d{0,2})?";
    private static final String TIMESTAMP_REG = "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}";
    private static final String INTEGER_REG = "\\d{1,11}";

    @Override
    public boolean validateWord(String string) {
        return string.matches(WORD_REG);
    }

    @Override
    public boolean validateEmail(String string) {
        return string.matches(EMAIL_REG);
    }

    @Override
    public boolean validatePhone(String string) {
        return string.matches(PHONE_REG);
    }

    @Override
    public boolean validateText(String string) {
        return string.matches(TEXT_REG);
    }

    @Override
    public boolean validateInteger(String string) {
        return string.matches(INTEGER_REG);
    }

    @Override
    public boolean validateDecimal(String string) {
        return string.matches(DECIMAL_REG);
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
