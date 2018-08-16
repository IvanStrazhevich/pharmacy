package by.epam.pharmacy.command;

/**
 * Define command actions
 */
public enum CommandType {
    WELCOME_PAGE("WelcomePage"), SET_LOCALE("SetLocale"),
    //Actions with user
    EDIT_USER_DATA_PAGE("EditUserDataPage"), SAVE_CLIENT_DETAIL("SaveClientDetail"),
    LOGIN_PAGE("LoginPage"), CHECK_LOGIN("CheckLogin"),
    REGISTER_USER("RegisterUser"), INVALIDATE_SESSION("InvalidateSession"),
    REGISTER_PAGE("RegisterPage"), UPLOAD_PHOTO("UploadPhoto"),
    //Actions with recipe for doctor's level
    RECIPE_LIST("RecipeList"), EDIT_RECIPE("EditRecipe"), APPROVE_RECIPE("ApproveRecipe"),
    DELETE_RECIPE("DeleteRecipe"),
    //Actions with medicine for pharmacist's level
    MEDICINE_LIST("MedicineList"), EDIT_MEDICINE("EditMedicine"), SAVE_MEDICINE("SaveMedicine"),
    USER_LIST("UserList"), EDIT_ACCESS_LEVEL("EditAccessLevel"), SAVE_ACCESS_LEVEL("SaveAccessLevel"),
    REMOVE_MEDICINE_FROM_BASE("RemoveMedicineFromBase"), REMOVE_FROM_AVAILABLE("RemoveFromAvailable"),
    //Actions for client's level
    PAY_ORDER("PayOrder"), MAKE_PAYMENT("MakePayment"), REMOVE_MEDICINE_FROM_ORDER("RemoveMedicineFromOrder"),
    FIND_MEDICINE("FindMedicine"), EDIT_ORDER("EditOrder"), ADD_MEDICINE_TO_ORDER("AddMedicineToOrder"),
    CHANGE_QUANTITY("ChangeQuantity"), DEMAND_RECIPE("DemandRecipe");
    private String command;

    /**
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
     * @param command
     */
    public void setCommand(String command) {
        this.command = command;
    }
}

