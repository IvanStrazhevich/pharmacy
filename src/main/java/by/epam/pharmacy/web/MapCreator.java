package by.epam.pharmacy.web;

import by.epam.pharmacy.*;
import by.epam.pharmacy.exception.PharmacyServletException;
import by.epam.pharmacy.service.*;

import java.util.HashMap;

public class MapCreator {
    private static MapCreator instance;
    private HashMap<String, RequestHandler> servletMap = new HashMap<>();

    private MapCreator() throws PharmacyServletException {

        for (CommandEnum command : CommandEnum.values()
                ) {
            switch (command) {
                case LOGIN_PAGE:
                    servletMap.put(command.getValue(), new LoginPageHandler());
                    break;
                case CHECK_LOGIN:
                    servletMap.put(command.getValue(), new CheckUserHandler());
                    break;
                case WELCOME_PAGE:
                    servletMap.put(command.getValue(), new WelcomePageHandler());
                    break;
                case UPLOAD_PAGE:
                    servletMap.put(command.getValue(), new UploadPageHandler());
                    break;
                case UPLOAD_RESULT_PAGE:
                    servletMap.put(command.getValue(), new XmlReadHandler());
                    break;
                case REGISTER_USER:
                    servletMap.put(command.getValue(), new RegisterUserHandler());
                    break;
                case REGISTER_PAGE:
                    servletMap.put(command.getValue(), new RegisterPageHandler());
                    break;
                default:
                    throw new PharmacyServletException("There is no such command at " + CommandEnum.class + command.getValue());
            }
        }
    }

    static MapCreator getInstance() throws PharmacyServletException {
        if (instance == null) {
            instance = new MapCreator();
        }
        return instance;
    }

    HashMap<String, RequestHandler> getServletMap() {
        return servletMap;
    }
}
