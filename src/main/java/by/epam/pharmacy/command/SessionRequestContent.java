package by.epam.pharmacy.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;

public class SessionRequestContent {
    private static Logger logger = LogManager.getLogger();
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String> requestParameters;
    private HashMap<String, Object> sessionAttributes;

    public SessionRequestContent() {
        requestAttributes = new HashMap<>();
        sessionAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
    }

    public void extractValues(HttpServletRequest request) {
        Iterator<String> requestAttributeNames = request.getAttributeNames().asIterator();
        while (requestAttributeNames.hasNext()) {
            String attribute = requestAttributeNames.next();
            requestAttributes.put(attribute, request.getAttribute(attribute));
        }
        logger.info(requestAttributes);
        Iterator<String> sessionAttributeNames = request.getSession().getAttributeNames().asIterator();
        while (sessionAttributeNames.hasNext()) {
            String attribute = sessionAttributeNames.next();
            sessionAttributes.put(attribute, request.getSession().getAttribute(attribute));
        }
        logger.info(sessionAttributes);
        Iterator<String> requestParameterNames = request.getParameterNames().asIterator();
        while (requestParameterNames.hasNext()) {
            String parameter = requestParameterNames.next();
            requestParameters.put(parameter, request.getParameter(parameter));
        }
        logger.info(requestParameters);
    }

    public void insertAttributes(HttpServletRequest request) {
        for (String key : requestAttributes.keySet()
                ) {
            request.setAttribute(key, requestAttributes.get(key));
        }

        for (String key : sessionAttributes.keySet()
                ) {
            request.getSession().setAttribute(key, sessionAttributes.get(key));
        }

        for (String key : requestParameters.keySet()
                ) {
            request.setAttribute(key, requestParameters.get(key));
        }

    }

    public HashMap<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    public HashMap<String, String> getRequestParameters() {
        return requestParameters;
    }

    public HashMap<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }
}
