package by.epam.pharmacy.command;

/**
 * Have all request attributes, that used at application
 */
public enum AttributeName {
    ACTION("action"), RAW_NUMBER("rawNumber"), SHIFT("shift"), LANG("lang"),
    //User attributes
    USER("user"), NEED_LOGIN("needLogin"), NEED_REGISTER("needRegister"), LOGGED("logged"),
    NOT_AUTHORISED("notAuthorised"), USER_EXIST("userExist"), USER_REGISTERED("userRegistered"),
    USER_NOT_REGISTERED("userNotRegistered"),  GREETING("greeting"), LOGOUT("logout"),
    //User details attributes
    CLIENT_DETAIL("clientDetail"), USERS("users"), USER_ID("userId"), LOGIN("login"), PASSWORD("password"),
    ACCESS_LEVEL("accessLevel"), NAME("name"), LASTNAME("lastname"), EMAIL("email"), PHONE("phone"),
    POSTCODE("postcode"), COUNTRY("country"), CITY("city"), ADDRESS("address"), CLIENT_DETAILS("clientDetails"),
    PHOTO("photo"),
    //Medicines and Order Attributes
    MEDICINES("medicines"), ORDER("order"),
    RECIPES("recipes"),  MEDICINE_ID("medicineId"), MEDICINE_NAME("medicineName"),
    DESCRIPTION("description"), DOSAGE("dosage"), RECIPE_REQ("recipeRequired"), PRICE("price"),
    AVAILABLE("available"), MEDICINE("medicine"), QUANTITY_AT_STORAGE("quantityAtStorage"),
    MEDICINE_QUANTITY("medicineQuantity"), MEDICINE_ADDED("medicineAdded"), MEDICINE_DELETED("medicineDeleted"),
    //Recipe attributes
    ORDER_ID("orderId"), RECIPE_REQUESTED("recipeRequested"),
    RECIPE("recipe"), RECIPE_ID("recipeId"), VALID_TILL("validTill"),
    APPROVED("approved"), RECIPE_DELETED("recipeDeleted"),
    //Payment attributes
    ORDER_SUM("orderSum"), PAYMENT("payment"), ACCOUNT("account"), PAYMENT_ID("paymentId"),
    ACCOUNT_DEBIT("accountDebit"), ACCOUNT_CREDIT("accountCredit"), PAYED("payed"),
    VALIDATION_ERROR("validationError");

    private String attribute;

    /**
     * @param attribute
     */
    AttributeName(String attribute) {
        this.attribute = attribute;
    }


    /**
     * @return Attribute type as String value
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * returns Attributes String type value
     */
    @Override
    public String toString() {
        return attribute;
    }
}

