package by.epam.pharmacy.command;

public enum CommandType {
    EDIT_USER_DATA_PAGE("EditUserDataPage"), SAVE_CLIENT_DETAIL("SaveClientDetail"),
    WELCOME_PAGE("WelcomePage"), LOGIN_PAGE("LoginPage"), CHECK_LOGIN("CheckLogin"),
    REGISTER_USER("RegisterUser"), INVALIDATE_SESSION("InvalidateSession"),
    REGISTER_PAGE("RegisterPage"), SET_LOCALE("SetLocale"),
    RECIPE_LIST("RecipeList"), EDIT_RECIPE("EditRecipe"), APPROVE_RECIPE("ApproveRecipe"),
    MEDICINE_LIST("MedicineList"), EDIT_MEDICINE("EditMedicine"), SAVE_MEDICINE("SaveMedicine"),
    USER_LIST("UserList"), EDIT_ACCESS_LEVEL("EditAccessLevel"), SAVE_ACCESS_LEVEL("SaveAccessLevel"),
    PAY_ORDER("PayOrder"), REMOVE_MEDICINE_FROM_ORDER("RemoveMedicineFromOrder"),
    REMOVE_MEDICINE_FROM_BASE("RemoveMedicineFromBase"),
    FIND_MEDICINE("FindMedicine"), EDIT_ORDER("EditOrder"), ADD_MEDICINE_TO_ORDER("AddMedicineToOrder"),
    CHANGE_QUANTITY("ChangeQuantity"), DEMAND_RECIPE("DemandRecipe"), DELETE_RECIPE("DeleteRecipe");
    private String command;

    /**
     * 
     * @param command 
     */
    CommandType(String command) {
        this.command = command;
    }

    /**
     * 
     */
    public String getCommand() {
        return command;
    }

    /**
     * 
     * @param command 
     */
    public void setCommand(String command) {
        this.command = command;
    }
}

