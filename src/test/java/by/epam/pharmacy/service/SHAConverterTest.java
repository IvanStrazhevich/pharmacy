package by.epam.pharmacy.service;

import by.epam.pharmacy.util.SHAConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SHAConverterTest {
    private static Logger logger = LogManager.getLogger();
    SHAConverter shaConverter;

    @BeforeMethod
    public void setUp() throws Exception {
        shaConverter = new SHAConverter();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        shaConverter = null;
    }

    @Test
    public void testConvertToSHA1() throws Exception {
        String login = shaConverter.encode("john@gmail.com");
        String password = shaConverter.encode("pass");
        logger.info("login is: "+login+'\n'+ "pass is: "+password);
    }
}