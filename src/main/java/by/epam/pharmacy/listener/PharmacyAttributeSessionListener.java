package by.epam.pharmacy.listener;

import by.epam.pharmacy.command.AttributeEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class PharmacyAttributeSessionListener implements HttpSessionAttributeListener {
    Logger logger = LogManager.getLogger();

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        logger.debug("Added worked");
        if (event.getSession().getAttribute(AttributeEnum.LOGGED.getAttribute()) != null && event.getSession().getAttribute(AttributeEnum.LOGGED.getAttribute()).equals(AttributeEnum.LOGGED.getAttribute())) {
            logger.info(AttributeEnum.LOGGED.getAttribute());
            logger.info(event.getSession().getAttribute(AttributeEnum.NEED_LOGIN.getAttribute()));
            event.getSession().removeAttribute(AttributeEnum.NEED_LOGIN.getAttribute());
            logger.info(event.getSession().getAttribute(AttributeEnum.NEED_LOGIN.getAttribute()));
            logger.info(event.getSession().getAttribute(AttributeEnum.NEED_REGISTER.getAttribute()));
            event.getSession().removeAttribute(AttributeEnum.NEED_REGISTER.getAttribute());
            logger.info(event.getSession().getAttribute(AttributeEnum.NEED_REGISTER.getAttribute()));
        }

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        logger.debug("Removed worked");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

        logger.debug("Replased worked");

    }
}
