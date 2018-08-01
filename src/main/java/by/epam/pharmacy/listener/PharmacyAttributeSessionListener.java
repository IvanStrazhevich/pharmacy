package by.epam.pharmacy.listener;

import by.epam.pharmacy.command.AttributeName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * 
 */
@WebListener
public class PharmacyAttributeSessionListener implements HttpSessionAttributeListener {
    Logger logger = LogManager.getLogger();

    /**
     * 
     * @param event 
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        logger.debug("Added worked");
        if (event.getSession().getAttribute(AttributeName.LOGGED.getAttribute()) != null && event.getSession().getAttribute(AttributeName.LOGGED.getAttribute()).equals(AttributeName.LOGGED.getAttribute())) {
            logger.debug(AttributeName.LOGGED.getAttribute());
            logger.debug(event.getSession().getAttribute(AttributeName.NEED_LOGIN.getAttribute()));
            event.getSession().removeAttribute(AttributeName.NEED_LOGIN.getAttribute());
            logger.debug(event.getSession().getAttribute(AttributeName.NEED_LOGIN.getAttribute()));
            logger.debug(event.getSession().getAttribute(AttributeName.NEED_REGISTER.getAttribute()));
            event.getSession().removeAttribute(AttributeName.NEED_REGISTER.getAttribute());
            logger.debug(event.getSession().getAttribute(AttributeName.NEED_REGISTER.getAttribute()));
        }
    }

    /**
     * 
     * @param event 
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        logger.debug("Removed worked");
    }

    /**
     * 
     * @param event 
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

        logger.debug("Replased worked");

    }
}

