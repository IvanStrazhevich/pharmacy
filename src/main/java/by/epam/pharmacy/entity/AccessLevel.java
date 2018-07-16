package by.epam.pharmacy.entity;

public enum AccessLevel {
    CLIENT("client"), DOCTOR("doctor"), PHARMACIST("pharmacist");
    private String value;

    AccessLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
