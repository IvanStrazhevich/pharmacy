package by.epam.pharmacy.listener;

import by.epam.pharmacy.command.AttributeName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 *
 */
@WebListener
public class PharmacyAttributeSessionListener implements HttpSessionAttributeListener {
    Logger logger = LogManager.getLogger();

    /**
     * @param event
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        logger.debug("Added worked");
        logger.debug("login is: " + event.getSession().getAttribute(AttributeName.LOGGED.getAttribute()));
        logger.debug(event.getName());
        if (event.getName().equals(AttributeName.LOGGED.getAttribute())) {
            logger.debug("login listener worked");
            HttpSession session = event.getSession();
            logger.debug(session.getId());
            session.removeAttribute(AttributeName.NEED_LOGIN.getAttribute());
            logger.debug(session.getAttribute(AttributeName.NEED_LOGIN.getAttribute()));
            session.removeAttribute(AttributeName.NEED_REGISTER.getAttribute());
            logger.debug(session.getAttribute(AttributeName.NEED_REGISTER.getAttribute()));
        } else {
            logger.debug("login listener not worked");
        }
        if (event.getName().equals(AttributeName.LOGOUT.getAttribute())) {
            logger.debug("logout listener worked" + event.getSession().getAttribute(AttributeName.LOGOUT.getAttribute()));
            HttpSession session = event.getSession();
            session.removeAttribute(AttributeName.LOGGED.getAttribute());
            logger.debug(session.getAttribute(AttributeName.LOGGED.getAttribute()));
            session.removeAttribute(AttributeName.LOGIN.getAttribute());
            logger.debug(session.getAttribute(AttributeName.LOGIN.getAttribute()));
            session.removeAttribute(AttributeName.ACCESS_LEVEL.getAttribute());
            logger.debug(session.getAttribute(AttributeName.ACCESS_LEVEL.getAttribute()));
            logger.debug(session.getId());
            session.invalidate();
        } else {
            logger.debug("logout listener not worked" + event.getSession().getAttribute(AttributeName.LOGOUT.getAttribute()));

        }


    }

    /**
     * @param event
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        logger.debug("Removed worked");
    }

    /**
     * @param event
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        logger.debug("Replased worked");
        HttpSession session = event.getSession();
        if (event.getName().equals(AttributeName.LOGGED.getAttribute())
                && event.getValue().equals(AttributeName.LOGGED.getAttribute())) {
            session.removeAttribute(AttributeName.NEED_LOGIN.getAttribute());
            session.removeAttribute(AttributeName.NEED_REGISTER.getAttribute());
        }
    }
}

