package by.epam.pharmacy.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Recipe extends Entity {

    private static final long serialVersionUID = 7502910725723537491L;
    private int recipeId;
    private int doctorId;
    private int clientId;
    private int medicineQuantity;
    private BigDecimal dosage;
    private Timestamp validTill;

    public Recipe() {
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getMedicineQuantity() {
        return medicineQuantity;
    }

    public void setMedicineQuantity(int medicineQuantity) {
        this.medicineQuantity = medicineQuantity;
    }

    public BigDecimal getDosage() {
        return dosage;
    }

    public void setDosage(BigDecimal dosage) {
        this.dosage = dosage;
    }

    public Timestamp getValidTill() {
        return validTill;
    }

    public void setValidTill(Timestamp validTill) {
        this.validTill = validTill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Recipe recipe = (Recipe) o;

        if (recipeId != recipe.recipeId) return false;
        if (doctorId != recipe.doctorId) return false;
        if (clientId != recipe.clientId) return false;
        if (medicineQuantity != recipe.medicineQuantity) return false;
        if (dosage != null ? !dosage.equals(recipe.dosage) : recipe.dosage != null) return false;
        return validTill != null ? validTill.equals(recipe.validTill) : recipe.validTill == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + recipeId;
        result = 31 * result + doctorId;
        result = 31 * result + clientId;
        result = 31 * result + medicineQuantity;
        result = 31 * result + (dosage != null ? dosage.hashCode() : 0);
        result = 31 * result + (validTill != null ? validTill.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", doctorId=" + doctorId +
                ", clientId=" + clientId +
                ", medicineQuantity=" + medicineQuantity +
                ", dosage=" + dosage +
                ", validTill=" + validTill +
                '}';
    }
}
