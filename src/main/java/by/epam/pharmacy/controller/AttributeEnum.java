package by.epam.pharmacy.controller;

public enum AttributeEnum {
    LANG("lang"), NEED_LOGIN("needLogin"),
    LOGIN("login"), PASSWORD("password"),
    NAME("name"), LASTNAME("lastname"),
    LOGGED("logged"),
    USER_EXIST("userExist"), USER_REGISTERED("userRegistered"),
    USER_NOT_REGISTERED("userNotRegistered"),
    GREETING("greeting"), NEED_REGISTER("needRegister"),
    ACCESS_LEVEL("accessLevel");

    private String value;

    AttributeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
