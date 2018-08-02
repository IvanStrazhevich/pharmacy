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
        /*logger.info("Added worked");
        logger.info("login is: " + event.getSession().getAttribute(AttributeName.LOGGED.getAttribute()));
        logger.info(event.getName());
        if (event.getName().equals(AttributeName.LOGGED.getAttribute())) {
            logger.info("login listener worked");
            HttpSession session = event.getSession();
            logger.info(session.getId());
           *//* event.getSession().removeAttribute(AttributeName.NEED_LOGIN.getAttribute());
            logger.info(event.getSession().getAttribute(AttributeName.NEED_LOGIN.getAttribute()));
           *//**//* event.getSession().removeAttribute(AttributeName.LOGOUT.getAttribute());
            logger.info(event.getSession().getAttribute(AttributeName.LOGOUT.getAttribute()));
           *//**//* event.getSession().removeAttribute(AttributeName.NEED_REGISTER.getAttribute());
            logger.info(event.getSession().getAttribute(AttributeName.NEED_REGISTER.getAttribute()));
*//*
        } else {
            logger.info("login listener not worked");
        }*/
        if (event.getName().equals(AttributeName.LOGOUT.getAttribute())) {
            logger.info("logout listener worked" + event.getSession().getAttribute(AttributeName.LOGOUT.getAttribute()));
            HttpSession session = event.getSession();
            /*session.removeAttribute(AttributeName.LOGGED.getAttribute());
            logger.info(session.getAttribute(AttributeName.LOGGED.getAttribute()));
            session.removeAttribute(AttributeName.LOGIN.getAttribute());
            logger.info(session.getAttribute(AttributeName.LOGIN.getAttribute()));
            session.removeAttribute(AttributeName.ACCESS_LEVEL.getAttribute());
            logger.info(session.getAttribute(AttributeName.ACCESS_LEVEL.getAttribute()));
            */logger.info(session.getId());
            session.invalidate();
        } else {
            logger.info("logout listener not worked" + event.getSession().getAttribute(AttributeName.LOGOUT.getAttribute()));

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

        /*logger.info("Replased worked");
        if (event.getName().equals(AttributeName.LOGGED.getAttribute())) {
            event.getSession().removeAttribute(AttributeName.NEED_LOGIN.getAttribute());
            event.getSession().removeAttribute(AttributeName.NEED_REGISTER.getAttribute());
        }*/
    }
}

