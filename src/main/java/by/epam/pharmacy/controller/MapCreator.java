package by.epam.pharmacy.controller;

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
                    servletMap.put(command.getCommand(), new LoginPageHandler());
                    break;
                case CHECK_LOGIN:
                    servletMap.put(command.getCommand(), new CheckUserHandler());
                    break;
                case WELCOME_PAGE:
                    servletMap.put(command.getCommand(), new WelcomePageHandler());
                    break;
                case UPLOAD_PAGE:
                    servletMap.put(command.getCommand(), new UploadPageHandler());
                    break;
                case UPLOAD_RESULT_PAGE:
                    servletMap.put(command.getCommand(), new FileReadHandler());
                    break;
                case REGISTER_USER:
                    servletMap.put(command.getCommand(), new RegisterUserHandler());
                    break;
                case REGISTER_PAGE:
                    servletMap.put(command.getCommand(), new RegisterPageHandler());
                    break;
                case INVALIDATE_SESSION:
                    servletMap.put(command.getCommand(), new InvalidateSessionHandler());
                    break;
                case SET_LOCALE:
                    servletMap.put(command.getCommand(), new LocaleSwitchHandler());
                    break;
                default:
                    throw new PharmacyServletException("There is no such command at " + CommandEnum.class + command.getCommand());
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
