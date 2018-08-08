package by.epam.pharmacy.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputValidatorImpl implements InputValidator {
    private static Logger logger = LogManager.getLogger();
    private static final int VARCHAR_45 = 45;
    private static final int LONGTEXT_ = 65535;
    private static final String TRUE ="true";
    private static final String FALSE ="false";
    private static final String PASSWORD_REG = "(\\p{Punct}?\\w\\p{Punct}?){6,45}";
    private static final String POSTCODE_REG = "\\w{0,10}";
    private static final String WORD_REG = "\\w{1,45}";
    private static final String PHONE_REG = "\\+\\d{10,15}";
    private static final String EMAIL_REG = "\\w{1,}@\\w{3,}\\.\\w{2,4}";
    private static final String TEXT_REG = "(\\s?\\p{Punct}?\\w\\s?\\p{Punct}?){1,65535}";
    private static final String DECIMAL_REG = "\\d{1,6}(\\.\\d{0,2})?";
    private static final String TIMESTAMP_REG = "((1\\d{3})|(20\\d{2}))-((0\\d)|(1[0-2]))-(([0-2]\\d)|(3[0-1])) (([0-1]\\d)|(2[0-3])):([0-5]\\d):([0-5]\\d)";
    private static final String INTEGER_REG = "\\d{1,11}";

    @Override
    public boolean validatePassword(String string) {
        if (string.matches(PASSWORD_REG)) {
            return validateLength(VARCHAR_45, string);
        } else {
            return false;
        }
    }

    @Override
    public boolean validateWord(String string) {
        return string.matches(WORD_REG);
    }

    @Override
    public boolean validateEmail(String string) {
        if (string.matches(EMAIL_REG)) {
            return validateLength(VARCHAR_45, string);
        } else {
            return false;
        }
    }

    @Override
    public boolean validatePhone(String string) {
        return string.matches(PHONE_REG);
    }

    @Override
    public boolean validatePostcode(String string) {
        return string.matches(POSTCODE_REG);
    }

    @Override
    public boolean validateText(String string) {
        if (string.matches(TEXT_REG)) {
            return validateLength(LONGTEXT_, string);
        } else {
            return false;
        }
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
        boolean isTimastamp = false;
        if (string.matches(TIMESTAMP_REG)) {
            Integer year = Integer.valueOf(string.substring(0, 4));
            Integer month = Integer.valueOf(string.substring(5, 7));
            Integer day = Integer.valueOf(string.substring(8, 10));
            Integer hour = Integer.valueOf(string.substring(11, 13));
            Integer minute = Integer.valueOf(string.substring(14, 16));
            Integer seconds = Integer.valueOf(string.substring(17, 19));
            logger.debug(year + " " + month + " " + day + " " + hour + " " + minute + " " + seconds);
            if (isCorrectDate(day, month, year) && isCorrectDate(hour, minute, seconds)) {
                isTimastamp = true;
            }
        }
        return isTimastamp;
    }

    @Override
    public boolean validateLength(int length, String string) {
        return string.length() <= length;
    }

    @Override
    public boolean validateBoolean(String string) {
        return string.equalsIgnoreCase(TRUE)||string.equalsIgnoreCase(FALSE);
    }

    private boolean isLeapYear(Integer year) {
        boolean leapYear = true;
        if ((year % 4) != 0) {
            leapYear = false;
        } else if (((year % 400) != 0) && ((year % 100) == 0)) {
            leapYear = false;
        }
        logger.debug(leapYear);
        return leapYear;
    }

    private boolean isCorrectDate(Integer day, Integer month, Integer year) {
        boolean correct = true;
        if (day < 32 && day != 0) {
            if (month == 2) {
                if (day < 30) {
                    if (isLeapYear(year)) {
                        if (day < 30) {
                            logger.debug("2-29");
                            correct = true;
                        } else {
                            correct = false;
                        }
                    } else if (day < 29) {
                        logger.debug("2-28");
                        correct = true;
                    } else {
                        correct = false;
                    }
                } else {
                    correct = false;
                }
            } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                if (day < 32) {
                    logger.debug("1-3-5-7-8-10-12");
                    correct = true;
                } else {
                    correct = false;
                }
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (day < 31) {
                    logger.debug("4-6-9-11");
                    correct = true;
                } else {
                    correct = false;
                }
            }
            return correct;
        } else {
            return false;
        }
    }

    private boolean isTimeCorrect(int hour, int minute, int second) {
        boolean isCorrectTime = false;
        if (hour < 24 && minute < 60 && second < 60) {
            isCorrectTime = true;
        }
        return isCorrectTime;
    }
}
