package by.epam.pharmacy.command;

public enum AttributeName {
    ACTION("action"),LANG("lang"), USER("user"),
    NEED_LOGIN("needLogin"), NEED_REGISTER("needRegister"), LOGGED("logged"), NOT_AUTHORISED("notAuthorised"),
    USER_EXIST("userExist"), USER_REGISTERED("userRegistered"),
    USER_NOT_REGISTERED("userNotRegistered"),
    GREETING("greeting"),
    USERS("users"), USER_ID("userId"),LOGIN("login"), PASSWORD("password"),ACCESS_LEVEL("accessLevel"),
    NAME("name"), LASTNAME("lastname"), EMAIL("email"), PHONE("phone"), POSTCODE("postcode"),
    COUNTRY("country"), CITY("city"), ADDRESS("address"),
    MEDICINES("medicines"),
    ORDER("order"),
    RECIPES("recipes"), LOGOUT("logout"),MEDICINE_ID("medicineId"), MEDICINE_NAME("medicineName"),
    DESCRIPTION("description"), DOSAGE("dosage"), RECIPE_REQ("recipeRequired"), PRICE("price"),
    AVAILABLE("available"),
    MEDICINE("medicine"), QUANTITY_AT_STORAGE("quantityAtStorage"), CLIENT_DETAIL("clientDetail"),
    CLIENT_DETAILS("clientDetails"), MEDICINE_QUANTITY("medicineQuantity"), MEDICINE_ADDED("medicineAdded"),
    ORDER_ID("orderId"), RECIPE_REQUESTED("recipeRequested"),
    RECIPE("recipe"), RECIPE_ID("recipeId"), VALID_TILL("validTill"), APPROVED("approved") ;



    private String attribute;

    AttributeName(String attribute) {
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
