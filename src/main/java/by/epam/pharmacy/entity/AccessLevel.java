package by.epam.pharmacy.entity;

public enum AccessLevel {
    CLIENT("client"), DOCTOR("doctor"), PHARMACIST("pharmacist");
    private String level;

    /**
     * 
     * @param level 
     */
    AccessLevel(String level) {
        this.level = level;
    }

    /**
     * 
     */
    public String getLevel() {
        return level;
    }

    /**
     * 
     * @param level 
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return level;
    }
}

