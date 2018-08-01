package by.epam.pharmacy.entity;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 
 */
public class Medicine extends Entity{
    private static final long serialVersionUID = 267509931711743300L;
    private int medicineId;
    private String medicineName;
    private String description;
    private BigDecimal dosage;
    private boolean recipeRequired;
    private BigDecimal price;
    private boolean available;
    private int quantityAtStorage;
    private ArrayList<OrderHasMedicine> orderHasMedicines;


    /**
     * 
     */
    public Medicine() {
    }

    /**
     * 
     */
    public int getMedicineId() {
        return medicineId;
    }

    /**
     * 
     * @param medicineId 
     */
    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    /**
     * 
     */
    public String getMedicineName() {
        return medicineName;
    }

    /**
     * 
     * @param medicineName 
     */
    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    /**
     * 
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     */
    public BigDecimal getDosage() {
        return dosage;
    }

    /**
     * 
     * @param dosage 
     */
    public void setDosage(BigDecimal dosage) {
        this.dosage = dosage;
    }

    /**
     * 
     */
    public boolean isRecipeRequired() {
        return recipeRequired;
    }

    /**
     * 
     * @param recipeRequired 
     */
    public void setRecipeRequired(boolean recipeRequired) {
        this.recipeRequired = recipeRequired;
    }

    /**
     * 
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 
     * @param price 
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * 
     * @param available 
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * 
     */
    public int getQuantityAtStorage() {
        return quantityAtStorage;
    }

    /**
     * 
     * @param quantityAtStorage 
     */
    public void setQuantityAtStorage(int quantityAtStorage) {
        this.quantityAtStorage = quantityAtStorage;
    }

    /**
     * 
     */
    public ArrayList<OrderHasMedicine> getOrderHasMedicines() {
        return orderHasMedicines;
    }

    /**
     * 
     * @param orderHasMedicines 
     */
    public void setOrderHasMedicines(ArrayList<OrderHasMedicine> orderHasMedicines) {
        this.orderHasMedicines = orderHasMedicines;
    }

    /**
     * 
     * @param o 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Medicine medicine = (Medicine) o;

        if (medicineId != medicine.medicineId) return false;
        if (recipeRequired != medicine.recipeRequired) return false;
        if (available != medicine.available) return false;
        if (quantityAtStorage != medicine.quantityAtStorage) return false;
        if (medicineName != null ? !medicineName.equals(medicine.medicineName) : medicine.medicineName != null)
            return false;
        if (description != null ? !description.equals(medicine.description) : medicine.description != null)
            return false;
        if (dosage != null ? !dosage.equals(medicine.dosage) : medicine.dosage != null) return false;
        if (price != null ? !price.equals(medicine.price) : medicine.price != null) return false;
        return orderHasMedicines != null ? orderHasMedicines.equals(medicine.orderHasMedicines) : medicine.orderHasMedicines == null;
    }

    /**
     * 
     */
    @Override
    public int hashCode() {
        int result = medicineId;
        result = 31 * result + (medicineName != null ? medicineName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dosage != null ? dosage.hashCode() : 0);
        result = 31 * result + (recipeRequired ? 1 : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (available ? 1 : 0);
        result = 31 * result + quantityAtStorage;
        result = 31 * result + (orderHasMedicines != null ? orderHasMedicines.hashCode() : 0);
        return result;
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "Medicine{" +
                "medicineId=" + medicineId +
                ", medicineName='" + medicineName + '\'' +
                ", description='" + description + '\'' +
                ", dosage=" + dosage +
                ", recipeRequired=" + recipeRequired +
                ", price=" + price +
                ", available=" + available +
                ", quantityAtStorage=" + quantityAtStorage +
                ", orderHasMedicines=" + orderHasMedicines +
                '}';
    }
}

