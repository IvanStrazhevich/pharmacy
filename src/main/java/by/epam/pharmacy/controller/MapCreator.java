package by.epam.pharmacy.controller;

import by.epam.pharmacy.command.CommandEnum;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.impl.*;
import by.epam.pharmacy.exception.PharmacyServletException;

import java.util.HashMap;

public class MapCreator {
    private static MapCreator instance;
    private HashMap<String, RequestCommand> servletMap = new HashMap<>();

    private MapCreator() throws PharmacyServletException {

        for (CommandEnum command : CommandEnum.values()
                ) {
            switch (command) {
                case LOGIN_PAGE:
                    servletMap.put(command.getCommand(), new LoginPageCommand());
                    break;
                case CHECK_LOGIN:
                    servletMap.put(command.getCommand(), new CheckUserCommand());
                    break;
                case WELCOME_PAGE:
                    servletMap.put(command.getCommand(), new WelcomePageCommand());
                    break;
                case EDIT_USER_DATA_PAGE:
                    servletMap.put(command.getCommand(), new EditUserDataPageCommand());
                    break;
                case UPLOAD_RESULT_PAGE:
                    servletMap.put(command.getCommand(), new FileReadCommand());
                    break;
                case REGISTER_USER:
                    servletMap.put(command.getCommand(), new RegisterUserCommand());
                    break;
                case REGISTER_PAGE:
                    servletMap.put(command.getCommand(), new RegisterPageCommand());
                    break;
                case INVALIDATE_SESSION:
                    servletMap.put(command.getCommand(), new InvalidateSessionCommand());
                    break;
                case SET_LOCALE:
                    servletMap.put(command.getCommand(), new LocaleSwitchCommand());
                    break;
                case RECIPE_LIST:
                    servletMap.put(command.getCommand(), new RecipeListCommand());
                    break;
                case EDIT_RECIPE:
                    servletMap.put(command.getCommand(), new EditRecipeCommand());
                    break;
                case APPROVE_RECIPE:
                    servletMap.put(command.getCommand(), new ApproveRecipeCommand());
                    break;
                case MEDICINE_LIST:
                    servletMap.put(command.getCommand(), new MedicineListCommand());
                    break;
                case EDIT_MEDICINE:
                    servletMap.put(command.getCommand(), new EditMedicineCommand());
                    break;
                case SAVE_MEDICINE:
                    servletMap.put(command.getCommand(), new SaveMedicineCommand());
                    break;
                case USER_LIST:
                    servletMap.put(command.getCommand(), new UserListCommand());
                    break;
                case EDIT_ACCESS_LVL:
                    servletMap.put(command.getCommand(), new EditAccesslevelCommand());
                    break;
                case SAVE_ACCESS_LVL:
                    servletMap.put(command.getCommand(), new SaveAccessLevelCommand());
                    break;
                case REMOVE_MEDICINE_FROM_ORDER:
                    servletMap.put(command.getCommand(), new RemoveMedicineFromOrderCommand());
                    break;
                case PAY_ORDER:
                    servletMap.put(command.getCommand(), new PayOrderCommand());
                    break;
                case FIND_MEDICINE:
                    servletMap.put(command.getCommand(), new FindMedicineCommand());
                    break;
                case EDIT_ORDER:
                    servletMap.put(command.getCommand(), new EditOrderCommand());
                    break;
                case REMOVE_MEDICINE_FROM_BASE:
                    servletMap.put(command.getCommand(), new RemoveFromDataBaseCommand());
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

    HashMap<String, RequestCommand> getServletMap() {
        return servletMap;
    }
}
