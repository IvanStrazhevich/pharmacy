package by.epam.pharmacy.util;

public enum CommandEnum {
    EDIT_USER_DATA_PAGE("EditUserDataPage"), UPLOAD_RESULT_PAGE("UploadResultPage"),
    WELCOME_PAGE("WelcomePage"), LOGIN_PAGE("LoginPage"), CHECK_LOGIN("CheckLogin"),
    REGISTER_USER("RegisterUser"), INVALIDATE_SESSION("InvalidateSession"),
    REGISTER_PAGE("RegisterPage"), SET_LOCALE("SetLocale"),
    RECIPE_LIST("RecipeList"), EDIT_RECIPE("EditRecipe"), APPROVE_RECIPE("ApproveRecipe"),
    MEDICINE_LIST("MedicineList"), EDIT_MEDICINE("EditMedicine"), SAVE_MEDICINE("SaveMedicine"),
    USER_LIST("UserList"), EDIT_ACCESS_LVL("EditAccessLevel"), SAVE_ACCESS_LVL("SaveAccessLevel"),
    PAY_ORDER("PayOrder"), REMOVE_MEDICINE_FROM_ORDER("RemoveMedicineFromOrder"),
    FIND_MEDICINE("FindMedicine"), EDIT_ORDER("EditOrder");
    private String command;

    CommandEnum(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
