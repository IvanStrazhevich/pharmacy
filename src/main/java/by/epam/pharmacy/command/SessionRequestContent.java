package by.epam.pharmacy.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Collects data (request attributes, request parameters, session attributes) from and to HttpServletRequest
 */
public class SessionRequestContent {
    private static Logger logger = LogManager.getLogger();
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String> requestParameters;
    private HashMap<String, Object> sessionAttributes;

    /**
     * Initialize HashMap for request attributes, request parameters, session attributes
     */
    public SessionRequestContent() {

        requestAttributes = new HashMap<>();
        sessionAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
    }

    /**
     * Extracts request attributes, request parameters, session attributes, and stores them into HashMaps
     * @param request HttpServletRequest
     */
    public void extractValues(HttpServletRequest request) {

        Iterator<String> requestAttributeNames = request.getAttributeNames().asIterator();
        while (requestAttributeNames.hasNext()) {
            String attribute = requestAttributeNames.next();
            requestAttributes.put(attribute, request.getAttribute(attribute));
        }

        Iterator<String> sessionAttributeNames = request.getSession().getAttributeNames().asIterator();
        while (sessionAttributeNames.hasNext()) {
            String attribute = sessionAttributeNames.next();
            sessionAttributes.put(attribute, request.getSession().getAttribute(attribute));
        }

        Iterator<String> requestParameterNames = request.getParameterNames().asIterator();
        while (requestParameterNames.hasNext()) {
            String parameter = requestParameterNames.next();
            requestParameters.put(parameter, request.getParameter(parameter));
        }
    }

    /**
     * Inserts request attributes, request parameters, session attributes as output data
     * @param request
     */
    public void insertAttributes(HttpServletRequest request) {
        for (String key : requestAttributes.keySet()
                ) {
            request.setAttribute(key, requestAttributes.get(key));
            logger.debug(key + requestAttributes.get(key));
            logger.debug(request.getAttribute(key));
        }

        for (String key : sessionAttributes.keySet()
                ) {
            request.getSession().setAttribute(key, sessionAttributes.get(key));
            logger.debug(key + sessionAttributes.get(key));
            logger.debug(request.getSession().getAttribute(key));
        }

        for (String key : requestParameters.keySet()
                ) {
            request.setAttribute(key, requestParameters.get(key));
            logger.debug(key + requestParameters.get(key));
            logger.debug(request.getAttribute(key));
        }
    }

    /**
     * @return HashMap with AttributeName value as a key, and request attribute as a value
     * @see AttributeName
     */
    public HashMap<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    /**
     * @return HashMap with AttributeName value as a key, and request parameter as a value
     * @see AttributeName
     */
    public HashMap<String, String> getRequestParameters() {
        return requestParameters;
    }

    /**
     * @return HashMap with AttributeName value as a key, and session attribute as a value
     * @see AttributeName
     */
    public HashMap<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }
}

