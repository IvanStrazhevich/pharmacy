package by.epam.pharmacy.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

public class InputValidatorTest {
    private static Logger logger = LogManager.getLogger();
    private InputValidator validator;

    @DataProvider
    public Object[][] inputEmail() {
        return new Object[][]{
                {"mike43@gmail.coma", true},
                {"mikegmail.com", false},
                {"mike34@gu.co", false},
                {"mike@go.h", false},
        };
    }

    @DataProvider
    public Object[][] inputWord() {
        return new Object[][]{
                {"wedrsfvcnhytredcvgbgsbhnmjhfrtyuik,mbvfcregf", false},
                {"w3e4rvbhtrsdrbyjd$%^&*((%$#@!@#$FVNNMKHGDSZrt", false},
                {"asdfgcvbhnj1234gfrth5nbgtyhfbnklmytrbyfgrhjns", true},
                {"", false},
                {"4", true},
                {"wert drew", false}
        };
    }

    @DataProvider
    public Object[][] inputPassword() {
        return new Object[][]{
                {"wedrsfvcn, hytredcvgb! g (sbhnmj) hfrtyuik, \\n mbvfcregf", false},
                {"sfvcn,hytredcvgb!g(sbhnmj)hfrtyuik,nmbvfcregf", true},
                {"15ry&ft", true},
                {"15r%", false},
                {"1 5r %", false},
                {"      ", false},
                {"", false}
        };
    }

    @DataProvider
    public Object[][] inputText() {
        return new Object[][]{
                {"wedrsfvcn, hytredcvgb! g (sbhnmj) hfrtyuik, \\n mbvfcregf", true},
                {"Аспирин", true},
                {"от головы", true},
                {"s,", true},
                {"1", true},
                {"", false},
                {"a", true},
                {"/", true}
        };
    }

    @DataProvider
    public Object[][] inputInteger() {
        return new Object[][]{
                {"wedrsfvcn, hytredcvgb! g (sbhnmj) hfrtyuik, \\n mbvfcregf", false},
                {"111111111111", false},
                {"1.1", false},
                {"", false},
                {"a", false},
                {"/", false},
                {"11111111111", true},
                {"0", true}
        };
    }

    @DataProvider
    public Object[][] inputDecimal() {
        return new Object[][]{
                {"wedrsfvcn, hytredcvgb! g (sbhnmj) hfrtyuik, \\n mbvfcregf", false},
                {"1111111", false},
                {"1.111", false},
                {"", false},
                {"a", false},
                {"/", false},
                {"1.11", true},
                {"0", true}
        };
    }

    @DataProvider
    public Object[][] inputTimeStamp() {
        return new Object[][]{
                {"1290-qw-we er:df:df", false},
                {"1200-13-32 24:60:60", false},
                {"fdghdjsk", false},
                {"2012-02-29 23:59:59", true},
                {"2010-02-28 23:59:59", true},
                {"2010-02-29 23:59:59", false},
                {"2010-02-30 23:59:59", false},
                {"2010-12-32 23:59:59", false},
                {"2010-11-31 23:59:59", false},
                {"a", false},
                {"/", false},
                {"1.11", false},
                {"0", false}
        };
    }

    @DataProvider
    public Object[][] inputPhone() {
        return new Object[][]{
                {"+375000000000", true},
                {"+gyh000000000", false},
                {"+79161234567", true},
                {"+375172345678", true},
                {"a", false},
                {"", false},
                {"/", false},
                {"1.11", false},
                {"0", false}
        };
    }

    @DataProvider
    public Object[][] inputString() {
        return new Object[][]{
                {2, "+37", false},
                {10, "0123456789", true},
                {1, "+", true},
                {1, "a", true},
                {1, "", true},
                {0, "/", false},
                {3, "1.11", false},
                {1, "0", true}
        };
    }

    @BeforeClass
    public void before() {
        logger = LogManager.getLogger();
        validator = new InputValidatorImpl();
    }

    @AfterClass
    public void after() {
        validator = null;
        logger = null;

    }

    /*@BeforeMethod
    public void setUp() {
        logger = LogManager.getLogger();
        validator = new InputValidatorImpl();
    }

    @AfterMethod
    public void tearDown() {
        validator = null;
        logger = null;

    }
*/
    @Test(dataProvider = "inputWord")
    public void testValidateWord(String word, boolean expected) {
        boolean actual = validator.validateWord(word);
        Assert.assertEquals(actual, expected);

    }

    @Test(dataProvider = "inputEmail")
    public void testValidateEmail(String email, boolean expected) {
        boolean actual = validator.validateEmail(email);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "inputText")
    public void testValidateText(String text, boolean expected) {
        boolean actual = validator.validateText(text);
        Assert.assertEquals(actual, expected);

    }

    @Test(dataProvider = "inputPassword")
    public void testValidatePassword(String password, boolean expected) {
        boolean actual = validator.validatePassword(password);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "inputInteger")
    public void testValidateInteger(String integer, boolean expected) {
        boolean actual = validator.validateInteger(integer);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "inputDecimal")
    public void testValidateDecimal(String decimal, boolean expected) {
        boolean actual = validator.validateDecimal(decimal);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "inputTimeStamp")
    public void testValidateTimeStamp(String timeStamp, boolean expected) {
        boolean actual = validator.validateTimeStamp(timeStamp);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "inputPhone")
    public void testValidatePhone(String phone, boolean expected) {
        boolean actual = validator.validatePhone(phone);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "inputString")
    public void testValidateLength(int length, String string, boolean expected) {
        boolean actual = validator.validateLength(length, string);
        Assert.assertEquals(actual, expected);
    }
}