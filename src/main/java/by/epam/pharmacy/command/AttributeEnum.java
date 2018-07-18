package by.epam.pharmacy.command;

public enum AttributeEnum {
    ACTION("action"),LANG("lang"), USER("user"),
    NEED_LOGIN("needLogin"), NEED_REGISTER("needRegister"), LOGGED("logged"), NOT_AUTHORISED("notAuthorised"),
    USER_EXIST("userExist"), USER_REGISTERED("userRegistered"),
    USER_NOT_REGISTERED("userNotRegistered"),
    GREETING("greeting"),
    USER_LIST("userList"),LOGIN("login"), PASSWORD("password"),ACCESS_LEVEL("accessLevel"),
    NAME("name"), LASTNAME("lastname"), EMAIL("email"), PHONE("phone"), POSTCODE("postcode"),
    COUNTRY("country"), CITY("city"), ADDRESS("address"),
    MEDICINE_LIST("medicineList"),
    ORDER("order"),
    RECIPE_LIST("recipeList"), LOGOUT("logout");



    private String attribute;

    AttributeEnum(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return attribute;
    }
}
