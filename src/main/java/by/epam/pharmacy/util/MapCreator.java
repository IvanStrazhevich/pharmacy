package by.epam.pharmacy.util;

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
                case EDIT_USER_DATA_PAGE:
                    servletMap.put(command.getCommand(), new EditUserDataPageHandler());
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
                case RECIPE_LIST:
                    servletMap.put(command.getCommand(), new RecipeListHandler());
                    break;
                case EDIT_RECIPE:
                    servletMap.put(command.getCommand(), new EditRecipeHandler());
                    break;
                case APPROVE_RECIPE:
                    servletMap.put(command.getCommand(), new ApproveRecipeHandler());
                    break;
                case MEDICINE_LIST:
                    servletMap.put(command.getCommand(), new MedicineListHandler());
                    break;
                case EDIT_MEDICINE:
                    servletMap.put(command.getCommand(), new EditMedicineHandler());
                    break;
                case SAVE_MEDICINE:
                    servletMap.put(command.getCommand(), new SaveMedicineHandler());
                    break;
                case USER_LIST:
                    servletMap.put(command.getCommand(), new UserListHandler());
                    break;
                case EDIT_ACCESS_LVL:
                    servletMap.put(command.getCommand(), new EditAccesslevelHandler());
                    break;
                case SAVE_ACCESS_LVL:
                    servletMap.put(command.getCommand(), new SaveAccessLevelHandler());
                    break;
                case REMOVE_MEDICINE_FROM_ORDER:
                    servletMap.put(command.getCommand(), new RemoveMedicineFromOrderHandler());
                    break;
                case PAY_ORDER:
                    servletMap.put(command.getCommand(), new PayOrderHandler());
                    break;
                case FIND_MEDICINE:
                    servletMap.put(command.getCommand(), new FindMedicineHandler());
                    break;
                case EDIT_ORDER:
                    servletMap.put(command.getCommand(), new EditOrderHandler());
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
