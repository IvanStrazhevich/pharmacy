package by.epam.pharmacy.controller;

import by.epam.pharmacy.command.CommandType;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.impl.*;
import by.epam.pharmacy.exception.PharmacyServletException;

import java.util.HashMap;

/**
 *
 */
public class CommandMapper {
    private static CommandMapper instance;
    private HashMap<String, RequestCommand> servletMap = new HashMap<>();

    /**
     *
     */
    private CommandMapper() {
        servletMap.put(CommandType.LOGIN_PAGE.getCommand(), new LoginPageCommand());
        servletMap.put(CommandType.CHECK_LOGIN.getCommand(), new CheckUserCommand());
        servletMap.put(CommandType.WELCOME_PAGE.getCommand(), new WelcomePageCommand());
        servletMap.put(CommandType.EDIT_USER_DATA_PAGE.getCommand(), new EditUserDataPageCommand());
        servletMap.put(CommandType.SAVE_CLIENT_DETAIL.getCommand(), new SaveClientDetailCommand());
        servletMap.put(CommandType.REGISTER_USER.getCommand(), new RegisterUserCommand());
        servletMap.put(CommandType.REGISTER_PAGE.getCommand(), new RegisterPageCommand());
        servletMap.put(CommandType.INVALIDATE_SESSION.getCommand(), new InvalidateSessionCommand());
        servletMap.put(CommandType.SET_LOCALE.getCommand(), new LocaleSwitchCommand());
        servletMap.put(CommandType.RECIPE_LIST.getCommand(), new RecipeListCommand());
        servletMap.put(CommandType.EDIT_RECIPE.getCommand(), new EditRecipeCommand());
        servletMap.put(CommandType.APPROVE_RECIPE.getCommand(), new ApproveRecipeCommand());
        servletMap.put(CommandType.MEDICINE_LIST.getCommand(), new MedicineListCommand());
        servletMap.put(CommandType.EDIT_MEDICINE.getCommand(), new EditMedicineCommand());
        servletMap.put(CommandType.SAVE_MEDICINE.getCommand(), new SaveMedicineCommand());
        servletMap.put(CommandType.USER_LIST.getCommand(), new UserListCommand());
        servletMap.put(CommandType.EDIT_ACCESS_LEVEL.getCommand(), new EditAccessLevelCommand());
        servletMap.put(CommandType.SAVE_ACCESS_LEVEL.getCommand(), new SaveAccessLevelCommand());
        servletMap.put(CommandType.REMOVE_MEDICINE_FROM_ORDER.getCommand(), new RemoveMedicineFromOrderCommand());
        servletMap.put(CommandType.DEMAND_RECIPE.getCommand(), new DemandRecipeCommand());
        servletMap.put(CommandType.CHANGE_QUANTITY.getCommand(), new ChangeQuantity());
        servletMap.put(CommandType.PAY_ORDER.getCommand(), new PayOrderCommand());
        servletMap.put(CommandType.FIND_MEDICINE.getCommand(), new FindMedicineCommand());
        servletMap.put(CommandType.ADD_MEDICINE_TO_ORDER.getCommand(), new AddMedicineToOrder());
        servletMap.put(CommandType.EDIT_ORDER.getCommand(), new EditOrderCommand());
        servletMap.put(CommandType.REMOVE_MEDICINE_FROM_BASE.getCommand(), new RemoveFromDataBaseCommand());
        servletMap.put(CommandType.DELETE_RECIPE.getCommand(), new DeleteRecipeCommand());
    }

    /**
     *
     */
    static CommandMapper getInstance() throws PharmacyServletException {
        if (instance == null) {
            instance = new CommandMapper();
        }
        return instance;
    }

    /**
     *
     */
    HashMap<String, RequestCommand> getServletMap() {
        return servletMap;
    }
}

